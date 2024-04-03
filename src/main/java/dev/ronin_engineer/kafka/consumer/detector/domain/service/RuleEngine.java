package dev.ronin_engineer.kafka.consumer.detector.domain.service;

import dev.ronin_engineer.kafka.consumer.detector.domain.event.RuleHitEvent;
import dev.ronin_engineer.kafka.consumer.detector.domain.event.TransactionEvent;
import dev.ronin_engineer.kafka.consumer.detector.domain.repository.RuleState;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;


@Slf4j
@Service
@RequiredArgsConstructor
public class RuleEngine {

    private final RuleState ruleState;

    private final RuleHitProducer ruleHitProducer;

    @Value("${rules.max-transactions-last-minutes.id}")
    private String ruleId;

    @Value("${rules.max-transactions-last-minutes.minutes}")
    private Integer periodInMinutes;

    private Integer periodInMs;

    @Value("${rules.max-transactions-last-minutes.max-transactions}")
    private Integer maxTransactions;


    @PostConstruct
    public void init() {
        periodInMs = periodInMinutes * 60 * 1000;
    }

    @SneakyThrows
    public void process(TransactionEvent transaction) {
        processMaxTransactionsLastMinutes(transaction.getDebitAccount(), transaction);
        processMaxTransactionsLastMinutes(transaction.getCreditAccount(), transaction);
    }


    private void processMaxTransactionsLastMinutes(String accountNumber, TransactionEvent transaction) {
        ruleState.addTransaction(accountNumber, transaction.getTransactionId(), transaction.getCreatedAt());

        long from = transaction.getCreatedAt() - periodInMs;
        int transactionCount = ruleState.countTransactionsInRange(
                accountNumber,
                from,
                transaction.getCreatedAt()
        );
        log.info("[{}] Account number {} has {} transactions from {} to {}", ruleId, accountNumber, transactionCount, from, transaction.getCreatedAt());

        if (transactionCount >= maxTransactions) {
            var ruleHit = RuleHitEvent.builder()
                    .accountNumber(accountNumber)
                    .ruleId(ruleId)
                    .issuedAt(transaction.getCreatedAt())
                    .transactionId(transaction.getTransactionId())
                    .build();

            log.info("[{}] Account number {} hit the rule with transaction id {}", ruleId, accountNumber, transaction.getTransactionId());
            ruleHitProducer.send(ruleHit);
        }

        CompletableFuture.runAsync(() -> ruleState.removeTransactionsBefore(accountNumber, from));
    }
}

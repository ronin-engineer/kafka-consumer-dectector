package dev.ronin_engineer.kafka.consumer.detector.application.endpoint.kafka;


import dev.ronin_engineer.kafka.consumer.detector.domain.dto.TransactionEvent;
import dev.ronin_engineer.kafka.consumer.detector.domain.service.RuleEngine;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.LeaderNotAvailableException;
import org.apache.kafka.common.errors.RetriableException;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.DltStrategy;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;

import java.net.SocketTimeoutException;


@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaConsumer {

    final private RuleEngine ruleEngine;


    @RetryableTopic(    // Non-blocking
            attempts = "3",
            backoff = @Backoff(delay = 1000, multiplier = 2.0),
            dltStrategy = DltStrategy.FAIL_ON_ERROR,
            autoCreateTopics = "true",   // false: recommended
            include = {RetriableException.class}
    )
    @KafkaListener(
            topics = "${kafka.transaction.topic}",
            properties = {"spring.json.value.default.type=dev.ronin_engineer.kafka.consumer.detector.domain.dto.TransactionEvent"}
    )
    @SneakyThrows
    public void listenTransactions(@Payload TransactionEvent transaction) {
        log.info("Received transaction message: {}", transaction);
        ruleEngine.process(transaction);
    }


    @DltHandler
    public void retryTransactions(@Payload TransactionEvent transaction) {
        log.warn("Retry transaction message: {}", transaction);
    }
}

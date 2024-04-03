package dev.ronin_engineer.kafka.consumer.detector.domain.repository;

public interface RuleState {

    void addTransaction(String accountNumber, String transactionId, Long timestamp);

    Integer countTransactionsInRange(String accountNumber, Long from, Long to);

    void removeTransactionsBefore(String accountNumber, Long timestamp);
}

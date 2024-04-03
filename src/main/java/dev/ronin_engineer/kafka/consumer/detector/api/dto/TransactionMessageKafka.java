package dev.ronin_engineer.kafka.consumer.detector.api.dto;

import dev.ronin_engineer.kafka.common.dto.KafkaMessage;
import dev.ronin_engineer.kafka.consumer.detector.domain.event.TransactionEvent;

public class TransactionMessageKafka extends KafkaMessage<TransactionEvent> {
}

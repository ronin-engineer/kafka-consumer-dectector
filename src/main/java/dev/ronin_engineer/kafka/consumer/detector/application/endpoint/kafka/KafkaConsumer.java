package dev.ronin_engineer.kafka.consumer.detector.application.endpoint.kafka;


import dev.ronin_engineer.kafka.consumer.detector.domain.dto.TransactionEvent;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaConsumer {


//    @RetryableTopic(
//            attempts = "3",
//            backoff = @Backoff(delay = 100, multiplier = 2.0),
//            dltStrategy = DltStrategy.NO_DLT,
//            autoCreateTopics = "false"
//    )
    @KafkaListener(
            topics = "${kafka.transaction.topic}",
            properties = {"spring.json.value.default.type=dev.ronin_engineer.kafka.consumer.detector.domain.dto.TransactionEvent"}
    )
    @SneakyThrows
    public void listenTransactions(@Payload TransactionEvent transaction) {
        log.info("Received message: " + transaction);
//        throw new Exception("Failed to process transaction");
    }

}

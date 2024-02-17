//package dev.ronin_engineer.kafka.consumer.detector.infrastructure.config;
//
//import dev.ronin_engineer.kafka.consumer.detector.domain.dto.TransactionEvent;
//import dev.ronin_engineer.kafka.consumer.detector.infrastructure.exception.NonRetryableException;
//import dev.ronin_engineer.kafka.consumer.detector.infrastructure.exception.RetryableException;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.core.ConsumerFactory;
//import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
//import org.springframework.kafka.listener.DefaultErrorHandler;
//import org.springframework.kafka.support.serializer.JsonDeserializer;
//import org.springframework.util.backoff.ExponentialBackOff;
//import org.springframework.util.backoff.FixedBackOff;
//
//import java.util.HashMap;
//import java.util.Map;
//
//
//@Slf4j
//@Configuration
//@RequiredArgsConstructor
//public class KafkaConsumerConfig {
//
//    private final ConsumerFactory consumerFactory;
//
//    private final KafkaTemplate kafkaTemplate;
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory);
//
//        DefaultErrorHandler errorHandler = new DefaultErrorHandler(
//                new DeadLetterPublishingRecoverer(kafkaTemplate),
//                new FixedBackOff(1000L, 3)
//        );
//        errorHandler.addRetryableExceptions(RetryableException.class);
//        errorHandler.addNotRetryableExceptions(NonRetryableException.class);
//
//        factory.setCommonErrorHandler(errorHandler);
//        return factory;
//    }
//
//}

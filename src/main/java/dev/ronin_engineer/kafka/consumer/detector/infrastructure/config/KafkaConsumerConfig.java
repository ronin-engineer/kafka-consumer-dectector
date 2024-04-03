//package dev.ronin_engineer.kafka.consumer.detector.infrastructure.config;
//
//import dev.ronin_engineer.kafka.common.dto.KafkaMessage;
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
//import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
//import org.springframework.kafka.support.serializer.JsonDeserializer;
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
//
//    @Bean
//    public ConsumerFactory<Object, Object> consumerFactory(@Value("${kafka.bootstrap-servers}") final String bootstrapServers) {
//        final Map<String, Object> config = new HashMap<>();
//        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//
//        config.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, StringDeserializer.class);
//
//        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
//        config.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class);
//        config.put(JsonDeserializer.VALUE_DEFAULT_TYPE, KafkaMessage.class.getCanonicalName());
//
//        return new DefaultKafkaConsumerFactory<>(config);
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<Object, Object> kafkaListenerContainerFactory(final ConsumerFactory<Object, Object> consumerFactory) {
//        final ConcurrentKafkaListenerContainerFactory<Object, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory);
//        return factory;
//    }
//}

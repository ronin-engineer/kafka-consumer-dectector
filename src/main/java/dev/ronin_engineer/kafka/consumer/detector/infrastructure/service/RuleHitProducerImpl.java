package dev.ronin_engineer.kafka.consumer.detector.infrastructure.service;

import dev.ronin_engineer.kafka.common.constant.EventType;
import dev.ronin_engineer.kafka.common.dto.KafkaMessage;
import dev.ronin_engineer.kafka.common.util.MessageBuilder;
import dev.ronin_engineer.kafka.consumer.detector.domain.constant.MessageCode;
import dev.ronin_engineer.kafka.consumer.detector.domain.event.RuleHitEvent;
import dev.ronin_engineer.kafka.consumer.detector.domain.service.RuleHitProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class RuleHitProducerImpl implements RuleHitProducer {

    @Value("${application-name}")
    private String serviceId;

    @Value("${kafka.rule-hit.topic}")
    private String ruleHitTopic;

    private final KafkaTemplate<String, Object> kafkaTemplate;


    @Override
    public void send(RuleHitEvent event) {
        try {
            var message = MessageBuilder.build(
                    serviceId,
                    EventType.EVENT,
                    MessageCode.RULE_HIT.getCode(),
                    event
            );

            kafkaTemplate.send(ruleHitTopic, message);
        } catch (Exception e) {
            log.error("Failed to produce the message to topic: " + ruleHitTopic);
            e.printStackTrace();
        }
    }
}

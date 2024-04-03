package dev.ronin_engineer.kafka.consumer.detector.domain.service;

import dev.ronin_engineer.kafka.consumer.detector.domain.event.RuleHitEvent;

public interface RuleHitProducer {

    void send(RuleHitEvent event);

}

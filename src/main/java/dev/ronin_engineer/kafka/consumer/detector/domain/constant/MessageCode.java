package dev.ronin_engineer.kafka.consumer.detector.domain.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Getter
public enum MessageCode {

    RULE_HIT("RULE_HIT"),
    ;

    private final String code;
}
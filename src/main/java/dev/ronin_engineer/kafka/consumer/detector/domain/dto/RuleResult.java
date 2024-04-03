package dev.ronin_engineer.kafka.consumer.detector.domain.dto;

import dev.ronin_engineer.kafka.consumer.detector.domain.event.RuleHitEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RuleResult {

    private boolean isHit = false;
    private RuleHitEvent ruleHit;
}

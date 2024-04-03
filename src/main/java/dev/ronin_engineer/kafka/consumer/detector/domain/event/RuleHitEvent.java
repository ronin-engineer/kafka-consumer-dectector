package dev.ronin_engineer.kafka.consumer.detector.domain.event;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RuleHitEvent {
    private String accountNumber;
    private String ruleId;
    private String transactionId;
    private Long issuedAt;
}

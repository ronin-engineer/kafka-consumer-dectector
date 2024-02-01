package dev.ronin_engineer.kafka.consumer.detector.domain.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TransactionEvent {
    private String transactionId;
    private String transactionType;
    private String debitAccount;
    private String creditAccount;
    private BigDecimal amount;
    private Integer status;
    private Long createdAt;
}

package dev.ronin_engineer.kafka.consumer.detector.domain.service;

import dev.ronin_engineer.kafka.consumer.detector.domain.dto.TransactionEvent;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.SocketTimeoutException;

@Slf4j
@Service
public class RuleEngine {



    @SneakyThrows
    public void process(TransactionEvent transaction) {
        throw new SocketTimeoutException();
    }
}

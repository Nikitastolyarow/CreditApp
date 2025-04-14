package ru.netology.creditprocessingservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import ru.netology.creditprocessingservice.config.RabbitMQConfig;
import ru.netology.creditprocessingservice.model.ApplicationResult;

@Service
@RequiredArgsConstructor
public class ApplicationResultSender {

    private final RabbitTemplate rabbitTemplate;

    public void sendResult(ApplicationResult result) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.RESULT_QUEUE, result);
    }
}
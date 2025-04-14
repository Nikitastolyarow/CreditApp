package ru.netology.creditapplicationservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;

@Configuration
public class RabbitMQConfig {

    public static final String RESULT_QUEUE = "credit-application-results";

    @Bean
    public Queue resultQueue() {
        return new Queue(RESULT_QUEUE, false); // false — очередь не сохраняется после перезапуска
    }
}
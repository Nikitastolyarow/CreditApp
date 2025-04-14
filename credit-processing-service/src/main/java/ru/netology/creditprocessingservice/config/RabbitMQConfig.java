package ru.netology.creditprocessingservice.config;


import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String RESULT_QUEUE = "credit-application-results";

    @Bean
    public Queue resultQueue() {
        return new Queue(RESULT_QUEUE, false); // false = не сохраняется после перезапуска
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
package ru.netology.creditprocessingservice.consumer;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.netology.creditprocessingservice.model.ApplicationResult;
import ru.netology.creditprocessingservice.model.CreditApplicationEvent;
import ru.netology.creditprocessingservice.service.ApplicationResultSender;
import ru.netology.creditprocessingservice.service.CreditProcessingService;

@Service
@RequiredArgsConstructor
public class CreditApplicationConsumer {

    private final CreditProcessingService processingService;
    private final ApplicationResultSender resultSender;

    @KafkaListener(topics = "credit-applications", groupId = "credit-group")
    public void consumeApplication(CreditApplicationEvent event) {
        // Обработка заявки
        String status = processingService.process(event);

        // Создание результата
        ApplicationResult result = new ApplicationResult(event.getApplicationId(), status);

        // Отправка в RabbitMQ
        resultSender.sendResult(result);

        System.out.println("Processed application #" + event.getApplicationId() + " → " + status);
    }
}
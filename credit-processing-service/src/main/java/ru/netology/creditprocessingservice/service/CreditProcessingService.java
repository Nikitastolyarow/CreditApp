package ru.netology.creditprocessingservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.netology.creditprocessingservice.model.ApplicationResult;
import ru.netology.creditprocessingservice.model.CreditApplicationEvent;

@Service
@RequiredArgsConstructor
public class CreditProcessingService {

    private final ApplicationResultSender sender;

    public String process(CreditApplicationEvent event) {
        String status;

        // Простая бизнес-логика: одобрить, если хорошие показатели
        if (event.getCreditRating() >= 700 && event.getCreditLoad() < 0.5 && event.getUserIncome() > 50000) {
            status = "APPROVED";
        } else {
            status = "DECLINED";
        }

        ApplicationResult result = new ApplicationResult(event.getApplicationId(), status);

        // Отправляем результат в RabbitMQ
        sender.sendResult(result);
        return status;
    }
}
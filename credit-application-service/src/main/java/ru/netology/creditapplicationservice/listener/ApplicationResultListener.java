package ru.netology.creditapplicationservice.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.netology.creditapplicationservice.event.ApplicationResult;
import ru.netology.creditapplicationservice.model.ApplicationStatus;
import ru.netology.creditapplicationservice.model.CreditApplication;
import ru.netology.creditapplicationservice.repository.CreditApplicationRepository;

@Component
@RequiredArgsConstructor
public class ApplicationResultListener {

    private final CreditApplicationRepository repository;

    @RabbitListener(queues = "credit-application-results")
    public void receive(ApplicationResult result) {
        System.out.println("Получен результат из RabbitMQ: " + result);

        repository.findById(result.getApplicationId()).ifPresent(application -> {
            ApplicationStatus status = ApplicationStatus.valueOf(result.getStatus());
            application.setStatus(status);
            repository.save(application);
            System.out.println("Обновлён статус заявки: " + application.getId() + " → " + status);
        });
    }
}
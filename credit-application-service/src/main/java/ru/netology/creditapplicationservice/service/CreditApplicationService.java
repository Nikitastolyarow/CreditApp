package ru.netology.creditapplicationservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.netology.creditapplicationservice.event.CreditApplicationEvent;
import ru.netology.creditapplicationservice.model.ApplicationStatus;
import ru.netology.creditapplicationservice.model.CreditApplication;
import ru.netology.creditapplicationservice.model.CreditApplicationRequest;
import ru.netology.creditapplicationservice.repository.CreditApplicationRepository;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CreditApplicationService {

    private final CreditApplicationRepository repository;
    private final KafkaTemplate<String, CreditApplicationEvent> kafkaTemplate;


    private final String TOPIC_NAME = "credit-applications";


    public Long createApplication(CreditApplicationRequest request) {
        // Преобразуем DTO в сущность
        CreditApplication application = new CreditApplication();
        application.setCreditAmount(request.getCreditAmount());
        application.setLoanAmount(request.getLoanAmount());
        application.setUserIncome(request.getUserIncome());
        application.setCreditLoad(request.getCreditLoad());
        application.setCreditRating(request.getCreditRating());

        application.setStatus(ApplicationStatus.IN_PROGRESS); // по умолчанию "в обработке"

        CreditApplication saved = repository.save(application);

        CreditApplicationEvent event = new CreditApplicationEvent(
                saved.getId(),
                saved.getCreditAmount(),
                saved.getLoanAmount(),
                saved.getUserIncome(),
                saved.getCreditLoad(),
                saved.getCreditRating()
        );
        kafkaTemplate.send(TOPIC_NAME, event);
        return saved.getId(); // возвращаем ID
    }

    public Optional<ApplicationStatus> getStatusById(Long id) {
        return repository.findById(id)
                .map(ru.netology.creditapplicationservice.model.CreditApplication::getStatus);
    }
}

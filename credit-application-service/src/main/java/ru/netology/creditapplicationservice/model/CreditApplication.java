package ru.netology.creditapplicationservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CreditApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private Double creditAmount;
    private Integer loanAmount;
    private Integer userIncome;
    private Double creditLoad;
    private Integer creditRating;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;


}

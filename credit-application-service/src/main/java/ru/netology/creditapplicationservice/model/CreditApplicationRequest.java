package ru.netology.creditapplicationservice.model;

import lombok.Data;

@Data
public class CreditApplicationRequest {
    private Double creditAmount;  // сумма кредита
    private Integer loanAmount;   // срок кредита
    private Integer userIncome;   // доход пользователя
    private Double creditLoad;    // кредитная нагрузка
    private Integer creditRating; // кредитный рейтинг
}

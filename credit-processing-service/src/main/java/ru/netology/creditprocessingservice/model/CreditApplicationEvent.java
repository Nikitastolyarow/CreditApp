package ru.netology.creditprocessingservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditApplicationEvent {

    private Long applicationId;
    private Double creditAmount;
    private Integer loanAmount;
    private Integer userIncome;
    private Double creditLoad;
    private Integer creditRating;
}

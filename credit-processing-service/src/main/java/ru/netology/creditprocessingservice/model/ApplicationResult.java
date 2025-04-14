package ru.netology.creditprocessingservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ApplicationResult {
    private Long applicationId; // ID заявки
    private String status;      // Результат обработки
}

package ru.netology.creditapplicationservice.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationResult {
    private Long applicationId;
    private String status; // "APPROVED" или "DECLINED"
}
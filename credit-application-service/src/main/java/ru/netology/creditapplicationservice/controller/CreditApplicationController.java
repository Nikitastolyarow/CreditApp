package ru.netology.creditapplicationservice.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.netology.creditapplicationservice.model.ApplicationStatus;
import ru.netology.creditapplicationservice.service.CreditApplicationService;

@RestController
@RequestMapping("/api/credit-applications")
@RequiredArgsConstructor
public class CreditApplicationController {

    private final CreditApplicationService service;

    @PostMapping
    public Long submitApplication(@RequestBody ru.netology.creditapplicationservice.model.CreditApplicationRequest request) {
        return service.createApplication(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationStatus> getApplicationStatus(@PathVariable Long id) {
        return service.getStatusById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
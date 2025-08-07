package org.mohaan.validateEmail.controllers;

import org.mohaan.validateEmail.services.EmailValidationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/validate-email")
public class EmailValidationController {

    private final EmailValidationService emailValidationService;

    public EmailValidationController(EmailValidationService emailValidationService) {
        this.emailValidationService = emailValidationService;
    }

    @GetMapping
    public ResponseEntity<Boolean> validateEmail(@RequestParam String email) {
        boolean isValid = emailValidationService.validateEmail(email);
        return ResponseEntity.ok(isValid);
    }
}

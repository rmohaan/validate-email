package org.mohaan.validateEmail.services;

import org.mohaan.validateEmail.entities.EmailEntity;
import org.mohaan.validateEmail.entities.ValidationResultEntity;
import org.mohaan.validateEmail.repositories.EmailRepository;
import org.mohaan.validateEmail.repositories.ValidationResultRepository;
import org.mohaan.validateEmail.validator.DomainValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailValidationService {

    private final ValidationResultRepository resultRepository;

    public EmailValidationService(EmailRepository emailRepository, ValidationResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    public boolean validateEmail(String email) {
        //String domain = getDomain(email);
        return DomainValidator.isValidDomain(email);
    }

    public boolean validateAndStoreEmail(String email) {
        String domain = getDomain(email);
        boolean isValid = DomainValidator.isValidDomain(domain);
        resultRepository.save(ValidationResultEntity.builder().email(email).status(isValid).build());
        return isValid;
    }

    private String getDomain(String email) {
        if (email != null && email.contains("@")) {
            return email.substring(email.indexOf("@") + 1).toLowerCase();
        }
        return "";
    }
}


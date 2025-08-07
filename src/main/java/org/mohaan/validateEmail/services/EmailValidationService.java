package org.mohaan.validateEmail.services;

import org.apache.commons.validator.routines.EmailValidator;
import org.mohaan.validateEmail.entities.ValidationResultEntity;
import org.mohaan.validateEmail.repositories.EmailRepository;
import org.mohaan.validateEmail.repositories.ValidationResultRepository;
import org.mohaan.validateEmail.validator.DomainValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class EmailValidationService {

    private final ValidationResultRepository resultRepository;

    public EmailValidationService(EmailRepository emailRepository, ValidationResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    public boolean validateEmail(String email) {
        String domain = getDomain(email);
        return !domain.isBlank() && DomainValidator.isValidDomain(domain);
    }

    public boolean validateAndStoreEmail(String email) {
        String domain = getDomain(email);
        boolean isValid = DomainValidator.isValidDomain(domain);
        resultRepository.save(ValidationResultEntity.builder().email(email).status(isValid).build());
        return isValid;
    }

    private String getDomain(String email) {
        var EMAIL_PATTERN =
                Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", Pattern.CASE_INSENSITIVE);

        if (email != null && EmailValidator.getInstance().isValid(email) && EMAIL_PATTERN.matcher(email).matches()) {
            return email.substring(email.indexOf("@") + 1).toLowerCase();
        }
        return "";
    }
}


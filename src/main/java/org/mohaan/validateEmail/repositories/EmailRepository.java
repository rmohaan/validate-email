package org.mohaan.validateEmail.repositories;

import org.mohaan.validateEmail.entities.EmailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<EmailEntity, Long> {

}


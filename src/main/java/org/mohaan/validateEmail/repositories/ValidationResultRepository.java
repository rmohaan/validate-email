package org.mohaan.validateEmail.repositories;

import org.mohaan.validateEmail.entities.ValidationResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ValidationResultRepository extends JpaRepository<ValidationResultEntity, Long> {

}


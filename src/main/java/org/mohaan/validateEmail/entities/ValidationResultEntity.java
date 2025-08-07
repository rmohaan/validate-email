package org.mohaan.validateEmail.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "email_validation_results")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ValidationResultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private boolean status;
}


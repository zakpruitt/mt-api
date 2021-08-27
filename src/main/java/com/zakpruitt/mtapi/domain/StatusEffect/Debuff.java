package com.zakpruitt.mtapi.domain.StatusEffect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Debuff {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Debuff Name is required.")
    private String debuffName;
    @NotEmpty(message = "Debuff Description is required.")
    private String debuffDescription;
    @NotNull
    private String imageURL;
}

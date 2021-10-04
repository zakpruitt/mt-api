package com.zakpruitt.mtapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Mutator extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Mutator Name is required.")
    private String mutatorName;
    @NotEmpty(message = "Mutator Description is required.")
    @Lob
    private String mutatorDescription;
    @NotNull
    private String imageURL;
}

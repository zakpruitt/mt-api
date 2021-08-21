package com.zakpruitt.mtapi.domain.Artifact;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Artifact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Card Name is required.")
    private String artifactName;
    @NotEmpty(message = "Card Lore is required.")
    private String artifactDescription;
    @NotEmpty(message = "Card Lore is required.")
    @Lob
    private String artifactLore;
}

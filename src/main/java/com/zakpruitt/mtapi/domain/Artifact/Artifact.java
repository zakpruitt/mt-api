package com.zakpruitt.mtapi.domain.Artifact;

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
public class Artifact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Artifact Name is required.")
    private String artifactName;
    @NotEmpty(message = "Artifact Description is required.")
    private String artifactDescription;
    @NotEmpty(message = "Artifact Lore is required.")
    @Lob
    private String artifactLore;
    @NotNull
    private String imageURL;
}

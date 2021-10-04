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
public class Sin extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Sin Name is required.")
    private String sinName;
    @NotEmpty(message = "Sin Description is required.")
    @Lob
    private String sinDescription;
    @NotNull
    private String imageURL;
}

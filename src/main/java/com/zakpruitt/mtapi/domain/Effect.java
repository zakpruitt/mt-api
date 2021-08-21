package com.zakpruitt.mtapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@Entity
@Table
public class Effect {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "Effect is required.")
    private String effect;
    @NotEmpty(message = "Effect description is required.")
    private String effectDescription;
}

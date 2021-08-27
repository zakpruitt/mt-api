package com.zakpruitt.mtapi.domain.StatusEffect;

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
public class Buff {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Buff Name is required.")
    private String buffName;
    @NotEmpty(message = "Buff Description is required.")
    private String buffDescription;
    @NotNull
    private String imageURL;
}

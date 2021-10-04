package com.zakpruitt.mtapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Enemy extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Enemy Name is required.")
    private String enemyName;
    @NotEmpty(message = "Enemy Lore is required.")
    @Lob
    private String enemyLore;
    @Nullable
    private String type;
    @NotNull
    private String imageURL;
    @NotNull
    private int damage;
    @NotNull
    private int health;
}

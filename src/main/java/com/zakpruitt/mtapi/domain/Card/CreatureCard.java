package com.zakpruitt.mtapi.domain.Card;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.PostConstruct;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CreatureCard extends Card {
    @PostConstruct
    private void assignType() {
        this.setType("Creature");
    }

    @NotNull
    private int capacity;
    @NotNull
    private int damage;
    @NotNull
    private int health;
}

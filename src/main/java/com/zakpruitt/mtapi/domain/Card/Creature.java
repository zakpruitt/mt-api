package com.zakpruitt.mtapi.domain.Card;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Creature extends CardParent {
    @NotNull
    private int capacity;
    @NotNull
    private int damage;
    @NotNull
    private int health;
}

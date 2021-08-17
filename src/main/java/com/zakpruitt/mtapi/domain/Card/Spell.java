package com.zakpruitt.mtapi.domain.Card;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.PostConstruct;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Spell extends CardParent {
    @PostConstruct
    private void assignType() {
        this.setType("Spell");
    }
}

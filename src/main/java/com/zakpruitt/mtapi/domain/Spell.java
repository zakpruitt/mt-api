package com.zakpruitt.mtapi.domain;

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
public class Spell extends Card {
    @PostConstruct
    private void assignType() {
        this.setType("Spell");
    }
}

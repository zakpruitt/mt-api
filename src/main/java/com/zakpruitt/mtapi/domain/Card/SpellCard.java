package com.zakpruitt.mtapi.domain.Card;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.annotation.PostConstruct;
import javax.persistence.Entity;

@Data
@AllArgsConstructor
@Entity
public class SpellCard extends Card {
}

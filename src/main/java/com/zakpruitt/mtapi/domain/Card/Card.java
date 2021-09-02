package com.zakpruitt.mtapi.domain.Card;


import com.zakpruitt.mtapi.domain.AuditModel;
import com.zakpruitt.mtapi.domain.Modifier;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Card extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Card Name is required.")
    private String cardName;
    @NotEmpty(message = "Card Lore is required.")
    @Lob
    private String cardLore;
    @NotNull
    private String type;
    @NotNull
    private String subtype;
    @NotNull
    private int rarity;
    @NotNull
    private String clan;
    @NotNull
    private int emberCost;
    @NotNull
    private String imageURL;
    @NotNull
    private String description;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "card_id", referencedColumnName = "id")
    List<Modifier> modifiers = new ArrayList<>();
}

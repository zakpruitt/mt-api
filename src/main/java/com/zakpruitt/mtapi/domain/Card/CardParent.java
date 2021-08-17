package com.zakpruitt.mtapi.domain.Card;


import com.zakpruitt.mtapi.domain.AuditModel;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class CardParent extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Card Name is required.")
    private String cardName;
    @NotEmpty(message = "Card Lore is required.")
    private String cardLore;
    @NotNull
    private String type;
    @NotNull
    private String rarity;
    @NotNull
    private String clan;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "card_id", referencedColumnName = "id")
    List<CardEffect> cardEffects = new ArrayList<>();
    @NotNull
    private String cardDescription;
    @NotNull
    private String emberCost;
    @NotNull
    private String imageURL;
}

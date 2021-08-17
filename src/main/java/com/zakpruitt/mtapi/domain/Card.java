package com.zakpruitt.mtapi.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
public class Card extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

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
    @Nullable
    private List<String> cardEffects;
    @NotNull
    private String cardDescription;
    @NotNull
    private int emberCost;
    @NotNull
    private String imageURL;
}

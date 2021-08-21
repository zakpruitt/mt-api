package com.zakpruitt.mtapi.domain.Enemy;

import com.zakpruitt.mtapi.domain.AuditModel;
import com.zakpruitt.mtapi.domain.Effect;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

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
    @NotNull
    private String type;
    @NotNull
    private String imageURL;
    @NotNull
    private int damage;
    @NotNull
    private int health;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "enemy_id", referencedColumnName = "id")
    List<Ring> rings = new ArrayList<>();
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "enemy_id", referencedColumnName = "id")
    List<Effect> effects = new ArrayList<>();
}

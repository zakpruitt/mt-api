package com.zakpruitt.mtapi.domain.StatusEffect;

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
@Table(name="trigger_effect")
public class Trigger {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Trigger Name is required.")
    private String triggerName;
    @NotEmpty(message = "Trigger Description is required.")
    private String triggerDescription;
    @Nullable
    private String imageURL;
}

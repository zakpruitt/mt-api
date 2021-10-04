package com.zakpruitt.mtapi.config;

import com.zakpruitt.mtapi.domain.StatusEffect.CardEffect;
import com.zakpruitt.mtapi.repository.CardEffectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CardEffectConfig {

    @Autowired
    CardEffectRepository cardEffectRepository;
    @Value("${MTAPI.ENV}")
    private String env;

    @Bean
    CommandLineRunner cardEffectRunner(CardEffectRepository cardEffectRepository) {
        return args -> {
            if (env.equals("dev")) {
                CreateCardEffect("Ascend", "Move up a floor and to the back of that floor.", "Unit");
                CreateCardEffect("Descend", "Move down a floor and to the back of that floor.", "Unit");
                CreateCardEffect("Reform", "Return a defeated friendly unit to your hand. Enhance with Burnout 1, +5 Attack, +5 Health, and 0 Ember.", "Unit");
                CreateCardEffect("Sacrifice", "Kill a friendly unit (of the required type in order) to play this card.", "Unit");
                CreateCardEffect("Cultivate", "Increase the attack and health of the friendly unit with the lowest health by the Cultivate value", "Unit");
                CreateCardEffect("Attuned", "Multiply the effects of Magic Power by 5.", "Spell");
                CreateCardEffect("Consume", "Can only be played once per battle.", "Spell");
                CreateCardEffect("Extract", "Lose 1 Echo after you play this card. If you cannot, the card cannot be played.", "Spell");
                CreateCardEffect("Frozen", "A Frozen card is not discarded at end of turn. Frozen is removed when the card is played.", "Spell");
                CreateCardEffect("Infused", "When played, the current floor will gain 1 Echo.", "Spell");
                CreateCardEffect("Magic Power", "Increases the amount of damage dealt and health restored by spells.", "Spell");
                CreateCardEffect("Offering", "If discarded before the end of your turn, it will instead be played.", "Spell");
                CreateCardEffect("Permafrost", "Gain Frozen when drawn.", "Spell");
                CreateCardEffect("Piercing", "Damage dealt by this card ignores armor and shield.", "Spell");
                CreateCardEffect("Purge", "Once played, this card is removed from your deck for the rest of the run.", "Spell");
                CreateCardEffect("Pyrebound", "Can only be played in the Pyre Room and the floor below it.", "Spell");
                CreateCardEffect("Reserve", "Triggers if this card remains in hand at the end of turn.", "Spell");
                CreateCardEffect("Spellchain", "When played, a copy of this card will be added to hand. The copy costs +1 Ember and gains Purge.", "Spell");
                CreateCardEffect("Unplayable", "Card cannot be played.", "Spell");
                CreateCardEffect("Unpurgeable", "Card cannot be purged by shops or unstable vortexes.", "Spell");
                CreateCardEffect("X Cost", "Spend all of your remaining Ember. The amount spent magnifies the result.", "Spell");
            }
        };
    }

    private void CreateCardEffect(String name, String description, String type) {
        if (cardEffectRepository.findByCardEffectName(name) != null) return;

        CardEffect cardEffect = new CardEffect();
        cardEffect.setCardEffectName(name);
        cardEffect.setCardEffectDescription(description);
        cardEffect.setCardType(type);
        cardEffectRepository.save(cardEffect);
    }
}

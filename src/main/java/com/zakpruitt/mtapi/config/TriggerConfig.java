package com.zakpruitt.mtapi.config;

import com.zakpruitt.mtapi.domain.StatusEffect.Trigger;
import com.zakpruitt.mtapi.repository.TriggerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TriggerConfig {

    @Autowired
    TriggerRepository triggerRepository;
    @Value("${MTAPI.ENV}")
    private String env;

    @Bean
    CommandLineRunner triggerRunner(TriggerRepository triggerRepository) {
        return args -> {
            if (env.equals("dev")) {
                CreateTrigger("Action", "Triggers at the start of this unit's turn.", "https://static.wikia.nocookie.net/monster_train/images/c/ca/Status_Action.png/revision/latest?cb=20200903224019");
                CreateTrigger("Armored", "Triggers when Armor is added to this unit.", "https://static.wikia.nocookie.net/monster_train/images/f/f8/Status_Armored.png/revision/latest?cb=20210711234056");
                CreateTrigger("Eaten", "After the next round of combat, this unit will be eaten by the front unit without an Eaten ability.", "https://static.wikia.nocookie.net/monster_train/images/f/f0/Status_Eaten.png/revision/latest?cb=20200528181001");
                CreateTrigger("End of Turn", "Triggers at end of turn.", "https://static.wikia.nocookie.net/monster_train/images/4/48/Status_End_of_Turn.png/revision/latest?cb=20200528181003");
                CreateTrigger("Etch", "Triggers when a card is Consumed on this floor.", "https://static.wikia.nocookie.net/monster_train/images/5/5a/Status_Etch.png/revision/latest?cb=20210711234350");
                CreateTrigger("Extinguish", "Triggers on death.", "https://static.wikia.nocookie.net/monster_train/images/d/da/Status_Extinguish.png/revision/latest?cb=20200528181009");
                CreateTrigger("Gorge", "Triggers when this unit eats a Morsel unit.", "https://static.wikia.nocookie.net/monster_train/images/b/b2/Status_Gorge.png/revision/latest?cb=20200528181012");
                CreateTrigger("Harvest", "Triggers when a unit on this floor dies.", "https://static.wikia.nocookie.net/monster_train/images/3/3e/Status_Harvest.png/revision/latest?cb=20200528181013");
                CreateTrigger("Hatch", "Once all Shell is removed, the unit dies, triggering its Hatch ability. Apply the Egg unit's upgrades and stat changes to the summoned unit.", "https://static.wikia.nocookie.net/monster_train/images/e/ec/Status_Hatch.png/revision/latest?cb=20210711234531");
                CreateTrigger("Hellborne Harvest", "Triggers when one of your units dies on this floor.", "https://static.wikia.nocookie.net/monster_train/images/4/48/Status_Hellborne_Harvest.png/revision/latest?cb=20200904081059");
                CreateTrigger("Hunger", "Triggers when a unit with Eaten is summoned.", "https://static.wikia.nocookie.net/monster_train/images/f/f6/Status_Hunger.png/revision/latest?cb=20210329115942");
                CreateTrigger("Incant", "Triggers when you cast a spell on this floor.", "https://static.wikia.nocookie.net/monster_train/images/1/1b/Status_Incant.png/revision/latest?cb=20200528181015");
                CreateTrigger("Inspire", "Triggers when you gain 1 Echo on this floor, once per Echo.", "https://static.wikia.nocookie.net/monster_train/images/4/49/Status_Inspire.png/revision/latest?cb=20210711234716");
                CreateTrigger("Rally", "Triggers when you play a non-Morsel unit card on this floor.", "https://static.wikia.nocookie.net/monster_train/images/8/87/Status_Rally.png/revision/latest?cb=20200903212802");
                CreateTrigger("Rejuvenate", "Triggers when healed, even at full health.", "https://static.wikia.nocookie.net/monster_train/images/2/20/Status_Rejuvenate.png/revision/latest?cb=20200528181039");
                CreateTrigger("Resolve", "Triggers after combat.", "https://static.wikia.nocookie.net/monster_train/images/3/3e/Status_Resolve.png/revision/latest?cb=20200528181040");
                CreateTrigger("Revenge", "Triggers when damaged.", "https://static.wikia.nocookie.net/monster_train/images/6/69/Status_Revenge.png/revision/latest?cb=20200528181041");
                CreateTrigger("Slay", "Triggers after dealing a killing blow.", "https://static.wikia.nocookie.net/monster_train/images/a/a3/Status_Slay.png/revision/latest?cb=20200528181045");
                CreateTrigger("Strike", "Triggers when attacking.", "https://static.wikia.nocookie.net/monster_train/images/f/f4/Status_Strike.png/revision/latest?cb=20200528181051");
                CreateTrigger("Summon", "Triggers when played.", "");
            }
        };
    }

    private void CreateTrigger(String name, String description, String imageURL) {
        if (triggerRepository.findByTriggerName(name) != null) return;

        Trigger trigger = new Trigger();
        trigger.setTriggerName(name);
        trigger.setTriggerDescription(description);
        trigger.setImageURL(imageURL);
        triggerRepository.save(trigger);
    }
}

package com.zakpruitt.mtapi.config;

import com.zakpruitt.mtapi.domain.StatusEffect.Debuff;
import com.zakpruitt.mtapi.repository.DebuffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DebuffConfig {

    @Autowired
    DebuffRepository debuffRepository;
    @Value("${MTAPI.ENV}")
    private String env;

    @Bean
    CommandLineRunner debuffRunner(DebuffRepository debuffRepository) {
        return args -> {
            if (env.equals("dev")) {
                CreateDebuff("Dazed", "As long as a unit is Dazed, it can't attack or use any Action or Resolve abilities.", "https://static.wikia.nocookie.net/monster_train/images/9/92/Status_Dazed.png/revision/latest?cb=20200528181000");
                CreateDebuff("Emberdrain", "At the start of turn, you lose 1 Ember for every stack of Emberdrain. Decreases every turn.", "https://static.wikia.nocookie.net/monster_train/images/8/88/Status_Emberdrain.png/revision/latest?cb=20200528181002");
                CreateDebuff("Frostbite", "Unit takes 1 damage per stack at the end of turn. Decreases every turn.", "https://static.wikia.nocookie.net/monster_train/images/8/86/Status_Frostbite.png/revision/latest?cb=20200528181009");
                CreateDebuff("Heartless", "A unit with Heartless cannot be healed.", "https://static.wikia.nocookie.net/monster_train/images/4/45/Status_Heartless.png/revision/latest?cb=20200528181013");
                CreateDebuff("Melee Weakness", "The next time the unit is attacked, it takes that much damage again for each stack of Melee Weakness.", "https://static.wikia.nocookie.net/monster_train/images/e/e8/Status_Melee_Weakness.png/revision/latest?cb=20200903212801");
                CreateDebuff("Reap", "Unit takes 1 damage per stack of Echo after combat ends. Does not decrease.", "https://static.wikia.nocookie.net/monster_train/images/f/f9/Status_Reap.png/revision/latest?cb=20210711235438");
                CreateDebuff("Rooted", "Prevents the next time a unit would move between floors. Bosses are immune.", "https://static.wikia.nocookie.net/monster_train/images/a/a1/Status_Rooted.png/revision/latest?cb=20200528181042");
                CreateDebuff("Sap", "-2 Attack per stack. Decreases every turn.", "https://static.wikia.nocookie.net/monster_train/images/f/fa/Status_Sap.png/revision/latest?cb=20200528181043");
                CreateDebuff("Spell Weakness", "The next time the unit is hit by a damage spell, it takes that much damage again for each stack of Spell Weakness.", "https://static.wikia.nocookie.net/monster_train/images/7/71/Status_Spell_Weakness.png/revision/latest?cb=20200528181048");
            }
        };
    }

    private void CreateDebuff(String name, String description, String imageURL) {
        if (debuffRepository.findByDebuffName(name) != null) return;

        Debuff debuff = new Debuff();
        debuff.setDebuffName(name);
        debuff.setDebuffDescription(description);
        debuff.setImageURL(imageURL);
        debuffRepository.save(debuff);
    }
}
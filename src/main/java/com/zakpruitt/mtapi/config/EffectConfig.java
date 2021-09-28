package com.zakpruitt.mtapi.config;

import com.zakpruitt.mtapi.domain.Enemy.Enemy;
import com.zakpruitt.mtapi.domain.StatusEffect.Effect;
import com.zakpruitt.mtapi.repository.EffectRepository;
import com.zakpruitt.mtapi.repository.EnemyRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Configuration
public class EffectConfig {

    @Value("${MTAPI.ENV}")
    private String env;

    @Autowired
    EffectRepository effectRepository;

    @Bean
    CommandLineRunner enemyRunner(EffectRepository effectRepository) {
        return args -> {
            if (env.equals("dev")) {
                CreateEffect("Armor", "Damage is dealt to Armor before health. Each point of Armor blocks one point of damage and is then removed.", "https://static.wikia.nocookie.net/monster_train/images/b/b0/Status_Armor.png/revision/latest?cb=20200528180947");
                CreateEffect("Buffet", "This unit can be eaten multiple times.", "https://static.wikia.nocookie.net/monster_train/images/1/13/Status_Buffet.png/revision/latest?cb=20200903212759");
                CreateEffect("Burnout", "Counts down every turn. When Burnout runs out, the unit dies.", "https://static.wikia.nocookie.net/monster_train/images/1/12/Status_Burnout.png/revision/latest?cb=20200528180955");
                CreateEffect("Cardless", "This unit was not summoned from a unit card. It won't go to the Consume pile and can't gain Endless.", "https://static.wikia.nocookie.net/monster_train/images/c/c8/Status_Cardless.png/revision/latest?cb=20200528180956");
                CreateEffect("Enchant", "Other friendly units on the same floor gain a bonus.", "https://static.wikia.nocookie.net/monster_train/images/8/83/Status_Enchant.png/revision/latest?cb=20200529183853");
                CreateEffect("Endless", "When this unit dies, return its card to the top of the draw pile.", "https://static.wikia.nocookie.net/monster_train/images/8/86/Status_Endless.png/revision/latest?cb=20200528181004");
                CreateEffect("Fragile", "If this unit loses health, it dies.", "https://static.wikia.nocookie.net/monster_train/images/c/c7/Status_Fragile.png/revision/latest?cb=20200721172246");
                CreateEffect("Fuel", "Negates the effect of Inert. Loses 1 stack every turn.", "https://static.wikia.nocookie.net/monster_train/images/e/ee/Status_Fuel.png/revision/latest?cb=20200528181011");
                CreateEffect("Haste", "Moves directly from the first floor to the third floor.", "https://static.wikia.nocookie.net/monster_train/images/e/ea/Status_Haste.png/revision/latest?cb=20200529183842");
                CreateEffect("Immobile", "Can't move between floors or change position on this floor.", "https://static.wikia.nocookie.net/monster_train/images/f/fd/Status_Immobile.png/revision/latest?cb=20200528181014");
                CreateEffect("Inert", "This unit cannot attack unless it has Fuel.", "https://static.wikia.nocookie.net/monster_train/images/b/b9/Status_Inert.png/revision/latest?cb=20200528181016");
                CreateEffect("Multistrike", "Attacks an additional time each turn.", "https://static.wikia.nocookie.net/monster_train/images/9/99/Status_Multistrike.png/revision/latest?cb=20200528181023");
                CreateEffect("Phased", "Cannot attack. Cannot be damaged or targeted in any way.", "https://static.wikia.nocookie.net/monster_train/images/5/57/Status_Phased.png/revision/latest?cb=20200903224419");
                CreateEffect("Purify", "After a debuff deals damage to this unit, remove that debuff.", "https://static.wikia.nocookie.net/monster_train/images/5/54/Status_Purify.png/revision/latest?cb=20210711232927");
                CreateEffect("Quick", "Attacks before enemy units during combat.", "https://static.wikia.nocookie.net/monster_train/images/4/47/Status_Quick.png/revision/latest?cb=20200528181023");
                CreateEffect("Relentless", "Combat in this room continues until all enemies are defeated. This unit cannot be rooted.", "https://static.wikia.nocookie.net/monster_train/images/9/94/Status_Relentless.png/revision/latest?cb=20200528181040");
                CreateEffect("Shard", "Powers Solgard the Martyr's abilities.", "https://static.wikia.nocookie.net/monster_train/images/6/6e/Status_Shard.png/revision/latest?cb=20200903223643");
                CreateEffect("Shell", "At end of turn, remove Echo from the floor to remove Shell from the front unit with Shell. When all Shell is removed, the unit will Hatch.", "https://static.wikia.nocookie.net/monster_train/images/d/db/Status_Shell.png/revision/latest?cb=20210711233310");
                CreateEffect("Silence", "Disables Triggered Abilities.", "https://static.wikia.nocookie.net/monster_train/images/6/64/Status_Silence.png/revision/latest?cb=20200528181044");
                CreateEffect("Sweep", "Attacks all enemy units.", "https://static.wikia.nocookie.net/monster_train/images/2/2d/Status_Sweep.png/revision/latest?cb=20200528181052");
                CreateEffect("Trample", "When attacking, excess damage is applied to the subsequent enemy unit.", "https://static.wikia.nocookie.net/monster_train/images/b/b1/Status_Trample.png/revision/latest?cb=20200618030918");
            }
        };
    };

    private void CreateEffect(String name, String description, String imageURL) {
        Effect effect = new Effect();
        effect.setEffectName(name);
        effect.setEffectDescription(description);
        effect.setImageURL(imageURL);
        effectRepository.save(effect);
    }
}

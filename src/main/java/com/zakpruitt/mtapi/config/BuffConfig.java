package com.zakpruitt.mtapi.config;

import com.zakpruitt.mtapi.domain.StatusEffect.Buff;
import com.zakpruitt.mtapi.domain.StatusEffect.Effect;
import com.zakpruitt.mtapi.repository.BuffRepository;
import com.zakpruitt.mtapi.repository.EffectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BuffConfig {

    @Value("${MTAPI.ENV}")
    private String env;

    @Autowired
    BuffRepository buffRepository;

    @Bean
    CommandLineRunner buffRunner(EffectRepository effectRepository) {
        return args -> {
            if (env.equals("dev")) {
                CreateBuff("Damage Shield", "Nullify the next source of damage.", "https://static.wikia.nocookie.net/monster_train/images/a/a5/Status_Damage_Shield.png/revision/latest?cb=20200528180957");
                CreateBuff("Lifesteal", "When a unit with Lifesteal attacks, it restores health equal to the damage dealt.", "https://static.wikia.nocookie.net/monster_train/images/c/ca/Status_Lifesteal.png/revision/latest?cb=20200528181017");
                CreateBuff("Rage", "+2 Attack per stack. Decreases every turn.", "https://static.wikia.nocookie.net/monster_train/images/3/37/Status_Rage.png/revision/latest?cb=20200528181024");
                CreateBuff("Regen", "Restores 1 health per stack at the end of turn. Decreases every turn.", "https://static.wikia.nocookie.net/monster_train/images/2/23/Status_Regen.png/revision/latest?cb=20200528181026");
                CreateBuff("Soul", "Modifies damage of Devourer of Death's Extinguish ability.", "https://static.wikia.nocookie.net/monster_train/images/6/6c/Status_Soul.png/revision/latest?cb=20200528181046");
                CreateBuff("Spell Shield", "Absorbs the next damage spell.", "https://static.wikia.nocookie.net/monster_train/images/e/e1/Status_Spell_Shield.png/revision/latest?cb=20200528181047");
                CreateBuff("Spikes", "When a unit with Spikes is attacked, the attacker takes 1 damage per stack.", "https://static.wikia.nocookie.net/monster_train/images/8/81/Status_Spikes.png/revision/latest?cb=20200528181049");
                CreateBuff("Stealth", "not a target in combat. Loses 1 stack every turn.", "https://static.wikia.nocookie.net/monster_train/images/5/51/Status_Stealth.png/revision/latest?cb=20200528181050");
            }
        };
    };

    private void CreateBuff(String name, String description, String imageURL) {
        if (buffRepository.findByBuffName(name) != null) return;

        Buff buff = new Buff();
        buff.setBuffName(name);
        buff.setBuffDescription(description);
        buff.setImageURL(imageURL);
        buffRepository.save(buff);
    }
}


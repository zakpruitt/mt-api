package com.zakpruitt.mtapi.config;

import com.zakpruitt.mtapi.repository.CreatureCardRepository;
import com.zakpruitt.mtapi.repository.SpellCardRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.net.URL;

@Configuration
public class CardConfig {

    @Bean
    CommandLineRunner commandLineRunner(CreatureCardRepository creatureCardRepository, SpellCardRepository spellCardRepository) {
        return args -> {
            for (int i = 0; i < 29; i++) {
                URL url = new URL(String.format("https://ocffhwpt3b.execute-api.us-west-2.amazonaws.com/production/api/v1/cards?offset=%s&limit=10", i));

            }
        };
    }
}

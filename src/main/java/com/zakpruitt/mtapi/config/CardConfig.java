package com.zakpruitt.mtapi.config;

import com.zakpruitt.mtapi.domain.Card.CreatureCard;
import com.zakpruitt.mtapi.domain.Card.SpellCard;
import com.zakpruitt.mtapi.repository.CreatureCardRepository;
import com.zakpruitt.mtapi.repository.SpellCardRepository;
import com.zakpruitt.mtapi.utility.DescriptionParserUtility;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Configuration
public class CardConfig {

    @Value("${MTAPI.ENV}")
    private String env;

    @Autowired
    CreatureCardRepository creatureCardRepository;
    @Autowired
    SpellCardRepository spellCardRepository;

    @Bean
    CommandLineRunner commandLineRunner(CreatureCardRepository creatureCardRepository, SpellCardRepository spellCardRepository) {
        return args -> {
            if (env.equals("dev")) {
                System.out.println(env);
                for (int i = 1; i < 30; i++) {
                    // Create URL and request connection
                    URL url = new URL(String.format("https://ocffhwpt3b.execute-api.us-west-2.amazonaws.com/production/api/v1/cards?offset=%s&limit=10", i));
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestProperty("Content-Type", "application/json");
                    con.setRequestProperty("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MzExNDMzMjEsInBsYXRmb3JtIjoiUHVibGljIiwicGxhdGZvcm1Vc2VySWQiOiJQdWJsaWMiLCJzZXNzaW9uVGlja2V0IjpudWxsLCJyb3V0ZXMiOlsiY2FyZHMiLCJjaGFsbGVuZ2UiLCJjaGFsbGVuZ2VzIiwiY2xhbnMiLCJjb3ZlbmFudHMiLCJhcnRpZmFjdHMiLCJlbmVtaWVzIiwibGVhZGVyYm9hcmQiLCJtdXRhdG9ycyIsInNoYXJlcyIsInNpbnMiLCJwYXRjaG5vdGVzIl0sImJ1aWxkVmVyc2lvbiI6bnVsbH0.DtaBk0-QoWhs2Fc6KsiaDiLEnEe96jL3LG9ZnLRsq9U");
                    con.setRequestMethod("GET");

                    // Read response
                    int status = con.getResponseCode();
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuffer content = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        content.append(inputLine);
                    }
                    in.close();

                    // Parse to JSON
                    JSONObject cardJSON = new JSONObject(content.toString());

                    // Iterate over and create card objects
                    JSONArray cardArray = cardJSON.getJSONArray("cards");
                    for (int j = 0; j < cardArray.length(); j++) {
                        JSONObject card = cardArray.getJSONObject(j);
                        if (creatureCardRepository.findByCardName(card.getString("name")) != null) continue;
                        if (spellCardRepository.findByCardName(card.getString("name")) != null) continue;

                        switch (card.getString("cardType")) {
                            case "Monster":
                                CreatureCard newCreatureCard = new CreatureCard();
                                newCreatureCard.setCardName(card.getString("name"));
                                newCreatureCard.setCardLore(card.getString("lore"));
                                newCreatureCard.setEmberCost(card.getInt("cost"));
                                newCreatureCard.setSubtype(card.getString("cardSubType"));
                                newCreatureCard.setRarity(card.getInt("rarity"));
                                newCreatureCard.setHealth(card.getInt("health"));
                                newCreatureCard.setDamage(card.getInt("attack"));
                                newCreatureCard.setImageURL(card.getString("imageUrl"));
                                newCreatureCard.setType(card.getString("cardType"));
                                newCreatureCard.setDescription(DescriptionParserUtility.parseDescription(card.getString("description")));
                                creatureCardRepository.save(newCreatureCard);
                                break;
                            case "Spell":
                                SpellCard newSpellCard = new SpellCard();
                                newSpellCard.setCardName(card.getString("name"));
                                newSpellCard.setCardLore(card.getString("lore"));
                                newSpellCard.setEmberCost(card.getInt("cost"));
                                newSpellCard.setSubtype(card.getString("cardSubType"));
                                newSpellCard.setRarity(card.getInt("rarity"));
                                newSpellCard.setImageURL(card.getString("imageUrl"));
                                newSpellCard.setType(card.getString("cardType"));
                                newSpellCard.setDescription(DescriptionParserUtility.parseDescription(card.getString("description")));
                                spellCardRepository.save(newSpellCard);
                                break;
                        }
                    }
                    con.disconnect();
                }
            }
        };
    }
}

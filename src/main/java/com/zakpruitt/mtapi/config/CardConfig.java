package com.zakpruitt.mtapi.config;

import com.zakpruitt.mtapi.domain.Card.CreatureCard;
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
                    con.setRequestProperty("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MzEwNjY0MDMsInBsYXRmb3JtIjoiUHVibGljIiwicGxhdGZvcm1Vc2VySWQiOiJQdWJsaWMiLCJzZXNzaW9uVGlja2V0IjpudWxsLCJyb3V0ZXMiOlsiY2FyZHMiLCJjaGFsbGVuZ2UiLCJjaGFsbGVuZ2VzIiwiY2xhbnMiLCJjb3ZlbmFudHMiLCJhcnRpZmFjdHMiLCJlbmVtaWVzIiwibGVhZGVyYm9hcmQiLCJtdXRhdG9ycyIsInNoYXJlcyIsInNpbnMiLCJwYXRjaG5vdGVzIl0sImJ1aWxkVmVyc2lvbiI6bnVsbH0.-_LVxh-oYaq_tQG_I1zLLH_SCHOV5ZTzPOQuywVWFrs");
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
                                CreatureCard newCard = new CreatureCard();
                                newCard.setCardName(card.getString("name"));
                                newCard.setCardLore(card.getString("lore"));
                                newCard.setEmberCost(card.getInt("cost"));
                                newCard.setSubtype(card.getString("cardSubType"));
                                newCard.setRarity(card.getInt("rarity"));
                                newCard.setHealth(card.getInt("health"));
                                newCard.setDamage(card.getInt("attack"));
                                newCard.setImageURL(card.getString("imageUrl"));
                                newCard.setType(card.getString("cardType"));
                                newCard.setDescription(DescriptionParserUtility.parseDescription(card.getString("description")));
                                creatureCardRepository.save(newCard);
                                break;
                            case "Spell":
                                break;
                        }
                    }
                    con.disconnect();
                }
            }
        };
    }
}

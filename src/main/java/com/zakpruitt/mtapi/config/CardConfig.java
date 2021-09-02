package com.zakpruitt.mtapi.config;

import com.zakpruitt.mtapi.domain.Card.CreatureCard;
import com.zakpruitt.mtapi.repository.CreatureCardRepository;
import com.zakpruitt.mtapi.repository.SpellCardRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    CreatureCardRepository creatureCardRepository;
    @Autowired
    SpellCardRepository spellCardRepository;

    @Bean
    CommandLineRunner commandLineRunner(CreatureCardRepository creatureCardRepository, SpellCardRepository spellCardRepository) {
        return args -> {
            for (int i = 1; i < 30; i++) {
                // Create URL and request connection
                URL url = new URL(String.format("https://ocffhwpt3b.execute-api.us-west-2.amazonaws.com/production/api/v1/cards?offset=%s&limit=10", i));
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestProperty("Content-Type", "application/json");
                con.setRequestProperty("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MzA2NDAxMzMsInBsYXRmb3JtIjoiUHVibGljIiwicGxhdGZvcm1Vc2VySWQiOiJQdWJsaWMiLCJzZXNzaW9uVGlja2V0IjpudWxsLCJyb3V0ZXMiOlsiY2FyZHMiLCJjaGFsbGVuZ2UiLCJjaGFsbGVuZ2VzIiwiY2xhbnMiLCJjb3ZlbmFudHMiLCJhcnRpZmFjdHMiLCJlbmVtaWVzIiwibGVhZGVyYm9hcmQiLCJtdXRhdG9ycyIsInNoYXJlcyIsInNpbnMiLCJwYXRjaG5vdGVzIl0sImJ1aWxkVmVyc2lvbiI6bnVsbH0.e4VLNVjjIbYOTcdaIop4WGWFrkqYyOoxosk1yloZ864");
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
                for(int j = 0; j < cardArray.length(); j++){
                    JSONObject card = cardArray.getJSONObject(j);
                    if (creatureCardRepository.findByCardName(card.getString("name")) != null) continue;
                    if (spellCardRepository.findByCardName(card.getString("name")) != null ) continue;

                    if (card.getString("cardType").equals("Monster")) {
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
                        newCard.setDescription(parseDescription(card.getString("description")));

                        creatureCardRepository.save(newCard);
                    } else {

                    }
                }
                con.disconnect();
            }
        };
    }

    private String parseDescription(String description) {
        if (description.equals("")) return description;
        if (description.contains("<sprite")) {
            description = parseSpriteTags(description);
        }
        description = parseEscapeCharacters(description);
        description = parseHTMLTags(description);

        return description;
    }

    private String parseSpriteTags(String description) {
        Pattern regex = Pattern.compile("\\\"(.*?)\\\"");
        Matcher regexMatcher = regex.matcher(description);
        while (regexMatcher.find()) {
            for (int i = 0; i < regexMatcher.groupCount(); i++) {
                switch (regexMatcher.group(i).replaceAll("\"", "")) {
                    case "Attack":
                        description = description.replace("<sprite name=\"Attack\">", " Attack");
                        break;
                    case "Health":
                        description = description.replace("<sprite name=\"Health\">", " Health");
                        break;
                    case "ChargedEchoes":
                        description = description.replace("<sprite name=\"ChargedEchoes\">", " ChargedEchoes");
                        break;
                    case "Xcost":
                        description = description.replace("<sprite name=\"Xcost\">", " Xcost");
                        break;
                    case "Ember":
                        description = description.replace("<sprite name=\"Ember\">", " Ember");
                        break;
                    case "Gold":
                        description = description.replace("<sprite name=\"Gold\">", " Gold");
                        break;
                    case "Capacity":
                        description = description.replace("<sprite name=\"Capacity\">", " Capacity");
                        break;
                }
            }
        }
        return description;
    }

    private String parseEscapeCharacters(String description) {
        return description.replaceAll("(\\\\.)+", "");
    }

    public String parseHTMLTags(String description) {
        return description.replaceAll("<.+?>", "");
    }
}

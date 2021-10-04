package com.zakpruitt.mtapi.config;

import com.zakpruitt.mtapi.domain.Card.CreatureCard;
import com.zakpruitt.mtapi.domain.Card.SpellCard;
import com.zakpruitt.mtapi.domain.Clan;
import com.zakpruitt.mtapi.domain.Mutator;
import com.zakpruitt.mtapi.repository.ClanRepository;
import com.zakpruitt.mtapi.repository.MutatorRepository;
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

@Configuration
public class MutatorConfig {

    @Autowired
    MutatorRepository mutatorRepository;

    @Value("${MTAPI.ENV}")
    private String env;
    @Value("${TOKEN}")
    private String token;

    @Bean
    CommandLineRunner mutatorRunner(MutatorRepository mutatorRepository) {
        return args -> {
            if (env.equals("dev")) {
                for (int i = 1; i < 9; i++) {
                    // Create URL and request connection
                    URL url = new URL(String.format("https://ocffhwpt3b.execute-api.us-west-2.amazonaws.com/production/api/v1/mutators?offset=%s&limit=10", i));
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestProperty("Content-Type", "application/json");
                    con.setRequestProperty("Authorization", String.format("Bearer %s", token));
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
                    JSONObject mutatorJSON = new JSONObject(content.toString());

                    // Iterate over and create card objects
                    JSONArray mutatorArray = mutatorJSON.getJSONArray("mutators");
                    for (int j = 0; j < mutatorArray.length(); j++) {
                        JSONObject mutator = mutatorArray.getJSONObject(j);
                        if (mutatorRepository.findByMutatorName(mutator.getString("name")) != null) continue;

                        Mutator newMutator = new Mutator();
                        newMutator.setMutatorName(mutator.getString("name"));
                        newMutator.setMutatorDescription(mutator.getString("description"));
                        newMutator.setImageURL(mutator.getString("imageUrl"));
                        mutatorRepository.save(newMutator);
                    }
                    con.disconnect();
                }
            }
        };
    }
}

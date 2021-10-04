package com.zakpruitt.mtapi.config;

import com.zakpruitt.mtapi.domain.Clan;
import com.zakpruitt.mtapi.domain.Enemy;
import com.zakpruitt.mtapi.repository.ClanRepository;
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
public class ClanConfig {

    @Autowired
    ClanRepository clanRepository;
    @Value("${MTAPI.ENV}")
    private String env;
    @Value("${TOKEN}")
    private String token;

    @Bean
    CommandLineRunner clanRunner(ClanRepository clanRepository) {
        return args -> {
            if (env.equals("dev")) {
                // Create URL and request connection
                URL url = new URL("https://ocffhwpt3b.execute-api.us-west-2.amazonaws.com/production/api/v1/clans");
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
                JSONObject clanJSON = new JSONObject(content.toString());

                // Iterate over and create enemy objects
                JSONArray enemyArray = clanJSON.getJSONArray("clans");
                for (int i = 0; i < enemyArray.length(); i++) {
                    JSONObject clan = enemyArray.getJSONObject(i);
                    if (clanRepository.findByClanName(clan.getString("name")) != null) continue;

                    Clan newClan = new Clan();
                    newClan.setClanName(clan.getString("name"));
                    newClan.setClanDescription(clan.getString("description"));
                    newClan.setClanSubDescription(clan.getString("subclassDescription"));
                    newClan.setImageURL(clan.getString("imageUrl"));
                    clanRepository.save(newClan);
                }
                con.disconnect();
            }
        };
    }

}


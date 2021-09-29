package com.zakpruitt.mtapi.config;

import com.zakpruitt.mtapi.domain.Enemy;
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
public class EnemyConfig {

    @Value("${MTAPI.ENV}")
    private String env;
    @Value("${TOKEN}")
    private String token;

    @Autowired
    EnemyRepository enemyRepository;

    @Bean
    CommandLineRunner enemyRunner(EnemyRepository enemyRepository) {
        return args -> {
            if (env.equals("a")) {
                for (int i = 1; i < 7; i++) {
                    // Create URL and request connection
                    URL url = new URL(String.format("https://ocffhwpt3b.execute-api.us-west-2.amazonaws.com/production/api/v1/enemies?offset=%s&limit=10", i));
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
                    JSONObject cardJSON = new JSONObject(content.toString());

                    // Iterate over and create enemy objects
                    JSONArray enemyArray = cardJSON.getJSONArray("enemies");
                    for (int j = 0; j < enemyArray.length(); j++) {
                        JSONObject enemy = enemyArray.getJSONObject(j);
                        if (enemyRepository.findByEnemyName(enemy.getString("name")) != null) continue;

                        Enemy newEnemy = new Enemy();
                        newEnemy.setEnemyName(enemy.getString("name"));
                        newEnemy.setEnemyLore(enemy.getString("lore"));
                        newEnemy.setHealth(enemy.getInt("health"));
                        newEnemy.setDamage(enemy.getInt("attack"));
                        newEnemy.setImageURL(enemy.getString("imageUrl"));
                        enemyRepository.save(newEnemy);
                    }
                    con.disconnect();
                }
            }
        };
    };
}

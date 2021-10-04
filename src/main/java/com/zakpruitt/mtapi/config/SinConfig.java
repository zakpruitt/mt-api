package com.zakpruitt.mtapi.config;

import com.zakpruitt.mtapi.domain.Sin;
import com.zakpruitt.mtapi.repository.SinRepository;
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
public class SinConfig {

    @Autowired
    SinRepository sinRepository;

    @Value("${MTAPI.ENV}")
    private String env;
    @Value("${TOKEN}")
    private String token;

    @Bean
    CommandLineRunner sinRunner(SinRepository sinRepository) {
        return args -> {
            if (env.equals("dev")) {
                for (int i = 1; i < 5; i++) {
                    // Create URL and request connection
                    URL url = new URL(String.format("https://ocffhwpt3b.execute-api.us-west-2.amazonaws.com/production/api/v1/sins?offset=%s&limit=10", i));
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
                    JSONObject sinsJSON = new JSONObject(content.toString());

                    // Iterate over and create card objects
                    JSONArray sinsArray = sinsJSON.getJSONArray("sins");
                    for (int j = 0; j < sinsArray.length(); j++) {
                        JSONObject sin = sinsArray.getJSONObject(j);
                        if (sinRepository.findBySinName(sin.getString("name")) != null) continue;

                        Sin newSin = new Sin();
                        newSin.setSinName(sin.getString("name"));
                        newSin.setSinDescription(DescriptionParserUtility.parseDescription(sin.getString("description")));
                        newSin.setImageURL(sin.getString("imageUrl"));
                        sinRepository.save(newSin);
                    }
                    con.disconnect();
                }
            }
        };
    }
}

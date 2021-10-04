package com.zakpruitt.mtapi.config;

import com.zakpruitt.mtapi.domain.Artifact;
import com.zakpruitt.mtapi.repository.ArtifactRepository;
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
public class ArtifactConfig {

    @Autowired
    ArtifactRepository artifactRepository;
    @Value("${MTAPI.ENV}")
    private String env;
    @Value("${TOKEN}")
    private String token;

    @Bean
    CommandLineRunner artifactRunner(ArtifactRepository artifactRepository) {
        return args -> {
            if (env.equals("dev")) {
                for (int i = 1; i < 17; i++) {
                    // Create URL and request connection
                    URL url = new URL(String.format("https://ocffhwpt3b.execute-api.us-west-2.amazonaws.com/production/api/v1/artifacts?offset=%s&limit=10", i));
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

                    // Iterate over and create card objects
                    JSONArray artifactArray = cardJSON.getJSONArray("artifacts");
                    for (int j = 0; j < artifactArray.length(); j++) {
                        JSONObject artifact = artifactArray.getJSONObject(j);
                        if (artifactRepository.findByArtifactName(artifact.getString("name")) != null) continue;

                        Artifact newArtifact = new Artifact();
                        newArtifact.setArtifactName(artifact.getString("name"));
                        if (!artifact.get("lore").equals(null)) newArtifact.setArtifactLore(artifact.getString("lore"));
                        newArtifact.setImageURL(artifact.getString("imageUrl"));
                        newArtifact.setArtifactDescription(DescriptionParserUtility.parseDescription(artifact.getString("description")));
                        artifactRepository.save(newArtifact);
                    }
                    con.disconnect();
                }
            }
        };
    }
}
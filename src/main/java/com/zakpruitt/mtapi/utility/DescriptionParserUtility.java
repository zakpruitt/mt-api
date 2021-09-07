package com.zakpruitt.mtapi.utility;

import lombok.experimental.UtilityClass;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class DescriptionParserUtility {
    public String parseDescription(String description) {
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

    private String parseHTMLTags(String description) {
        return description.replaceAll("<.+?>", "");
    }
}

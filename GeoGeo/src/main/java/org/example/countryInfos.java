package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import static org.example.game.normalizeString;

public class countryInfos {
    public static ArrayList getInfos(String region,boolean b){
        try {
            Random rand = new Random();
            String country= (String) (listOfCountries.getList(region,b)).get(rand.nextInt(listOfCountries.getList(region,b).size() - 1));
            URL url=null;
            if (country.contains(" ")){
                url = new URL("https://restcountries.com/v3.1/name/" + country.substring(0, country.indexOf(" ")).toLowerCase());
            }
            else {
                url = new URL("https://restcountries.com/v3.1/name/" + country.toLowerCase());
            }


            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();
            try {

                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode rootNode = objectMapper.readTree(response.toString());
                String name = rootNode.path(0).path("name").path("common").asText();
                JsonNode nameNode = rootNode.path(0).path("capital").path(0);
                JsonNode continent = rootNode.path(0).path("continents").path(0);
                String currencyCode = rootNode.path(0).path("currencies").fieldNames().next();
                JsonNode currencyName = rootNode.path(0).path("currencies").path(currencyCode).path("name");
                String languageCode = rootNode.path(0).path("languages").fieldNames().next();
                JsonNode languageName= rootNode.path(0).path("languages").path(languageCode);
                JsonNode nameFr = rootNode.path(0).path("translations").path("fra").path("common");
                JsonNode carSide = rootNode.path(0).path("car").path("side");
                JsonNode Population =rootNode.path(0).path("population");
                JsonNode flag =rootNode.path(0).path("flags").path("png");

                ArrayList infos=new ArrayList(11);
                infos.add(name);
                infos.add(nameNode.asText());
                infos.add(continent.asText());

                infos.add(currencyCode);
                infos.add(currencyName.asText());

                infos.add(languageCode.toLowerCase());
                infos.add(languageName.asText());

                infos.add(nameFr.asText());
                infos.add(carSide.asText());
                infos.add(Population.asText());
                infos.add(flag.asText());

                return infos;
            } catch (Exception e) {
                e.printStackTrace();
            }

            connection.disconnect();
        }
        catch(Exception a){
            a.printStackTrace();
        }
        return null;
    }
}

package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class listOfCountries {
    public static ArrayList getList(String region,boolean b) {
        try {
            URL url=null;
            if (b){
                 url= new URL("https://restcountries.com/v3.1/region/"+region.toLowerCase().replace(" ","%20"));
            }
            else {
                url= new URL("https://restcountries.com/v3.1/all");
            }


            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(connection.getInputStream());

            List<String> countryNames = new ArrayList<>();
            Iterator<JsonNode> countriesIterator = rootNode.elements();/**/
            while (countriesIterator.hasNext()) {
                JsonNode countryNode = countriesIterator.next();
                String name = countryNode.path("name").path("common").asText();
                countryNames.add(name);
            }

            String[] countriesArray = countryNames.toArray(new String[0]);
            
            connection.disconnect();
            return (ArrayList) countryNames;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

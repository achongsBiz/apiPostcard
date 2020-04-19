package acs.api_example.dao;

import acs.api_example.model.Gif;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class GiphyDAO {

    private String searchTerm;

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    @Value("${giphy.api.key}")
    private String apiId;

    @Value("${giphy.api.url}")
    private String apiURL;

    public List<Gif> getGifs() {
        HttpEntity<String> httpEntity = new HttpEntity<>(new HttpHeaders());
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(buildRequestURL(this.searchTerm, this.apiId, this.apiURL), HttpMethod.GET, httpEntity, String.class);
        return gifEntryConverter(response);

    }

    public String buildRequestURL(String word, String apiId, String apiURL) {

        String url = apiURL + "api_key=" + apiId + "&q=" + word + "&limit=3";
        return url;
    }

    private List<Gif> gifEntryConverter(ResponseEntity<String> response) {

        ObjectMapper objectMapper = new ObjectMapper();
        List<Gif> gifEntriesList = new ArrayList<>();

        try {
            JsonNode jsonNode = objectMapper.readTree(response.getBody());

            if(jsonNode.path("data").size() > 0) {
                for (int i = 0; i < 3; i++) {
                    Gif gif = new Gif(jsonNode.path("data").path(i).path("images").path("fixed_width").path("url").asText());
                    gifEntriesList.add(gif);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return gifEntriesList;
    }
}

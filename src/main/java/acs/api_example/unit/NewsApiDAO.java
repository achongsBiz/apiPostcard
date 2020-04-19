package acs.api_example.unit;

import acs.api_example.model.Article;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Component
public class NewsApiDAO {

    private String searchTerm;

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    @Value("${news.api.key}")
    private String apiId;

    @Value("${news.api.url}")
    private String apiURL;

    public List<Article> getArticles() {

        ResponseEntity<String> response = makeRequest(this.searchTerm, this.apiURL, this.apiId);
        return articleEntryConverter(response);

    }

    public ResponseEntity<String> makeRequest(String searchTerm, String apiURL, String apiId) {

        HttpEntity<String> httpEntity = new HttpEntity<>(new HttpHeaders());
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(buildRequestURL(searchTerm, apiURL, apiId), HttpMethod.GET, httpEntity, String.class);

    }


    public String buildRequestURL(String word, String apiURL, String apiId) {

        LocalDate today = LocalDate.now();
        LocalDate lowerBoundDate = today.minusWeeks(4);
        String lowerBoundDateStr = lowerBoundDate.getYear() + "-" + lowerBoundDate.getMonth() + "-" + lowerBoundDate.getDayOfMonth();
        String url = apiURL + "apiKey=" + apiId + "&qInTitle=" + word + "&from=" + lowerBoundDateStr + "&language=en";

        return url;
    }

    public List<Article> articleEntryConverter(ResponseEntity<String> response) {

        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<Article> articlesList = new ArrayList<>();
        try {

            JsonNode jsonNode = objectMapper.readTree(response.getBody());

            if (!jsonNode.path("totalResults").asText().equals(0)) {

                int upperBound = jsonNode.path("articles").size() < 5 ? jsonNode.path("articles").size() : 5;

                if (upperBound > 0) {
                    for (int i = 0; i < upperBound; i++) {

                        String title = jsonNode.path("articles").path(i).path("title").asText();
                        String link = jsonNode.path("articles").path(i).path("url").asText();
                        String author = jsonNode.path("articles").path(i).path("author").asText();

                        Article article = new Article(title, author, link);
                        articlesList.add(article);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return articlesList;
    }
}


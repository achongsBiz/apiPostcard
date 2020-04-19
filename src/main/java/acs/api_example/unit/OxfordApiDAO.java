package acs.api_example.unit;

import acs.api_example.model.LexicalEntry;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class OxfordApiDAO {

    private String searchTerm;

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    @Value("${oxford.api.id}")
    private String apiId;

    @Value("${oxford.api.key}")
    private String apiKey;

    @Value("${oxford.api.url}")
    private String apiURL;

    public List<LexicalEntry> getDefinitions() {

        List<LexicalEntry> listOfDefinitions = new ArrayList<>();

        try {

            ResponseEntity<String> response = makeRequest(this.searchTerm, this.apiURL, this.apiId);
            listOfDefinitions = lexicalEntryConverter(response);
        } catch (HttpClientErrorException e) {
            System.out.println("word not found.");
        } finally {
            return listOfDefinitions;
        }
    }

    public ResponseEntity<String> makeRequest(String searchTerm, String apiURL, String apiId) {

        HttpEntity<String> httpEntity = new HttpEntity<>(buildRequestHeader("application/json", "f4f29384", "a6c6d4cd83d8d9ae28888094551afb6e"));
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(buildRequestURL(searchTerm, apiURL), HttpMethod.GET, httpEntity, String.class);

    }


    public HttpHeaders buildRequestHeader(String contentType, String app_id, String app_key) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", contentType);
        httpHeaders.set("app_id", app_id);
        httpHeaders.set("app_key", app_key);

        return httpHeaders;
    }

    public String buildRequestURL(String word, String apiURL) {

        String url = apiURL + word;
        return url;
    }

    public List<LexicalEntry> lexicalEntryConverter(ResponseEntity<String> response) {

        ObjectMapper objectMapper = new ObjectMapper();
        List<LexicalEntry> lexicalEntriesList = new ArrayList<>();

        try {

            JsonNode jsonNode = objectMapper.readTree(response.getBody());

            int maxSpeechCategories = jsonNode.path("results").path(0).path("lexicalEntries").size();
            for (int i = 0; i < maxSpeechCategories; i++) {
                LexicalEntry lexicalEntry = new LexicalEntry(jsonNode.path("results")
                        .path(0)
                        .path("lexicalEntries")
                        .path(i)
                        .path("lexicalCategory")
                        .path("text")
                        .asText()
                        , this.searchTerm);

                int maxDefinitions = jsonNode.path("results")
                        .path(0)
                        .path("lexicalEntries")
                        .path(i).path("entries")
                        .path(0).path("senses").size();

                for (int j = 0; j < maxDefinitions; j++) {

                    JsonNode testNode = jsonNode.path("results").path(0).path("lexicalEntries")
                            .path(i)
                            .path("entries")
                            .path(0)
                            .path("senses")
                            .path(j)
                            .path("definitions")
                            .path(0);

                    // Check is required to prevent unpredictable "non-definition" entries in the same spot.
                    // This happens only rarely.

                    if (testNode.asText().isEmpty())
                        continue;

                    lexicalEntry.addDefinition(jsonNode.path("results").path(0).path("lexicalEntries")
                            .path(i)
                            .path("entries")
                            .path(0)
                            .path("senses")
                            .path(j)
                            .path("definitions")
                            .path(0).asText());
                }
                lexicalEntriesList.add(lexicalEntry);

            }
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lexicalEntriesList;
    }
}

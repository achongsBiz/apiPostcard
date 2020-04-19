package acs.api_example.integration;

import acs.api_example.model.Article;
import acs.api_example.model.Gif;
import acs.api_example.unit.GiphyDAO;
import acs.api_example.unit.NewsApiDAO;
import acs.api_example.unit.OxfordApiDAO;
import acs.api_example.model.LexicalEntry;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class IntegrationTest {

    @Mock
    OxfordApiDAO oxfordApiDAOMock;

    @Mock
    NewsApiDAO newsApiDAO;

    @Mock
    GiphyDAO giphyDAO;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void parseOxfordApiResponse() throws IOException {

        // Test file stored in resources folder.
        String mockBody = readTestFile("oxfordAPI");
        ResponseEntity<String> mockResponseEntity = new ResponseEntity(mockBody, new HttpHeaders(), HttpStatus.OK);

        // The exchange of data with the API is mocked:
        when(oxfordApiDAOMock.makeRequest("watch", "test","test")).thenReturn(mockResponseEntity);
        ResponseEntity<String> mockResponse = oxfordApiDAOMock.makeRequest("watch", "test", "test");

        // Processing of the response will be done using the actual production function:
        when(oxfordApiDAOMock.lexicalEntryConverter(mockResponse)).thenCallRealMethod();
        List<LexicalEntry> testResults = oxfordApiDAOMock.lexicalEntryConverter(mockResponse);

        assertEquals(2, testResults.size());
        assertEquals(3, testResults.get(0).getDefinitions().size());
        assertEquals(4, testResults.get(1).getDefinitions().size());

    }

    @Test
    public void parseNewsApiResponse() throws IOException {

        String mockBody = readTestFile("newsAPI");
        ResponseEntity<String> mockResponseEntity = new ResponseEntity(mockBody, new HttpHeaders(), HttpStatus.OK);

        // The exchange of data with the API is mocked:
        when(newsApiDAO.makeRequest("vaccine", "test","test")).thenReturn(mockResponseEntity);
        ResponseEntity<String> mockResponse = newsApiDAO.makeRequest("vaccine", "test", "test");

        // Processing of the response will be done using the actual production function:
        when(newsApiDAO.articleEntryConverter(mockResponse)).thenCallRealMethod();
        List<Article> testResults = newsApiDAO.articleEntryConverter(mockResponse);

        assertEquals(5, testResults.size());
    }

    @Test
    public void parseGiphyApiResponse() throws IOException {

        String mockBody = readTestFile("giphyAPI");
        ResponseEntity<String> mockResponseEntity = new ResponseEntity(mockBody, new HttpHeaders(), HttpStatus.OK);

        // The exchange of data with the API is mocked:
        when(giphyDAO.makeRequest("vaccine", "test","test")).thenReturn(mockResponseEntity);
        ResponseEntity<String> mockResponse = giphyDAO.makeRequest("vaccine", "test", "test");

        // Processing of the response will be done using the actual production function:
        when(giphyDAO.gifEntryConverter(mockResponse)).thenCallRealMethod();
        List<Gif> testResults = giphyDAO.gifEntryConverter(mockResponse);

        assertEquals(3, testResults.size());


    }


    public String readTestFile(String whichAPI) throws IOException {

        String filePath = "";
        String content = "";

        if (whichAPI == "oxfordAPI") {
             filePath = "../apiPostcard/src/test/resources/oxfordAPITestResponse.json";
             content = new String(Files.readAllBytes(Paths.get(filePath)));
        }
        else if (whichAPI == "newsAPI") {
             filePath = "../apiPostcard/src/test/resources/newsAPITestResponse.json";
             content = new String(Files.readAllBytes(Paths.get(filePath)));
        }
        else {
            filePath = "../apiPostcard/src/test/resources/giphyAPITestResponse.json";
            content = new String(Files.readAllBytes(Paths.get(filePath)));
        }


        return content;
    }
}
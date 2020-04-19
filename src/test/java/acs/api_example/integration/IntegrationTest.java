package acs.api_example.integration;

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

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void parseOxfordApiResonse() throws IOException {

        // Test file stored in resources folder.
        String mockBody = readTestFile();
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

    public String readTestFile() throws IOException {

        String filePath = "../apiPostcard/src/test/resources/oxfordAPITestResponse.json";
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        return content;
    }
}
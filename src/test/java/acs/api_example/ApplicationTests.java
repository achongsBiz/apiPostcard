package acs.api_example;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;


public class ApplicationTests {

//    @Mock
//    Oxford oxfordMock;
//
//    @Before
//    public void setUp() throws Exception {
//
//        MockitoAnnotations.initMocks(this);
//    }

//@Ignore
//    @Test
//    public void unitTest() throws IOException {

//        String mockBody = readTestFile();
//        ResponseEntity<String> mockResponseEntity = new ResponseEntity(mockBody, any(), HttpStatus.OK);
//        when(oxfordMock.getDefinitions("watch")).thenReturn(mockResponseEntity);
//        ResponseEntity<String> mockResponse = oxfordMock.getDefinitions("watch");
//        OxfordToEntryConverter oxfordToEntryConverter = new OxfordToEntryConverter(mockResponse, "watch");
//
//        assertThat(oxfordToEntryConverter.lexicalEntriesList.size()).isEqualTo(2);
//        assertThat(oxfordToEntryConverter.lexicalEntriesList.get(0).getPartOfSpeech()).isEqualTo("\"Verb\"");
//        assertThat(oxfordToEntryConverter.lexicalEntriesList.get(0).getDefinitions().size()).isEqualTo(3);
//        assertThat(oxfordToEntryConverter.lexicalEntriesList.get(1).getPartOfSpeech()).isEqualTo("\"Noun\"");
//        assertThat(oxfordToEntryConverter.lexicalEntriesList.get(1).getDefinitions().size()).isEqualTo(4);
//    }
//
//    public String readTestFile() throws IOException {
//
//        String filePath = "../api_example/src/test/resources/TestResponse.json";
//        String content = new String(Files.readAllBytes(Paths.get(filePath)));
//        return content;
//    }
}
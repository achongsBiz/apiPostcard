package acs.api_example.dao;

import org.junit.Test;
import org.springframework.http.HttpHeaders;

import static org.junit.Assert.*;

public class OxfordApiDAOTest {

    OxfordApiDAO oxfordApiDAO = new OxfordApiDAO();

    @Test
    public void buildRequestURL_ut() {

        String actualResult = oxfordApiDAO.buildRequestURL("lightbulb","https://od-api.oxforddictionaries.com/api/v2/entries/en/");
        String expectedResult = "https://od-api.oxforddictionaries.com/api/v2/entries/en/lightbulb";
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void buildHeaders_ut() {

        HttpHeaders actualHeader= oxfordApiDAO.buildRequestHeader("application/json", "AY345", "JJKE");

        boolean keysAreCorrect = true;

        if(!actualHeader.containsKey("Content-Type") || !actualHeader.containsKey("app_id") || !actualHeader.containsKey("app_key")) {
            keysAreCorrect = false;
        }

        assertTrue(keysAreCorrect);

    }
}
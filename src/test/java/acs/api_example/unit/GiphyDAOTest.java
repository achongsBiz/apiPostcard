package acs.api_example.unit;

import org.junit.Test;

import static org.junit.Assert.*;

public class GiphyDAOTest {

    GiphyDAO giphyDAO = new GiphyDAO();

    @Test
    public void giphyDAO_ut() {

        String actualResult = giphyDAO.buildRequestURL("lightbulb", "GH3234","https://api.giphy.com/v1/gifs/search?");
        String expectedResult = "https://api.giphy.com/v1/gifs/search?api_key=GH3234&q=lightbulb&limit=3";
        assertEquals(expectedResult, actualResult);
    }

}
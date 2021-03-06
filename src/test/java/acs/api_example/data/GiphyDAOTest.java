package acs.api_example.data;

import org.junit.Test;

import static org.junit.Assert.*;

public class GiphyDAOTest {

    GiphyDAO giphyDAO = new GiphyDAO();

    @Test
    public void giphyDAO_ut() {

        String actualResult = giphyDAO.buildRequestURL("lightbulb", "https://api.giphy.com/v1/gifs/search?","GH3234");
        String expectedResult = "https://api.giphy.com/v1/gifs/search?api_key=GH3234&q=lightbulb&limit=5";
        assertEquals(expectedResult, actualResult);
    }

}
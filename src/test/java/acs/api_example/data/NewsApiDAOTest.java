package acs.api_example.data;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class NewsApiDAOTest {

    NewsApiDAO newsApiDAO = new NewsApiDAO();

    @Test
    public void buildRequestURL_ut() {

        String actualResult = newsApiDAO.buildRequestURL("lightbulb", "https://newsapi.org/v2/everything?","AX13456");

        LocalDate today = LocalDate.now();
        LocalDate lowerBoundDate = today.minusWeeks(4);
        String lowerBoundDateStr = lowerBoundDate.getYear() + "-" + lowerBoundDate.getMonth() + "-" + lowerBoundDate.getDayOfMonth();

        String expectedResult = "https://newsapi.org/v2/everything?apiKey=AX13456&qInTitle=lightbulb&from=" + lowerBoundDateStr + "&language=en";
        assertEquals(expectedResult, actualResult);
    }



}
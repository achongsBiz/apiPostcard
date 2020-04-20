# Postcards from the Internet

## Introduction
This application takes a search input from the user and displays matching results from three different API's. It will display a dictionary definition for the word searched, a list of matching gif images, and current news articles relevant to the search term.

## Architecture

* Data:
    * Giphy API: Data source used for gif results.
    * News API: Data source used for news results.
    * Oxford API: Data source used for dictionary results.

* Server: 
    * SpringBoot (in Java)
    * Gradle
* Front End: 
    * JSP Views
    * JSTL Tag Library
* Testing:
    * Unit Tests through J-Unit.
    * Integration Testing through J-Unit and Mockito.

## Visuals
![screenshot](https://github.com/achongsBiz/readme-files/blob/master/api-postcards/sc1.png)

## Citations and Acknowledgements 
The free tier of the above mentioned API's were used.

* [Giphy API](https://developers.giphy.com/)
* [News API](https://newsapi.org/)
* [Oxford Dictionary API](https://developer.oxforddictionaries.com/)

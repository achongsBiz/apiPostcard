# apiDemo

This is a SpringBoot application that serves as an educational demonstration of the following concepts:
- Implementing a MVC-style application with SpringBoot and Thymeleaf.
- Interacting with an external API (in this case, the Oxford Dictionary Developer API:
https://developer.oxforddictionaries.com/
- Mocking with Mockito.

The application's controller codes for 1 endpoint: (/definition). The endpoint takes an optional parameter
"word," which should contain the word you are trying to query. With this parameter ommitted, the value of
word will default to "cat" within the application.

The HTML view will render all definitions and sort them by part of speech (noun, verb, etc.).

For example, invoking the following URL (http://localhost:8080/definition?word=store) will return all the 
definitions for the word "store" sorted by parts of speech.

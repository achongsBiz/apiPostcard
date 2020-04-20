package acs.api_example.controller;

import acs.api_example.unit.GiphyDAO;
import acs.api_example.unit.NewsApiDAO;
import acs.api_example.unit.OxfordApiDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    OxfordApiDAO oxfordDAO;

    @Autowired
    GiphyDAO giphyDAO;

    @Autowired
    NewsApiDAO newsApiDAO;

    @GetMapping(value="/postcard")
    public String definition(@RequestParam(name = "word", required = false, defaultValue = "shiba") String word, Model model) {

        oxfordDAO.setSearchTerm(word);
        model.addAttribute("definitions", oxfordDAO.getDefinitions());

        giphyDAO.setSearchTerm(word);
        model.addAttribute("gifs", giphyDAO.getGifs());

        newsApiDAO.setSearchTerm(word);
        model.addAttribute("articles", newsApiDAO.getArticles());

        model.addAttribute("currentSearch", word);

        return "definition";
    }

    @PostMapping(value="/postcard")
    public String handleFormSubmit(@RequestParam(name = "word") String word) {

        return "redirect:/postcard?word=" + word;
    }
}

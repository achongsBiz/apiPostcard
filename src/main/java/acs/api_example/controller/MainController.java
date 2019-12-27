package acs.api_example.controller;

import acs.api_example.dao.GiphyDAO;
import acs.api_example.dao.NewsApiDAO;
import acs.api_example.dao.OxfordApiDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    OxfordApiDAO oxfordDAO;

    @Autowired
    GiphyDAO giphyDAO;

    @Autowired
    NewsApiDAO newsApiDAO;

    @GetMapping(value="/hi")
    public String definition(@RequestParam(name = "word", required = false, defaultValue = "shiba") String word, Model model) {

        oxfordDAO.setSearchTerm(word);
        model.addAttribute("definitions", oxfordDAO.getDefinitions());

        giphyDAO.setSearchTerm(word);
        model.addAttribute("gifs", giphyDAO.getGifs());

        newsApiDAO.setSearchTerm(word);
        model.addAttribute("articles", newsApiDAO.getArticles());

        return "definition";
    }
}

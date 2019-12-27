package acs.api_example.model;

import java.util.ArrayList;
import java.util.List;

public class LexicalEntry {

    private String word;
    private String partOfSpeech;
    private List<String> definitions;

    public LexicalEntry(String partOfSpeech, String word) {
        this.word = word;
        this.partOfSpeech = partOfSpeech;
        definitions = new ArrayList<>();
    }

    public String getWord() {
        return this.word;
    }

    public String getPartOfSpeech() {
        return this.partOfSpeech;
    }

    public List<String> getDefinitions() {
        return this.definitions;
    }

    public void addDefinition(String definition) {
        definitions.add(definition);
    }
}

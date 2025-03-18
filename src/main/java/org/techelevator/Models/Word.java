package org.techelevator.Models;

import java.util.Arrays;
import java.util.List;

public class Word {

    private String word;
    private List<String> characters;

// Constructor
    public Word(String word) {
        this.word = word.toLowerCase();
        this.characters = Arrays.stream(word.split("")).toList();
    }

// Getters/Setters
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
        this.characters = Arrays.stream(word.split("")).toList(); //is this really needed?
    }

    public List<String> getCharacters() {
        return characters;
    }

// Methods
    @Override
    public String toString() {
        return this.getWord();
    }
}


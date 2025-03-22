package org.techelevator.Models;

import java.util.Arrays;
import java.util.List;

public class Acronym {

    private String acronym;
    private List<String> characters;
    private String meaning;

// Constructor
    public Acronym(String acronym, String meaning) {
        this.acronym = acronym;
        this.meaning = meaning;
        this.characters = Arrays.stream(acronym.split("")).toList();
    }

// Getters/Setters

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public List<String> getCharacters() {
        return characters;
    }

    public void setCharacters(List<String> characters) {
        this.characters = characters;
    }

    // Methods
    @Override
    public String toString() {
        return this.getAcronym();
    }

}

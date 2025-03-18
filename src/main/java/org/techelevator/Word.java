package org.techelevator;

import java.util.List;

public class Word {

    private String word;
    private List<Character> characters;

//************
//Constructor
//************
    public Word(String word) {
        this.word = word;
    }

//************
//Getters/Setters
//************

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }

//************
//Methods
//************

    @Override
    public String toString(){

        return null;
    }


}

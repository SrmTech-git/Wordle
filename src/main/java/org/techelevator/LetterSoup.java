package org.techelevator;

import org.techelevator.Services.APIWordService;
import org.techelevator.Services.WordService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class LetterSoup {

    public LetterSoup() {
    }

//*************
//   Methods
//*************
    List<String> guessedWords = new ArrayList<>();
    int pointCounter = 0;

    public void run(){

        List<Character> randomLetters = getRandomletters();
        System.out.println(randomLetters);
        String playerGuess = askForGuess();
        addWordToList(playerGuess);
        displayWordCount();

    }

    public List<Character> getRandomletters(){
        List<Character> randomLetters = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            char letter = (char) (random.nextInt(26) + 'a'); // Generates a random letter (a-z)
            randomLetters.add(letter); // Add the letter to the list
        }

        return randomLetters;
    }

    public String askForGuess() {
        boolean validGuess = false;
        String guess = "";

        while (!validGuess) {

            System.out.println("Input your new word");
            Scanner scanner = new Scanner(System.in);
            guess = scanner.nextLine();

            if (guess.length() <= 6 && guess.matches("[a-zA-Z]+")) { //checks length and that is only letters, no digits
                validGuess = true;
                return guess;
            } else {
                System.out.println("Please input a valid guess type (5 letters)");
            }
        }

        return guess;
    }

    public void addWordToList(String guess){
        APIWordService apiWordService = new APIWordService();
        boolean validWord = apiWordService.getWordValidity(guess);

        if(validWord){
            guessedWords.add(guess);
            pointCounter++;
        }else{
            System.out.println("Not a valid word. Try again.");
        }

    }

    public void displayWordCount(){
        System.out.println("Points: " + pointCounter);
        System.out.println("Valid words: \n" + guessedWords);
    }

}

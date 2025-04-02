package org.techelevator;

import org.techelevator.Models.Word;
import org.techelevator.Services.APIWordService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordLadder {
    public WordLadder() {
    }

    //*************
//   Methods
//*************
    List<String> guessedWords = new ArrayList<>();
    int pointCounter = 0;
    Word word = getRandomWord();

    public void run() {
        boolean isPlaying = true;
        splashScreen();
        boolean stillGuessing = true;
        displayLatestWord();
        while (isPlaying) {

            String newWord = askForGuess(word);

            stillGuessing = stillPlaying(newWord);
            if (!stillGuessing) {
                break;
            }

            Word newestWord = addWordToList(newWord);
            displayWordCount();
            displayLatestWord(newestWord);
            word = newestWord;
        }
    }

    public Word getRandomWord() {

        List<Word> wordList = new ArrayList<>();
        wordList.add(new Word("tiny"));
        wordList.add(new Word("word"));
        wordList.add(new Word("golf"));
        wordList.add(new Word("white"));
        wordList.add(new Word("push"));
        wordList.add(new Word("wet"));
        wordList.add(new Word("like"));
        wordList.add(new Word("dream"));
        wordList.add(new Word("sheep"));
        wordList.add(new Word("wheat"));
        wordList.add(new Word("star"));
        wordList.add(new Word("coin"));
        wordList.add(new Word("grass"));
        wordList.add(new Word("comb"));
        wordList.add(new Word("bears"));
        wordList.add(new Word("fish"));
        wordList.add(new Word("mine"));
        wordList.add(new Word("bird"));
        wordList.add(new Word("kettle"));
        wordList.add(new Word("winter"));

        int randomIndex = (int) (Math.random() * wordList.size());
        return wordList.get(randomIndex);
    }

    public void splashScreen() {
        System.out.println(
                        "_        __  ____   ____   ____        __     ___    ____   ____   ____   ____ \n" +
                        "| |     / / / __ \\ / __ \\ / __\\      / /    /   |  / __ \\ / __ \\ / __ \\ / __ \\\n" +
                        "| | /| / / / / / // /_/ // / / /    / /    / /| | / / / // / / // / / // /_/ /\n" +
                        "| |/ |/ / / /_/ // _, _// /_/ /    / /___ / ___ |/ /_/ // /_/ // /_/ // _, _/ \n" +
                        "|__/|__/  \\____//_/ |_|/_____/    /_____//_/  |_/_____//_____//_____//_/ |_|");

        System.out.println("Welcome to Word Ladder! You'll be given a random word.\n" +
                "Which you you can change, one letter at a time, to make another word. \n" +
                "Good luck!");


    }

    public String askForGuess(Word word) {
        boolean validGuess = false;
        String guess = "";

        while (!validGuess) {

            boolean isValid = false;
            boolean onlyletters = false;
            boolean notRepeated = false;
            boolean onlyOneLetterChanged = false;

            System.out.println("Input your new word (one letter can be different) OR hit enter X to exit game");
            Scanner scanner = new Scanner(System.in);
            guess = scanner.nextLine();
            guess = guess.toLowerCase();

            if (guess.equalsIgnoreCase("X")) {
                validGuess = true;
                return guess;
            }

            if (guess.length() == word.getWord().length() && guess.matches("[a-zA-Z]+")) { //checks length and that is only letters, no digits
                onlyletters = true;
            } else {
                System.out.println("Please input a valid guess type (Only letters)");
            }

            if (isWordValid(guess)) {
                isValid = true;
            } else {
                System.out.println("That is not a valid word.");
            }

            if (notRepeatedWord(guess, guessedWords)) {
                notRepeated = true;
            } else {
                System.out.println("You already used that combination of letters.");
            }

            if (oneLetterChanged(word, guess)) {
                onlyOneLetterChanged = true;
            } else {
                System.out.println("You need to change only 1 letter.");
            }

            if (notRepeated && isValid && onlyletters && onlyOneLetterChanged) {
                validGuess = true;
            }
        }

        return guess;

    }

    public boolean isWordValid(String guess) {
        APIWordService apiWordService = new APIWordService();
        boolean validWord = apiWordService.getWordValidity(guess);

        return validWord;
    }

    public boolean notRepeatedWord(String guess, List<String> guessedWords) {
        boolean notRepeated = true;

        if (guessedWords.contains(guess)) {
            notRepeated = false;
        }

        return notRepeated;
    }

    public boolean oneLetterChanged(Word prevWordObject, String newWord) {

        boolean oneLetterChanged = false;
        String prevWord = prevWordObject.getWord();

        // Check if words are of same length
        if (prevWord.length() != newWord.length()) {
            return false;
        }

        int differenceCount = 0;

        // Loop through each position in the strings
        for (int i = 0; i < prevWord.length(); i++) {
            // Compare characters at the same position
            if (prevWord.charAt(i) != newWord.charAt(i)) {
                differenceCount++;
            }
        }
        // If more than one difference is found, return false
        if (differenceCount == 1) {
            oneLetterChanged = true;
        }

        return oneLetterChanged;
    }

    public Word addWordToList(String guess){
        guessedWords.add(guess);
        pointCounter++;
        Word newWord = new Word(guess);
        return newWord;
    }

    public void displayWordCount(){
        System.out.println("Points: " + pointCounter);
        System.out.println("Valid words: \n" + guessedWords);
    }

    public boolean stillPlaying(String guess){
        boolean stillPlaying = true;
        if(guess.equalsIgnoreCase("X")){
            stillPlaying = false;
        }

        return stillPlaying;
    }

    public void displayLatestWord(){
        System.out.println("Word: " + word.toString());
    }
    public void displayLatestWord(Word newWord){
        System.out.println("Word: " + newWord.toString());
    }
}

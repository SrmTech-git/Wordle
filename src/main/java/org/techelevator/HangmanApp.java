package org.techelevator;

import org.techelevator.Models.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HangmanApp {


    public List<Character> correctLetters; // List to store correct letters
    public int wrongGuessCounter;

    public void run() {
        Word word = getRandomWord();
        boolean isPlaying = true;

        correctLetters = new ArrayList<>();
        for (int i = 0; i < word.getCharacters().size(); i++) {
            correctLetters.add('*');
        }


        splashScreen();

        while (isPlaying) {

            char guess = askForGuess();
            //int matches = displaySpaces(guess);
            boolean correctGuess = isCorrect(guess, word); //displays string word
            int unknownLetterCount = correctLetterCountDisplay(correctLetters);
            guessCounterDisplay(correctGuess);
            isPlaying = isStillGuessing(wrongGuessCounter, unknownLetterCount, word);

        }
    }

    public void splashScreen(){
        System.out.println(  "  " +
                "  SSS    N   N    OOO    W   W   M   M    A    N   N\n" +
                " S   S   NN  N   O   O   W   W   MM MM   A A   NN  N\n" +
                "  SSS    N N N   O   O   W W W   M M M  AAAAA  N N N\n" +
                "     S   N  NN   O   O   W W W   M   M  A   A  N  NN\n" +
                " SSSS    N   N    OOO     W W    M   M  A   A  N   N\n");

        System.out.println("Welcome to SNOWMAN! You'll be given a mystery word.\n" +
                "Which you will have to guess the word one letter at a time without reaching 5 wrong guesses\n" +
                "Good luck! \n");


    }

    public char askForGuess() {
        boolean validGuess = false;
        char guess = '\0'; // Initialize with a null character

        while (!validGuess) {
            System.out.println("Input your guess (a single letter):");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            // Check if the input is exactly 1 character and is a letter
            if (input.length() == 1 && input.matches("[a-zA-Z]")) {
                guess = input.charAt(0); // Get the first character from the string
                validGuess = true;
            } else {
                System.out.println("Invalid input. Please input a single letter (a-z or A-Z).");
            }
        }

        return guess;
    }

    public boolean isCorrect(char guess, Word word) {
        boolean found = false;
        String wholeWord = word.getWord();
        // Check if the guessed letter exists in the word
        for (int i = 0; i < word.getCharacters().size(); i++) {
            if (Character.toLowerCase(wholeWord.charAt(i)) == Character.toLowerCase(guess)) {
                correctLetters.set(i, wholeWord.charAt(i)); // Update correctLetters
                found = true;
            }
        }

        // Display current state of the word
        if (found) {
            System.out.println("Correct! Current progress: [" + correctLetters+ "]");
        } else {
            System.out.println("Incorrect guess. Try again! [" + correctLetters+ "]");
        }

        return found;
    }

    public int guessCounterDisplay(boolean isCorrect){
        if(!isCorrect){
            wrongGuessCounter++;
        }
        System.out.println("Wrong guesses: " + wrongGuessCounter);
        guessArt(wrongGuessCounter);
        return wrongGuessCounter;
    }


    public int correctLetterCountDisplay(List<Character> correctLetters){
        int asteriskCount = 0;

        // Iterate through the list and count '*' characters
        for (Character c : correctLetters) {
            if (c == '*') {
                asteriskCount++;
            }
        }

        // Display the count
        return asteriskCount;
    }


    public boolean isStillGuessing(int guessCounter, int unknownLetterCount, Word word){
        boolean isStillGuessing = true;
        if(unknownLetterCount == 0){
            System.out.println("You Win!");
            System.out.println( "\n"+
                    "W       W   III   N    N   N    N   EEEEE   RRRR     !!!\n" +
                    "W   W   W    I    NN   N   NN   N   E       R    R   !!!\n" +
                    " W W W W     I    N N  N   N N  N   EEEE    RRRR     !!!\n" +
                    "  W   W      I    N   NN   N   NN   E       R   R     \n" +
                    "  W   W     III   N    N   N    N   EEEEE   R    R   !!!\n");
            isStillGuessing = false;
        }else if(guessCounter == 5){
            System.out.println("\n"+
                    "LL       OOO    SSSS   EEEEE   RRRR    !!!\n" +
                    "LL      O   O   S      E       R   R   !!!\n" +
                    "LL      O   O   SSS    EEEE    RRRR    !!!\n" +
                    "LL      O   O      S   E       R  R    \n" +
                    "LLLLLL   OOO    SSSS   EEEEE   R   R   !!!\n");
            System.out.println("You LOSE. You are out of guesses and need to think about your decisions.");
            System.out.println("The word was " + word.getWord().toUpperCase());
            isStillGuessing = false;

        }

        return isStillGuessing;
    }

    public Word getRandomWord() {

        List<Word> wordList = new ArrayList<>();
        wordList.add(new Word("glacier"));
        wordList.add(new Word("sunrise"));
        wordList.add(new Word("mountain"));
        wordList.add(new Word("rainbow"));
        wordList.add(new Word("journey"));
        wordList.add(new Word("islands"));
        wordList.add(new Word("phoenix"));
        wordList.add(new Word("horizon"));
        wordList.add(new Word("treasure"));
        wordList.add(new Word("wildlife"));
        wordList.add(new Word("explorer"));
        wordList.add(new Word("festival"));
        wordList.add(new Word("campfire"));
        wordList.add(new Word("pyramid"));
        wordList.add(new Word("stargaze"));
        wordList.add(new Word("miracle"));
        wordList.add(new Word("bluebird"));
        wordList.add(new Word("adventure"));
        wordList.add(new Word("portrait"));
        wordList.add(new Word("galactic"));

        int randomIndex = (int) (Math.random() * wordList.size());
        return wordList.get(randomIndex);
    }

    public void guessArt(int guessCount){
        if(guessCount == 1){
            System.out.println(
                            "      |===|\n" +
                            "     _|===|_\n" +
                            "     ( o o )   \n" +
                            "     ( _>_ ) ");
        }else if(guessCount == 2){
            System.out.println("" +
                    "      |===|\n" +
                    "     _|===|_\n" +
                    "     ( o o )   \n" +
                    "     ( _>_ ) \n" +
                    "    (   o   ) \n" +
                    "    (  _o_  )");
        }else if(guessCount == 3){
            System.out.println("     " +
                    "      |===|\n" +
                    "     _|===|_\n" +
                    " Ɏ   ( o o )   \n" +
                    "  \\  ( _>_ )  \n" +
                    "   \\(   o   )\n" +
                    "    (  _o_  )");
        }else if(guessCount == 4){
            System.out.println("" +
                    "      |===|\n" +
                    "     _|===|_\n" +
                    " Ɏ   ( o o )   Ɏ\n" +
                    "  \\  ( _>_ )  /\n" +
                    "   \\(   o   )/\n" +
                    "    (  _o_  )");
        }else if(guessCount == 5){
            System.out.println("" +
                    "      |===|\n" +
                    "     _|===|_\n" +
                    " Ɏ   ( o o )   Ɏ\n" +
                    "  \\  ( _>_ )  /\n" +
                    "   \\(   o   )/\n" +
                    "    (  _o_  )\n" +
                    "   (\t     )\n" +
                    "   (\t     )\n" +
                    "   (_________)"
            );
        }
    }

}

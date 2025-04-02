package org.techelevator;

import org.springframework.web.bind.annotation.PathVariable;
import org.techelevator.Services.APIWordService;
import org.techelevator.Services.WordService;

import java.util.*;

public class LetterSoup {

    public LetterSoup() {
    }


//*************
//   Methods
//*************
    List<String> guessedWords = new ArrayList<>();
    int pointCounter = 0;

    public void run(){
        splashScreen();
        boolean stillGuessing = true;
        List<Character> randomLetters = getRandomLetters();
        while(stillGuessing) {

            System.out.println(randomLetters);
            String playerGuess = askForGuess(randomLetters);

            stillGuessing = stillPlaying(playerGuess);
                if(!stillGuessing){
                    break;
                }

            addWordToList(playerGuess);
            displayWordCount();
        }
    }



    public List<Character> getRandomLetters() {
        List<Character> randomLetters = new ArrayList<>();
        Random random = new Random();
        boolean hasVariety = false;

        while (!hasVariety) {
            randomLetters.clear(); // Clear the list before adding new letters

            for (int i = 0; i < 6; i++) {
                char letter = (char) (random.nextInt(26) + 'a'); // Generates a random letter (a-z)
                randomLetters.add(letter); // Add the letter to the list
            }

            boolean hasVowels = containsVowel(randomLetters);
            boolean hasUniqueLetters = uniqueLetters(randomLetters);

            if (hasUniqueLetters && hasVowels) {
                hasVariety = true;
            }
        }

        return randomLetters;
    }

    public boolean uniqueLetters(List<Character> charList) {
        // Use a HashSet to track characters we've seen
        Set<Character> seenChars = new HashSet<>();

        // Iterate through the list
        for (Character c : charList) {
            // If the character is already in the set, we found a duplicate
            if (seenChars.contains(c)) {
                return false;
            }
            // Otherwise, add it to the set
            seenChars.add(c);
        }

        // If we get through the entire list without finding duplicates, return true
        return true;
    }

    public boolean containsVowel(List<Character> charList) {
        // Define the set of vowels (both lowercase and uppercase)
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));

        // Iterate through the list
        for (Character c : charList) {
            // If the current character is a vowel, return true
            if (vowels.contains(c)) {
                return true;
            }
        }

        // If no vowels are found, return false
        return false;
    }

    public String askForGuess(List<Character> randomLetters) {
        boolean validGuess = false;
        String guess = "";
        boolean hasRightLetters = false;
        boolean hasValidLength = false;
        boolean notRepeated = false;
        boolean validWord = false;

        while (!validGuess) {

            System.out.println("Input your new word OR hit enter X to exit game");
            Scanner scanner = new Scanner(System.in);
            guess = scanner.nextLine();

            if(guess.equalsIgnoreCase("X")){
                validGuess = true;
                return guess;
            }

            if (guess.length() <= 6 && guess.matches("[a-zA-Z]+")) { //checks length and that is only letters, no digits
                hasValidLength = true;

            } else {
                System.out.println("Please input a valid guess type (5 letters)");
            }

            if(hasCorrectLetters(guess, randomLetters)){
                hasRightLetters = true;
            } else {
                System.out.println("Please only use the letters provided");
            }

            if(notRepeatedWord(guess, guessedWords)){
                notRepeated = true;
            }else {
                System.out.println("You've already used that word, try again");
            }

            if(isWordValid(guess)){
                validWord = true;
            }else{
                System.out.println("That isn't a valid word. Try again.");
            }

            if(hasRightLetters && hasValidLength && notRepeated && validWord){
                validGuess = true;
            }

        }

        return guess;
    }

    public boolean hasCorrectLetters(String guess, List<Character> randomLetters){
        boolean onlyRightLetters = true;
        String[] guessLetters = guess.split("");

        for (String letter : guessLetters) {
            // Convert String to Character
            char charLetter = letter.charAt(0);

            if (!randomLetters.contains(charLetter)) { // Compare as Character
                onlyRightLetters = false;
                break;
            }
        }

        return onlyRightLetters;
    }

    public boolean notRepeatedWord(String guess, List<String> guessedWords){
        boolean notRepeated = true;

        if(guessedWords.contains(guess)){
            notRepeated = false;
        }

        return notRepeated;
    }

    public boolean stillPlaying(String guess){
        boolean stillPlaying = true;
        if(guess.equalsIgnoreCase("X")){
            stillPlaying = false;
        }

        return stillPlaying;
    }

    public boolean isWordValid(String guess){
        APIWordService apiWordService = new APIWordService();
        boolean validWord = apiWordService.getWordValidity(guess);

        return validWord;
    }

    public void addWordToList(String guess){
        guessedWords.add(guess);
        pointCounter++;
    }

    public void displayWordCount(){
        System.out.println("Points: " + pointCounter);
        System.out.println("Valid words: \n" + guessedWords);
    }

    public void splashScreen(){
        System.out.println(
                "          ___,.-------..__       \n" +
                "     _,-''        g   y   `'--._ \n" +
                "   ;'     d     q            z   `: \n" +
                " `(   s                 j      b   )'\n" +
                "   :.         s     h            ,;\n" +
                "    `.`--.___     a     ___.--','\n" +
                "      `.     ``-------''     ,'\n" +
                "         -.               ,-\n" +
                "      gpyy  `-._______.-'\n");

        System.out.println("Welcome to Letter Soup! You'll be given 6 random letters.\n" +
                "How many words can you make from those letters? There is no limit to guesses\n"+
                "and you will have to use your best judgement to see when you've guessed them all.\n"+
                "Good luck! \n");



    }

}

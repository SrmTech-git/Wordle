package org.techelevator;

import org.techelevator.Models.Acronym;
import org.techelevator.Models.Word;

import java.util.*;

public class IYKYKApp {
    Acronym randomAcronym = getRandomAcronym();

    public void run(){
        boolean isPlaying = true;
        int guessCounter = 0;
        splashScreen();

        while(isPlaying){

            String guess = getPlayerGuess();
            int matches = displaySpaces(guess);
            guessCounter++;
            guessCounterDisplay(guessCounter);
            displayClue(guessCounter);
            isPlaying = isStillGuessing(matches, guessCounter, randomAcronym);

        }

    }


    public void splashScreen(){
        System.out.println(
                " III    Y   Y   K   K    Y   Y   K   K\n" +
                        "  I      Y Y    K  K      Y Y    K  K\n" +
                        "  I       Y     KKK        Y     KKK\n" +
                        "  I       Y     K  K       Y     K  K\n" +
                        " III      Y     K   K      Y     K   K\n");
        System.out.println("Welcome to IYKYK, an acronym guessing game! \n" +
                "\nYou'll be given a random acronym to guess with length revealed." +
                "You will have 3 attempts to guess the acronym and will be given a hint at each level." +
                "Good luck!");
    }

    public String getPlayerGuess(){
        boolean validGuess = false;
        String guess = "";
        int randomAcronymLength = randomAcronym.getCharacters().size();
        System.out.println("The acronym is " + randomAcronymLength + " characters long");
        while(!validGuess){

            System.out.println("Input your guess");
            Scanner scanner = new Scanner(System.in);
            guess = scanner.nextLine();

            if(guess.length() == randomAcronymLength && guess.matches("[a-zA-Z]+")){ //checks length and that is only letters, no digits
                validGuess = true;
                return guess;
            }
            else{
                System.out.println("Please input a valid guess length: " + randomAcronymLength);
            }
        }

        return guess;
    }

    public Acronym getRandomAcronym(){
        List<Acronym> acronymList = new ArrayList<>();

        // Tech
        acronymList.add(new Acronym("HTTP", "HyperText Transfer Protocol"));
        acronymList.add(new Acronym("JSON", "JavaScript Object Notation"));
        acronymList.add(new Acronym("HTML", "HyperText Markup Language"));
        acronymList.add(new Acronym("USB", "Universal Serial Bus"));

        // Nature
        acronymList.add(new Acronym("WWF", "World Wildlife Fund"));
        acronymList.add(new Acronym("NOAA", "National Oceanic and Atmospheric Administration"));
        acronymList.add(new Acronym("EPA", "Environmental Protection Agency"));

        // Cooking
        acronymList.add(new Acronym("FIFO", "First In, First Out (inventory method often used in food management)"));
        acronymList.add(new Acronym("KCAL", "Kilocalories"));
        acronymList.add(new Acronym("FARM", "Food and Agriculture Resource Management"));

        // Pop Culture
        acronymList.add(new Acronym("OMG", "Oh My God"));
        acronymList.add(new Acronym("YOLO", "You Only Live Once"));
        acronymList.add(new Acronym("BFFL", "Best Friends For Life"));
        acronymList.add(new Acronym("FOMO", "Fear Of Missing Out"));

        // Business/Finance
        acronymList.add(new Acronym("CEO", "Chief Executive Officer"));
        acronymList.add(new Acronym("ROI", "Return On Investment"));
        acronymList.add(new Acronym("IPO", "Initial Public Offering"));

        // Miscellaneous
        acronymList.add(new Acronym("DIY", "Do It Yourself"));

        int randomIndex = (int) (Math.random() * acronymList.size());
        return acronymList.get(randomIndex);
    }

    public void guessCounterDisplay(int guess){
        if(guess == 2){
            System.out.println("You have 1 guess left, choose carefully!");
        }else if(guess <=2){
            System.out.println("Guess counter: " + guess + "/3");
        }
    }

    public boolean isStillGuessing(int matches, int guessCounter, Acronym acronym){
        boolean isStillGuessing = true;
        List<String> charactersList = acronym.getCharacters();
        if(matches == charactersList.size()){
            System.out.println("You Win!");
            System.out.println( "\n"+
                    "W       W   III   N    N   N    N   EEEEE   RRRR     !!!\n" +
                    "W   W   W    I    NN   N   NN   N   E       R    R   !!!\n" +
                    " W W W W     I    N N  N   N N  N   EEEE    RRRR     !!!\n" +
                    "  W   W      I    N   NN   N   NN   E       R   R     \n" +
                    "  W   W     III   N    N   N    N   EEEEE   R    R   !!!\n");
            isStillGuessing = false;
        }else if(guessCounter == 3){
            System.out.println("\n"+
                    "LL       OOO    SSSS   EEEEE   RRRR    !!!\n" +
                    "LL      O   O   S      E       R   R   !!!\n" +
                    "LL      O   O   SSS    EEEE    RRRR    !!!\n" +
                    "LL      O   O      S   E       R  R    \n" +
                    "LLLLLL   OOO    SSSS   EEEEE   R   R   !!!\n");
            System.out.println("You LOSE. You are out of guesses and need to think about your decisions.");
            System.out.println("The acronym was " + acronym.getAcronym().toUpperCase() + " which stands for:" +
                    acronym.getMeaning());
            isStillGuessing = false;

        }

        return isStillGuessing;
    }

    public void displayClue(int guessCounter){

        if(guessCounter ==1){
            String lastDigit = randomAcronym.getCharacters().getLast();
            String letterGroup = getLetterRange(lastDigit);
            System.out.println(letterGroup);
        }
        else if(guessCounter == 2){
            // a word from the meaning
            String[] meaningSplit = randomAcronym.getMeaning().split(" ");
            int meaningSplitLength = meaningSplit.length;
            String randomMeaningWord = meaningSplit[getRandomMeaningWordInt(meaningSplitLength)];
            System.out.println("Here is a random word in the acronym meaning to help you guess: " + randomMeaningWord);
        }
    }

    public int displaySpaces(String guessAcronym){
        guessAcronym = guessAcronym.toLowerCase();

        List<String> guessCharacters = Arrays.stream(guessAcronym.split("")).toList();
        List<String> wordCharacters = randomAcronym.getCharacters();

        List<String> partialWord = new ArrayList<>();

        int count = 0;
        for(int i = 0; i < wordCharacters.size(); i++){
            if(guessCharacters.get(i).equalsIgnoreCase(wordCharacters.get(i))){
                count++; //Add correct counter
                partialWord.add(guessCharacters.get(i).toUpperCase());
            }else{
                partialWord.add("*");
            }
        }


        System.out.println("You got " + count + " characters correct");
        generalMatch(randomAcronym.getAcronym(),guessAcronym);
        System.out.println(partialWord + "\n");

        return count;
    }

    public String getLetterRange(String letter) {
        // Define the alphabet in groups of 5 letters
        List<List<String>> letterGroups = Arrays.asList(
                Arrays.asList("A", "B", "C", "D", "E"),
                Arrays.asList("F", "G", "H", "I", "J"),
                Arrays.asList("K", "L", "M", "N", "O"),
                Arrays.asList("P", "Q", "R", "S", "T"),
                Arrays.asList("U", "V", "W", "X", "Y", "Z")
        );

        // Iterate through the groups to find the range
        for (List<String> group : letterGroups) {
            if (group.contains(letter.toUpperCase())) {
                return "One of these letters is in the Acronym: " + String.join(", ", group) + ".";
            }
        }

        // If the letter is not found
        return "Invalid input. Please enter a valid letter from A-Z.";
    }

    public int getRandomMeaningWordInt(int length){
        Random random = new Random();
        return random.nextInt(length);
    }

    private static String generalMatch(String randomWord, String guess) {
        // Convert randomWord to lowercase to ensure case-insensitive matching
        randomWord = randomWord.toLowerCase();

        String[] guessWordCharacters = guess.toLowerCase().split(""); // Convert guess to lowercase
        String correctLetters = "";

        for (int i = 0; i < guessWordCharacters.length; i++) {
            if (randomWord.contains(guessWordCharacters[i])) {
                correctLetters = correctLetters + guessWordCharacters[i].toUpperCase() + ", "; // Add matched characters in uppercase
            }
        }

        System.out.println("Right letters, wrong place: [" + correctLetters + "]");
        return correctLetters;
    }

}

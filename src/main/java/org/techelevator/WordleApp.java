package org.techelevator;

import org.techelevator.Models.Word;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class WordleApp {
      Word word = getRandomWord();
      //TODO make the display letters persistent
      List<Character> displayCharacters = new ArrayList<>(Arrays.asList('*', '*', '*', '*', '*'));

   public void run(){
      boolean isPlaying = true;
      int guessCounter = 0;
      splashScreen();

      while(isPlaying){

         String guess = askForGuess();
         int matches = displaySpaces(guess);
         guessCounter++;
         isPlaying = isStillGuessing(matches, guessCounter, word);
         guessCounterDisplay(guessCounter);


      }
   }

   public void splashScreen(){
      System.out.println(  "\n W       W    OOOOO    RRRRR    DDDD     L        EEEEE\n" +
                          " W   W   W   O     O   R    R   D   D    L        E    \n" +
                          " W  W W  W   O     O   RRRRR    D    D   L        EEEE \n" +
                          "  W     W    O     O   R  R     D   D    L        E    \n" +
                          "   W   W      OOOOO    R   R    DDDD     LLLLL    EEEEE\n");

      System.out.println("Welcome to Wordle! You'll be given a mystery 5 letter word.\n" +
              "Which you will have 5 tries to guess. Each guess, we will tell you what you\n" +
              "got correct and what you still need to guess. Good luck! \n");


   }

   public String askForGuess(){
      boolean validGuess = false;
      String guess = "";

      while(!validGuess){

         System.out.println("Input your guess");
         Scanner scanner = new Scanner(System.in);
         guess = scanner.nextLine();

         if(guess.length() == 5 && guess.matches("[a-zA-Z]+")){ //checks length and that is only letters, no digits
            validGuess = true;
            return guess;
         }
         else{
            System.out.println("Please input a valid guess type (5 letters)");
         }
      }

      return guess;

   }

   public int displaySpaces(String guessWord){
      guessWord = guessWord.toLowerCase();
      List<String> guessCharacters = Arrays.stream(guessWord.split("")).toList();
      List<String> wordCharacters = word.getCharacters();

      for(int i = 0; i < 5; i++){
         if(displayCharacters.get(i).equals('*')){
            if(guessCharacters.get(i).equalsIgnoreCase(wordCharacters.get(i))){
               char guessCharacter = guessCharacters.get(i).charAt(0);
               displayCharacters.set(i,guessCharacter);
            }
         }
      }

      //see how many characters out of 5 are correct
      char targetCharacter = '*';
      int asteriskCount = 0;
      for (char letter : displayCharacters) {
         if (letter == targetCharacter) {
            asteriskCount++;
         }
      }
      int count = 5 - asteriskCount;

      System.out.println("You got " + count + " exact characters correct");
      generalMatch(word.getWord(),guessWord);
      System.out.println(displayCharacters);

      return count;
   }

   public void guessCounterDisplay(int guess){
      if(guess == 4){
         System.out.println("You have 1 guess left, choose carefully!");
      }else if(guess <=3){
         System.out.println("You have used " + guess + " guesses out of 5. Choose carefully!");
      }
   }

   public boolean isStillGuessing(int matches, int guessCounter, Word word){
      boolean isStillGuessing = true;
      if(matches == 5){
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

   public Word getRandomWord(){

      List<Word> wordList = new ArrayList<>();
      wordList.add(new Word("apple"));
      wordList.add(new Word("chair"));
      wordList.add(new Word("river"));
      wordList.add(new Word("plane"));
      wordList.add(new Word("grass"));
      wordList.add(new Word("music"));
      wordList.add(new Word("smile"));
      wordList.add(new Word("happy"));
      wordList.add(new Word("block"));
      wordList.add(new Word("stone"));
      wordList.add(new Word("dress"));
      wordList.add(new Word("bread"));
      wordList.add(new Word("house"));
      wordList.add(new Word("light"));
      wordList.add(new Word("dream"));
      wordList.add(new Word("magic"));
      wordList.add(new Word("table"));
      wordList.add(new Word("train"));
      wordList.add(new Word("beach"));
      wordList.add(new Word("blaze"));

      int randomIndex = (int) (Math.random() * wordList.size());
      return wordList.get(randomIndex);
   }

   private static String generalMatch(String randomWord, String guess){
      String[] guessWordCharacters = guess.split("");
      String correctLetters = "";
      for(int i =0; i < guessWordCharacters.length; i++){
         if( randomWord.contains(guessWordCharacters[i])){
            correctLetters = correctLetters + guessWordCharacters[i] + ", ";
         }
      }

      System.out.println("Right letters, wrong place: [" + correctLetters +"]");
      return correctLetters;
   }

}

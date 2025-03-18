package org.techelevator;

import org.techelevator.Models.Word;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

//TODO: make sure its case insensitive
//add a count to the loop that updates the try counter in the isPlaying loop
//add method that checks the displaySpaces return to see if its 5 and if it is, then display win

public class App {
      Word word = new Word("Apple");
   public void run(){
      boolean isPlaying = true;

      while(isPlaying){
         splashScreen();
         String guess = askForGuess();
         displaySpaces(guess);
      }
   }

   public void splashScreen(){
      System.out.println(  " W       W    OOOOO    RRRRR    DDDD     L        EEEEE\n" +
                          " W   W   W   O     O   R    R   D   D    L        E    \n" +
                          " W  W W  W   O     O   RRRRR    D    D   L        EEEE \n" +
                          "  W     W    O     O   R  R     D   D    L        E    \n" +
                          "   W   W      OOOOO    R   R    DDDD     LLLLL    EEEEE\n");
      System.out.println("Welcome to Wordle! You'll be given a mystery 5 letter word.\n" +
              "Which you will have 5 tries to guess. Each guess, we will tell you what you\n" +
              "got correct and what you still need to guess. Good luck!");


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
      List<String> guessCharacters = Arrays.stream(guessWord.split("")).toList();
      List<String> wordCharacters = word.getCharacters();
      List<String> correctCharacters = new ArrayList<>();
      List<String> partialWord = new ArrayList<>();

      int count = 0;
      for(int i = 0; i < 5; i++){
         if(guessCharacters.get(i).equals(wordCharacters.get(i))){
            count++; //Add correct counter
            correctCharacters.add(guessCharacters.get(i)); //Add correct characters to the display list
            partialWord.add(guessCharacters.get(i));
         }else{
            partialWord.add("*");
         }
      }

      System.out.println("You got " + count + " characters correct");
      System.out.println(correctCharacters);
      System.out.println(partialWord);

      return count;
   }

}

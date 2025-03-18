package org.techelevator;

public class App {

   public void run(){
      boolean isPlaying = true;

      while(isPlaying){
         splashScreen();
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

   public void word

}

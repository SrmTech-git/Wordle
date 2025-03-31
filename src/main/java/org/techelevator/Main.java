package org.techelevator;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
       getUserGameChoice();
    }

    public static void getUserGameChoice(){

        while(true) {
            System.out.println("------------------------------------------------------------\n" +
                    ">>>>>>>>>>>>>>>>>>>>>>>>>GAME<<<TIME<<<<<<<<<<<<<<<<<<<<<<\n" +
                    "------------------------------------------------------------\n");
            System.out.println("Which game would you like to play today?\n" +
                    String.format("► [1] %-10s --- %s\n", "Wordle", "A word guessing game with 5 guesses") +
                    String.format("► [2] %-10s --- %s\n", "IYKYK", "A acronym guessing game with 3 guesses")+
                    String.format("► [3] %-10s --- %s\n", "Snowman", "A word guessing game with a limit of 5 wrong guesses")+
                    String.format("► [4] %-10s --- %s\n", "LetterSoup", "An word guessing game with unlimited tries, but limited right answers")
                    );


            Scanner scanner = new Scanner(System.in);
            int userChoice = -1; // Initialize with an invalid choice

            while (true) {
                System.out.print("Please choose your game: ");
                String input = scanner.nextLine();

                try {
                    userChoice = Integer.parseInt(input); // Attempt to parse input into an integer
                    if (userChoice == 1 || userChoice == 2 || userChoice == 3|| userChoice == 4) { // Check if the choice is valid
                        break; // Exit the loop if valid input
                    } else {
                        System.out.println("Invalid choice. Please enter 1, 2, 3 or 4.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number (1, 2, 3 or 4).");
                }
            }

            if (userChoice == 1) {
                WordleApp wordleApp = new WordleApp();
                wordleApp.run();
            } else if (userChoice == 2) {
                IYKYKApp IykykApp = new IYKYKApp();
                IykykApp.run();
            }else if (userChoice == 3) {
                HangmanApp hangmanApp = new HangmanApp();
                hangmanApp.run();
            }else if (userChoice == 4) {
                LetterSoup letterSoup = new LetterSoup();
               letterSoup.run();
            } else{
                System.out.println("Invalid choice. Try again");
            }
        }
    }
}
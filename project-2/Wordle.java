import java.util.Scanner;
import java.util.Random;

/**
 * Name: Patrick Kariuki
 * Date: 09/28/2022
 *
 * Project 2: Wordle
 *
 * This project has you create a text based version of Wordle
 * (https://www.nytimes.com/games/wordle/index.html). Wordle is a word guessing
 * game in which you have 6 tries to guess a 5-letter word. You are told whether
 * each letter of your guess is in the word and in the right position, in the
 * word but in the wrong position, or not in the word at all.
 *
 * Some key differences in our version are:
 *
 * - Text menu based with no grid. Players have to scroll up to see their
 * previous guesses.
 *
 * - Support for 4, 5, or 6 letter words
 *
 * - We don't check for whether a guess is a valid word or not. Players can
 * guess anything they want (of the correct length).
 *
 * Fun facts: The original Wordle was developed by Josh Wardle. Wardle's wife
 * chose the official word list for the game.
 *
 * 500.112 Gateway Computing: Java Fall 2022
 */
public class Wordle {

   /**
    * Defining the only Random variable you may (and must) use. DO NOT CHANGE
    * THIS LINE OF CODE.
    */
   static Random gen = new Random(0);

   /**
    * Defines the number of guesses the player starts with for each word. DO NOT
    * CHANGE THIS LINE OF CODE.
    */
   static final int MAX_GUESSES = 6;
   /**
    * Defines the number of hints the player starts with for each word. DO NOT
    * CHANGE THIS LINE OF CODE.
    */
   static final int MAX_HINTS = 2;

   /**
    * The main method. This is where most of your menu logic and game logic
    * (i.e. implementation of the rules of the game ) will end up. Feel free to
    * move logic in to smaller subroutines as you see fit.
    *
    * @param args commandline args
    */
   public static void main(String[] args) {
      printMenu();

      Scanner kb = new Scanner(System.in);
      System.out.print("Please enter a choice: ");
      String word = "";
      int numGuesses = 0;
      int numHints = 0;
      
      while (true) {
         String userInput = kb.nextLine().toLowerCase();
         switch (userInput) {
            case "e":
               // Loop should end when user enters 'e' or 'E'
               kb.close();
               return;
               
            case "n":
               // Initialize game states and generate new word
               word = newWord();
               numGuesses = MAX_GUESSES;
               numHints = MAX_HINTS;
               break;
            
            case "h":
               // Display hint to player if they still have hints remaining 
               if (numHints > 0) {
                  if (word != null) {
                     giveHint(word);
                     numHints--;
                     System.out.printf("You have %d %s remaining.\n", 
                        numHints, (numHints == 1 ? "hint" : "hints"));
                  }
               }
               
               else {
                  System.out.println("Sorry, you're out of hints!");
               }
               break;
               
            case "g":
               // Prompts the player to enter a guess   
               if (numGuesses == 0) {
                  // The user is out of guesses, so they have to restart
                  // Make the word variable null so that the user has to 
                  // re-initialize the game using "n"/"N"
                  System.out.print("Sorry, you're out of guesses! " + 
                     "Use the \"n\"/\"N\" command to play again.");
                  word = null;
               }
               
               else {
                  if (word != null) {
                     System.out.println("Enter your guess:");
                     String guess = kb.nextLine();
                     boolean validGuess = validateGuess(word.length(), guess) 
                        && checkGuess(word, guess);
                  
                     if (validGuess) {
                        System.out.println("Congrats! You won!");
                        word = null;
                     }
                  
                     else {
                        // Decrease the number of remaining guess attempts
                        numGuesses--;
                     
                        if (numGuesses == 0) {
                           System.out.println("Sorry, you're out of guesses! " +
                              "The word was " + word.toUpperCase() + ". " + 
                              "Use the \"n\"/\"N\" command to play again.");
                           // Reset the word to null
                           word = null;                   
                        }
                     
                        else {
                           System.out.printf("You have %d %s remaining.\n", 
                              numGuesses, (numGuesses == 1 ? "guess" : 
                              "guesses"));
                        }
                     }
                  }
               }
               break;
               
            default:
               System.out.println("Invalid option! Try again!");
               break;
         }
         
         // Print menu after every try until user decides to quit
         printMenu();
         System.out.print("Please enter a choice: ");
      }
   }

   /**
    * Prints "HINT! The word contains the letter: X" where X is a randomly
    * chosen letter in the word parameter.
    *
    * @param word The word to give a hint for.
    */
   static void giveHint(String word) {
      int randIdx = gen.nextInt(word.length());
      System.out.println("HINT! The word contains the letter: " 
         + word.charAt(randIdx) + ".");
   }

   /**
    * Checks the players guess for validity. We define a valid guess as one that
    * is the correct length and contains only lower case letters and upper case
    * letters. If either validity condition fails, a message is printed 
    * indicating which condition(s) failed.
    *
    * @param length The length of the current word that the player is trynig to
    *               guess.
    * @param guess  The guess that the player has entered.
    * @return true if the guess is of the correct length and contains only valid
    * characters, otherwise false.
    */
   static boolean validateGuess(int length, String guess) {
      if (guess.length() != length) {
         System.out.println("You must enter a guess of length " + length);
         return false;
      }
      
      for (int i = 0; i < guess.length(); i++) {
         char c = guess.charAt(i);
         if (!Character.isLowerCase(c) && !Character.isUpperCase(c)) {
            System.out.println("Your guess must only contain upper " + 
               "case letters and lower case letters");
            return false;
         }
      }
      return true;
   }

   /**
    * Checks the player's guess against the current word. Capitalization is
    * IGNORED for this comparison. This function also prints a string
    * corresponding to the player's guess. A ? indicates a letter that isn't in
    * the word at all. A lower case letter indicates that the letter is in the
    * word but not in the correct position. An upper case letter indicates a
    * correct letter in the correct position. Example:
    *
    * SPLINE (the correct word)
    *
    * SPEARS (the player's guess)
    *
    * SPe??s (the output printed by this function)
    *
    * Suggestion 1: Convert guess to upper case before doing anything else. This
    * can help simplify later logic.
    *
    * Suggestion 2: Consider using String.indexOf
    *
    * @param word  The current word the player is trying to guess.
    * @param guess The guess that a player has entered.
    * @return true if the word and guess match IGNORING CASE, otherwise false.
    */
   static boolean checkGuess(String word, String guess) {
      // Convert both word and guess to upper case to ignore case
      word = word.toUpperCase();
      guess = guess.toUpperCase();
   
      String result = "";
   
      // Iterate through each letter in the guess
      for (int i = 0; i < guess.length(); i++) {
         char c = guess.charAt(i);
   
         // If the letter is in the word, use the corresponding letter 
         // from the word in the result string. Otherwise, use a '?' character.
         if (word.indexOf(c) >= 0) {
            if (word.indexOf(c) == guess.indexOf(c)) {
               result += word.charAt(i);
            }
            else {
               result += Character.toString(guess.charAt(i)).toLowerCase();
            }
         } 
         else {
            result += '?';
         }
      }
   
      // Print the result string
      System.out.println(result);
   
      // Return true if the result string is the same as the word, ignoring case
      return guess.equalsIgnoreCase(word);
   }

   /**
    * Chooses a random word using WordProvider.getWord(int length). This should
    * print "New word length: X" where x is the length of the word.
    *
    * @return the randomly chosen word
    */
   static String newWord() {
      // Chooses a random word length between 4 and 6
      int length = gen.nextInt(3) + 4;
      
      // Output the length of the new word
      System.out.println("New word length: " + length);
      
      // Generate a new word using WordProvider
      String newWord = WordProvider.getWord(length);
      return newWord;
   }

   /**
    * Prints menu options.
    */
   static void printMenu() {
      System.out.println("n/N: New word");
      System.out.println("h/H: Get a hint");
      System.out.println("g/G: Make a guess");
      System.out.println("e/E: Exit");
      System.out.println("-------------");
   }
}

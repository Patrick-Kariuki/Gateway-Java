/*
 * @author Gateway Instructors
 * @version 1.0
 */
import java.util.Scanner;

/**
 * Execute this to start a game! You can have 2, 3, 4 or 5 players.
 */
public class Start {

   public static void main(String[] args) {

      Scanner input = new Scanner(System.in);
   
      // Display welcome message and prompt for number of players
      System.out.println("===================================");
      System.out.println("Welcome to Crazy Eights!");
      System.out.print("Please enter number of players (2-5): ");
      int num = input.nextInt();
      input.nextLine();
      System.out.println("===================================\n");

      // Create a new game and play it!
      Game crazyEights = new Game(num, input);
      crazyEights.playGame();

   }
}

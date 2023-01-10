/*
 * @author Gateway Instructors
 * @version 1.0
 */

/**
 * Execute this to see two (computer) players play crazy eights.
 */
public class Demo {

   public static void main(String[] args) {
   
      // Display welcome message   
      System.out.println("===================================");
      System.out.println("Welcome to Crazy Eights!");
      System.out.println("===================================\n");

      // Create a new game and play it!
      Game crazyEight = new Game();
      crazyEight.playGame();
   }

}

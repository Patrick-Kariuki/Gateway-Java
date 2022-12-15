import java.util.Scanner;

/**
 * Name: Patrick Kariuki.
 * Date: 09/22/2022
 *
 * Gateway Computing: Java
 * Johns Hopkins University
 * Fall 2022
 *
 * Mondrian Art:
 * This Project enables users to create a customised version of an art piece
 * along the lines of Dutch artist Piet Mondrian. The artpiece is inspired
 * by his piece 'Composition II in Red, Blue and Yellow' with additional
 * elements from 'Mondrian Circle VI'.
 * This project utilizes the Standard Draw Library for implementation. 
 */
public class Project1 {

   public static void main(String[] args) {
   
      Scanner kb = new Scanner(System.in); // allows us to collect input

      // fixed-value "constants" needed by the program
      final int SIZE = 512;  // can be increase if you want
      StdDraw.setCanvasSize(SIZE, SIZE);
      double defaultPenRadius = 0.01;
    
      // Prompt for and collect parameters from the user
      // Prompts user to input the percent for the blue block width
      System.out.print("Enter percent for blue block width: ");
      double percentBlueBlockWidth = kb.nextDouble() / 100.0;
      
      // Prompts user to input the percent for the blue block height
      System.out.print("Enter percent for blue block height: ");
      double percentBlueBlockHeight = kb.nextDouble() / 100.0;
      
      // Prompts user to input the percent for top left block height
      System.out.print("Enter percent for top left block height: ");
      double percentTopLeftBlockHeight = kb.nextDouble() / 100.0;
      
      // Prompts user to input the percent of bottom right block width
      System.out.print("Enter percent for bottom right block width: ");
      double percentBottomRightBlockWidth = kb.nextDouble() / 100.0;
      
      
      // Calculates drawing parameters for the blue block
      // x1: x-coordinate of the blue block center
      double x1 = percentBlueBlockWidth / 2.0;
      // y1: y-coordinate of the blue block center
      double y1 = percentBlueBlockHeight / 2.0;
      // Blue block half-width and half-height
      double blueBlockHalfWidth = percentBlueBlockWidth / 2.0;
      double blueBlockHalfHeight = percentBlueBlockHeight / 2.0;
      
      // Calculates drawing parameters for yellow block
      // x2: x-cooredinate of the yellow block center
      double x2 = 1 - percentBottomRightBlockWidth / 2.0;
      // y2: y-coordinate of the yellow block center
      double y2 = y1 / 2.0;
      // Yellow block half-width and half-height
      double yellowBlockHalfWidth = percentBottomRightBlockWidth / 2.0;
      double yellowBlockHalfHeight = blueBlockHalfHeight / 2.0;
      
      // Calculates drawing parameters for top red block
      // Height of the red block
      double topRedBlockHeight = 1.0 - percentBlueBlockHeight;
      // Width of the red block
      double topRedBlockWidth = 1.0 - percentBlueBlockWidth;
      // x3: x-coordinate of the red block center
      double x3 = 1 - (topRedBlockWidth / 2.0);
      // y3: y-coordinate of the red block center
      double y3 = 1 - (topRedBlockHeight / 2.0);
      // Red block half-width and half-height
      double topRedBlockHalfWidth = topRedBlockWidth / 2.0;
      double topRedBlockHalfHeight = topRedBlockHeight / 2.0;
      
      // Calculates drawing parameters for the circle
      // x4: x-coordinate of the center of the circle
      double x4 = percentBlueBlockWidth;
      // y4: y-coordinate of the center of the circle
      double y4 = 1 - percentTopLeftBlockHeight;
      // Radius of the circle
      double radius = percentTopLeftBlockHeight / 3.0;
      
   
      // Draws the blue block
      StdDraw.setPenColor(StdDraw.BLUE); // Fills block with blue color
      StdDraw.filledRectangle(x1, y1, blueBlockHalfWidth, blueBlockHalfHeight);
      
      // Draws the yellow block
      StdDraw.setPenColor(StdDraw.YELLOW); //Fills block with yellow color
      StdDraw.filledRectangle(x2, y2, yellowBlockHalfWidth, 
         yellowBlockHalfHeight);
      
      // Draws top red block
      StdDraw.setPenColor(StdDraw.RED); // Fills block with red color
      StdDraw.filledRectangle(x3, y3, topRedBlockHalfWidth, 
         topRedBlockHalfHeight);
      
      // Draws the circle
      StdDraw.setPenColor(StdDraw.WHITE); // Fills circle with white color
      StdDraw.setPenRadius(defaultPenRadius);
      StdDraw.filledCircle(x4, y4, radius);
      
      // Draws line separating bottom-right and bottom-left & center blocks
      StdDraw.setPenRadius(2 * defaultPenRadius);
      StdDraw.setPenColor(StdDraw.BLACK);
      // x5: x-coordinate of the start of the line
      double x5 = 1 - percentBottomRightBlockWidth;
      // x6: x-coordinate of the end of the line
      double x6 = 1 - percentBottomRightBlockWidth;
      // y6: y-coordinate of the end of the line
      double y6 = percentBlueBlockHeight;
      StdDraw.line(x5, 0, x6, y6);
      
      // Draws line dividing the bottom-right block in half
      StdDraw.setPenRadius(2 * defaultPenRadius);
      StdDraw.setPenColor(StdDraw.BLACK);
      // x7: x-coordinate of the start of the line
      double x7 = 1 - percentBottomRightBlockWidth;
      // y7: y-coordinate of the start of the line
      double y7 = blueBlockHalfHeight;
      // y8: y-coordinate of the end of the line
      double y8 = blueBlockHalfHeight;
      StdDraw.line(x7, y7, 1.0, y8);
      
      // Draws the black arc on red block
      StdDraw.setPenRadius(defaultPenRadius);
      StdDraw.arc(x4, y4, radius, -90, +90);
      
      // Draws the blue arc
      StdDraw.setPenRadius(defaultPenRadius);
      StdDraw.setPenColor(StdDraw.BLUE);
      StdDraw.arc(x4, y4, radius, +90, +180);
      
      // Draws the yellow arc
      StdDraw.setPenRadius(defaultPenRadius);
      StdDraw.setPenColor(StdDraw.YELLOW);
      StdDraw.arc(x4, y4, radius, +180, -90);
      
      // Draws the thickest line
      double thickestLineRadius = (3 * defaultPenRadius) / 2.0;
      StdDraw.setPenColor(StdDraw.BLACK);
      // x9: x-coordinate of the start of the thickest line
      double x9 = x1;
      // y9: y-coordinate of the start of the thickest line
      double y9 = 1 - percentTopLeftBlockHeight;
      // x10: x-coordinate of the end of the thickest line
      double x10 = blueBlockHalfWidth;
      // y10: y-coordinate of the end of the thickest line
      double y10 = thickestLineRadius;
      StdDraw.filledRectangle(x9, y9, x10, y10);
      
      // Draws line separating bottom and top blocks
      StdDraw.setPenRadius(defaultPenRadius);
      StdDraw.setPenColor(StdDraw.BLACK);
      StdDraw.line(0.0, percentBlueBlockHeight, 1.0, percentBlueBlockHeight);
      
      // Draws line separating the left and right blocks
      StdDraw.setPenColor(StdDraw.BLACK);
      StdDraw.setPenRadius(defaultPenRadius);
      StdDraw.line(percentBlueBlockWidth, 0, percentBlueBlockWidth, 1);
   }
}

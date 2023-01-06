import java.util.Scanner;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileInputStream;
import java.awt.Color;

/**
 * Name: Patrick Kariuki.
 * Date: 11/01/2022
 *
 * Project 4: Rectangles of Rectangles
 * 
 * Gateway Computing: Java
 * Johns Hopkins University
 * Fall 2022
 *
 * This program creates and displays colorful rectanglar grids
 * created in the form of 2D arrays
 * using the Std Draw library.
 * The checkerboard pattern is generated based on user specified
 * size of board and color values(RGB).
 * The snake and spiral patterns are generated from user entered
 * files that contain rectangle values.
 */
public class Project4 {

   /**
    * Drive the Rectangle of Rectangles program.
    * @param args This program does not take commandline arguments.
    * @throws IOException
    */
   public static void main(String[] args) throws IOException {
      Scanner kb = new Scanner(System.in);
   
      System.out.print("Enter checkerboard size: ");
      int checkerboardSize = kb.nextInt();
   
      System.out.print("Enter RGB values, each [0, 255]: ");
      int redColor = kb.nextInt();
      int greenColor = kb.nextInt();
      int blueColor = kb.nextInt();
      Color board = new Color(redColor, greenColor, blueColor);
   
      //Display canvas and calls checkerboard method
      displayBoard();
      checkerboard(board, checkerboardSize);
      
      // Snake drawing operations
      System.out.print("Enter snake input filename: ");
      String snakeInputFile = kb.next();
      Rectangle[][] snakeGrid = readFile(snakeInputFile);
      displayBoard();
      snake(snakeGrid);
      
      // Spiral drawing operations
      System.out.print("Enter spiral input filename: ");
      String spiralInputFile = kb.next();
      Rectangle[][] spiralGrid = readFile(spiralInputFile);
      displayBoard();
      spiral(spiralGrid);
   }
   
   /**
   * Reads a file and creates Rectangle based on values in the file.
   * @param fileIn the user file with rectangle values
   * @throws IOException
   * @return grid the rectangle created from file values
   */
   public static Rectangle[][] readFile(String fileIn) throws IOException {
      FileInputStream file = new FileInputStream(fileIn);
      Scanner scan = new Scanner(file);
      int rows = scan.nextInt();
      int cols = scan.nextInt();
      Rectangle[][] grid = new Rectangle[rows][cols];      
      
      scan.nextLine();
      while (scan.hasNextLine()) {
         for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
               grid[i][j] = new Rectangle(scan.nextLine());
            }
         }
      }
      return grid;
   }
   
   /**
   * Displays light gray Std Draw canvas.
   */
   public static void displayBoard() {
      StdDraw.clear(StdDraw.LIGHT_GRAY);
   }
   
   /**
   * Displays a checkerboard pattern on Std Draw canvas.
   * @param color the user specified color to draw
   * @param size the user specified canvas size
   * @throws IOException
   */
   public static void checkerboard(Color color, int size) throws IOException {
      PrintWriter outfile = new PrintWriter("checkerboard" + size + ".txt");
      int rows = size;
      int cols = size;
      double width = 1.0 / cols;
      double height = 1.0 / rows;
      double xCenter = width / 2.0;
      double yCenter = height / 2.0;
      int red;
      int green;
      int blue;
      double rectWidth;
      double rectHeight;
      boolean filled;
      
      Rectangle[][] drawing = new Rectangle[rows][cols];
      outfile.println(rows + " " + cols);  
      for (int i = 0; i < rows; i++) {
         for (int j = 0; j < cols; j++) {
            red = color.getRed();
            green = color.getGreen();
            blue = color.getBlue();
            rectWidth = width;
            rectHeight = height;
            filled = (i + j) % 2 != 0;
            Color colorToDraw;
            if (filled) {
               colorToDraw = new Color(red, green, blue);
            } 
            else {
               colorToDraw = Color.WHITE;
            }
            drawing[i][j] = new Rectangle(colorToDraw, 
               rectWidth, rectHeight, true,
               (j * width) + xCenter, 1 - ((i * height) + yCenter));
            drawing[i][j].draw();
            StdDraw.pause(200);
            outfile.println(drawing[i][j]);
         }
      }
      outfile.flush();
      outfile.close();
   }
   
   /**
   * Displays a snake pattern on Std Draw canvas.
   * @param snakeGrid the generated snake pattern rectangle
   */
   public static void snake(Rectangle[][] snakeGrid) { 
      int i = 0;
      while (i < snakeGrid.length) {
         if (i % 2 == 0) {
            for (int j = 0; j < snakeGrid[i].length; j++) {
               snakeGrid[j][i].draw();
               StdDraw.pause(200);
            }
         }
         else {
            for (int r = snakeGrid[i].length - 1; r >= 0; r--) {
               snakeGrid[r][i].draw();
               StdDraw.pause(200);
            }
         } 
         i++;
      }
   }
     
   /**
   * Displays a spiral pattern on Std Draw canvas.
   * @param spiralGrid the generated spiral pattern rectangle
   */
   public static void spiral(Rectangle[][] spiralGrid) {
      int startRowIndex = 0;
      int endRowIndex = spiralGrid.length;
      int startColIndex = 0;
      int endColIndex = spiralGrid[0].length;
            
      while (startRowIndex < endRowIndex && startColIndex < endColIndex) {
         for (int i = endRowIndex - 1; i >= 0; i--) {
            spiralGrid[endRowIndex - 1][i].draw();
            StdDraw.pause(200);
         }
         
         for (int i = 0; i < endColIndex; i++) {
            spiralGrid[i][endColIndex - 1].draw();
            StdDraw.pause(200);
         }
         
         for (int i = 0; i < endColIndex; i++) {
            spiralGrid[endColIndex - 1][i].draw();
            StdDraw.pause(200);
         }
         
         for (int i = 0; i < endColIndex; i++) {
            spiralGrid[i][endColIndex - 1].draw();
            StdDraw.pause(200);
         }
         
         for (int i = 0; i < endRowIndex; i++) {
            spiralGrid[i][endColIndex - 1].draw();
            StdDraw.pause(200);
         }
         startRowIndex++;
         endColIndex--;
         startColIndex++;
         endRowIndex--;
      }
   }
}

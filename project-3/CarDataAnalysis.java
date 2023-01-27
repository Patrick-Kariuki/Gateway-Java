import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintWriter;

/**
 * Name: Patrick Kariuki
 * Date: 10/07/2022
 *
 * Car Data Analysis: Project 3 Starter Code.
 *
 * Gateway Programming: Java
 * Johns Hopkins University
 * Fall 2022
 * 
 * This program enables the user to carry out car data analysis
 * by parsing the file with the data and storing the data we care
 * about in parallel arrays, each representing a whole column of data.
 *
 * The program presents the user with a menu to perform data
 * analysis on the loaded data as follows:
 * Option 1: Average price per brand
 * Option 2: Two highest prices in the loaded dataset
 * Option 3: Average price in year and mileage range
 * Option 4: Best value
 * Option 5: Quit and terminates program
 */
public class CarDataAnalysis {

   // menu options
   static final int BRAND_QUERY = 1;
   static final int TWO_HIGHEST_PRICES_QUERY = 2;
   static final int RANGE_QUERY = 3;
   static final int BEST_VALUE_QUERY = 4;
   static final int QUIT = 5;

   // column index constants for car data file
   static final int BRAND = 2;
   static final int YEAR = 4;
   static final int MILEAGE = 6;
   static final int PRICE = 1;

   /**
    * Counts the number of lines in a given plain-text file.
    * @param filename The file whose lines are to be counted.
    * @return the number of lines in the file.
    * @throws FileNotFoundException
    */
   public static int countFileLines(String filename)
                                    throws FileNotFoundException {
      FileInputStream file = new FileInputStream(filename);
      Scanner scan = new Scanner(file);
      int count = 0;
      while (scan.hasNextLine()) {
         scan.nextLine();
         count++;
      }
      scan.close();
      return count;
   }


   /**
    * Print the program menu to the console.
    */
   public static void printMenu() {

      System.out.printf("[%d]: Average price of brand.\n", BRAND_QUERY);
      System.out.printf("[%d]: Two highest prices.\n",
             TWO_HIGHEST_PRICES_QUERY);
      System.out.printf("[%d]: Average price in year and mileage range.\n",
             RANGE_QUERY);
      System.out.printf("[%d]: Best value.\n", BEST_VALUE_QUERY);
      System.out.printf("[%d]: Quit.\n", QUIT);
      System.out.print("Please select an option: ");

   }


    /**
    * Loads brand columns from the csv file.
    * @param brandArr an array for the brand names
    * @param filename the file whose data is being split into columns
    * @throws FileNotFoundException
    * @return loaded column brands
    */
   public static String[] loadColumnBrands(String[] brandArr, String filename)
      throws FileNotFoundException {
      
      FileInputStream file = new FileInputStream(filename);
      Scanner scan = new Scanner(file);
      scan.nextLine();
      int i = 0;
      while (scan.hasNextLine()) {
         String line = scan.nextLine();
         String[] arr = line.split(",");
         brandArr[i] = arr[BRAND];
         i++;
      }
      scan.close();
      return brandArr;
   }


    /**
    * Loads years columns from the csv file.
    * @param yearsArr an array for the year values
    * @param filename the file whose data is being split into columns
    * @return yearArr the loaded years
    * @throws FileNotFoundException
    */
   public static int[] loadColumnYears(int[] yearsArr, String filename) 
      throws FileNotFoundException {
      
      FileInputStream file = new FileInputStream(filename);
      Scanner scan = new Scanner(file);
      scan.nextLine();
      int i = 0;
      while (scan.hasNextLine()) {
         String line = scan.nextLine();
         String[] arr = line.split(",");
         yearsArr[i] = Integer.parseInt(arr[YEAR]);
         i++;
      }
      scan.close();
      return yearsArr;
   }


    /**
    * Load mileage columns from the csv file.
    * @param mileageArr an array for the mileage values
    * @param filename the file whose data is being split into columns
    * @throws FileNotFoundException
    * @return mileageArr the loaded mileage columns
    */
   public static double[] loadColumnMileage(double[] mileageArr,
      String filename) 
      throws FileNotFoundException {
      
      FileInputStream file = new FileInputStream(filename);
      Scanner scan = new Scanner(file);
      scan.nextLine();
      int i = 0;
      while (scan.hasNextLine()) {
         String line = scan.nextLine();
         String[] arr = line.split(",");
         mileageArr[i] = Double.parseDouble(arr[MILEAGE]);
         i++;
      }
      scan.close();
      return mileageArr;
   }


    /**
    * Loads price columns from the csv file.
    * @param priceArr an array for the price values
    * @param filename the file whose data is being split into columns
    * @throws FileNotFoundException
    * @return priceArr the loaded price columns
    */
   public static double[] loadColumnPrice(double[] priceArr, String filename) 
      throws FileNotFoundException {
      
      FileInputStream file = new FileInputStream(filename);
      Scanner scan = new Scanner(file);
      scan.nextLine();
      int i = 0;
      while (scan.hasNextLine()) {
         String line = scan.nextLine();
         String[] arr = line.split(",");
         priceArr[i] = Double.parseDouble(arr[PRICE]);
         i++;
      }
      scan.close();
      return priceArr;
   }
    
    /**
    * Calculates the average price per brand.
    * @param brand the car brand in query
    * @param outputFile the file to save all matching entries
    * @param brandArr array with loaded brand columns
    * @param priceArr array with loaded price columns
    * @param yearsArr array with loaded year columns
    * @param mileageArr array with loaded mileage columns
    */
   public static void avgPricePerBrand(String brand, String outputFile, 
      String[] brandArr, double[] priceArr, int[] yearsArr,
      double[] mileageArr) 
      throws FileNotFoundException {
      
      FileOutputStream fileStream = new FileOutputStream(outputFile);
      PrintWriter fileOut = new PrintWriter(fileStream);
      double sum = 0;
      int count = 0;
      double avgPrice;
      
      for (int i = 0; i < brandArr.length; i++) {
         if (brand.equalsIgnoreCase(brandArr[i])) {
            sum += priceArr[i];
            count++;
            fileOut.print(i + ", " + brand + ", " + yearsArr[i]);
            fileOut.println(", " + mileageArr[i] + ", " + priceArr[i]);
         }
      }
      fileOut.close();
      avgPrice = sum / count;
      System.out.print("There are " + count + " matching entries for brand ");
      System.out.print(brand);
      System.out.print(" with an average price of $"); 
      System.out.printf("%.2f", avgPrice);
      System.out.println(".");
   }
    
    /**
    * Outputs the two highest prices.
    * @param priceArr array with loaded price columns
    */
   public static void twoHighestPrices(double[] priceArr) {
      double highestPrice = Double.MIN_VALUE;
      double secondHighestPrice = Double.MIN_VALUE;
       
      for (int i = 0; i < priceArr.length; i++) {
         if (priceArr[i] > highestPrice) {
            secondHighestPrice = highestPrice;
            highestPrice = priceArr[i];
         }
         if (priceArr[i] < highestPrice && priceArr[i] > secondHighestPrice) {
            secondHighestPrice = priceArr[i];
         }
      }
      System.out.print("The two highest prices are $");
      System.out.printf("%.2f", highestPrice);
      System.out.print(" and $");
      System.out.printf("%.2f", secondHighestPrice);
      System.out.println(".");
   }
    
    
    /**
    * Outputs the average price in year and mileage range.
    * @param yearsArr array with loaded year columns
    * @param priceArr array with loaded prices columns
    * @param mileageArr array with loaded mileage columns
    * @param yearLowerBound low-end car year
    * @param yearUpperBound high-end car year
    * @param mileageLowerBound low-end mileage value
    * @param mileageUpperBound high-end mileage value
    */
   public static void avgPriceYearMileageRange(int[] yearsArr, 
      double[] priceArr, double[] mileageArr, int yearLowerBound, 
      int yearUpperBound, int mileageLowerBound, 
      int mileageUpperBound) {
      
      int count = 0;
      double sum = 0;
      double avgPrice;
       
      for (int i = 0; i < yearsArr.length; i++) {
         if (yearsArr[i] >= yearLowerBound && yearsArr[i] <= yearUpperBound) {
            if (mileageArr[i] >= mileageLowerBound && 
               mileageArr[i] <= mileageUpperBound) {
               sum += priceArr[i];
               count++;
            }
         }
      }
      avgPrice = sum / count;
      System.out.print("There are " + count + " matching entries ");
      System.out.print("for year range [" + yearLowerBound + ", ");
      System.out.print(yearUpperBound + "] and mileage range ");
      System.out.print("[" + mileageLowerBound + ", ");
      System.out.print(mileageUpperBound + "]");
      System.out.print(" with an average price of $");
      System.out.printf("%.2f", avgPrice);
      System.out.println(".");
   }
    
    /**
    * Outputs the best value.
    * @param priceArr array with loaded price columns
    * @param mileageArr array with loaded mileage columns
    * @param yearsArr array with loaded year columns
    * @param brandArr array with loaded brand columns
    * @param mileageThreshold lower mileage threshold
    * @param priceThreshold lower price threshold
    */
   public static void bestValue(double[] priceArr, 
      double[] mileageArr, int[] yearsArr, String[] brandArr, 
      int mileageThreshold, int priceThreshold) {
      
      double bestMileage = 0;
      int bestYear = 0;
      String bestBrand = "";
      double bestValue = 0.0;
      double bestValueFinal = 0.0;
      
      for (int i = 0; i < priceArr.length; i++) {
         if (priceArr[i] > priceThreshold && mileageArr[i] > mileageThreshold) {
            bestValue = yearsArr[i] - (mileageArr[i] / 13500)
               - (priceArr[i] / 1900);
            
            if (bestValueFinal < bestValue) {
               bestValueFinal = bestValue;
               bestBrand = brandArr[i];
               bestMileage = mileageArr[i];
               bestYear = yearsArr[i];
            }
         }
      }

      System.out.print("The best-value entry with more than ");
      System.out.print(mileageThreshold);
      System.out.print(" miles and a price higher than $" + priceThreshold);
      System.out.print(" is a " + bestYear + " " + bestBrand + " with ");
      System.out.print(bestMileage + " miles for a price of $");
      System.out.print((int) bestValueFinal); 
      System.out.println(".");
   }

    
   /**
    * Drive the Car Data Analysis program.
    * @param args This program does not take commandline arguments.
    * @throws FileNotFoundException
    */
   public static void main(String[] args) throws FileNotFoundException {

      // output purpose
      System.out.println("Welcome to the car dataset analysis program.");

      // get input filename (e.g. "USA_cars_datasets.csv")
      System.out.print("Please enter input csv filename: ");
      Scanner keyboard = new Scanner(System.in);
      String filename = keyboard.nextLine();

      // count the number of rows in the file (ignore headers line)
      int rowCount = countFileLines(filename) - 1;
      System.out.println("File has " + rowCount + " entries.");
      System.out.println();

      // declare and allocate parallel arrays for each column of interest
      String[] brandArr = new String[rowCount];
      int[] yearsArr = new int[rowCount];
      double[] mileageArr = new double[rowCount];
      double[] priceArr = new double[rowCount];
      

      // load columns from file
      brandArr = loadColumnBrands(brandArr, filename);
      yearsArr = loadColumnYears(yearsArr, filename);
      mileageArr = loadColumnMileage(mileageArr, filename);
      priceArr = loadColumnPrice(priceArr, filename);


      // while the user doesn't choose to quit...
      int option = 0;
      while (option != QUIT) {

         // display the menu and get an option
         printMenu();
         option = keyboard.nextInt();

         // handle chosen option
         switch (option) {
            case BRAND_QUERY:
               System.out.print("Please enter a car brand: ");
               String brand = keyboard.next();
               System.out.print("Please enter an output file: ");
               String outputFile = keyboard.next();
               avgPricePerBrand(brand, outputFile, brandArr, priceArr,
                  yearsArr, mileageArr);
               break;
               
            case TWO_HIGHEST_PRICES_QUERY:
               twoHighestPrices(priceArr);
               break;
               
            case RANGE_QUERY:
               System.out.print("Please enter the year lower bound: ");
               int yearLowerBound = keyboard.nextInt();
               System.out.print("Please enter the year upper bound: ");
               int yearUpperBound = keyboard.nextInt();
               System.out.print("Please enter the mileage lower bound: ");
               int mileageLowerBound = keyboard.nextInt();
               System.out.print("Please enter the mileage upper bound: ");
               int mileageUpperBound = keyboard.nextInt();
               avgPriceYearMileageRange(yearsArr, priceArr, mileageArr, 
                  yearLowerBound, yearUpperBound, mileageLowerBound,
                  mileageUpperBound);
               break;
               
            case BEST_VALUE_QUERY:
               System.out.print("Please enter lower mileage threshold: ");
               int mileageThreshold = keyboard.nextInt();
               System.out.print("Please enter lower price threshold: ");
               int priceThreshold = keyboard.nextInt();
               bestValue(priceArr, mileageArr, yearsArr, brandArr, 
                  mileageThreshold, priceThreshold);
               break;
               
            case QUIT:
               System.out.println("Thank you for using the program!");
               break;
               
            default:
               System.out.println("Invalid option.");

         }

         // leave empty line for next printing of menu
         System.out.println();

      }

   }

}

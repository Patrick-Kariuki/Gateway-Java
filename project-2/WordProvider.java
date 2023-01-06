import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * This class has one static method getWord that returns a random word from
 * wordlist.txt of the specified length.
 *
 * DO NOT MODIFY THIS FILE!
 */
public class WordProvider {
   private static final Random RNG = new Random(0);
   private static final Map<Integer, List<String>> WORD_MAP = new HashMap<>();

   static {
      Scanner scanner = null;
      try {
         File file = new File("wordlist.txt");
         scanner = new Scanner(file);
         while (scanner.hasNextLine()) {
            String word = scanner.nextLine();
            WORD_MAP.computeIfAbsent(word.length(), i -> new ArrayList<>())
                  .add(word.toUpperCase());
         }
      } catch (FileNotFoundException e) {
         System.out.println("An error occurred reading wordlist.txt! Make " +
               "sure wordlist.txt is in the current directory. The current " +
               "directory is: " + new File(".").getAbsoluteFile());
      } finally {
         if (scanner != null) {
            scanner.close();
         }
      }
   }

   /**
    * Returns a random word of the specified length, in all caps.
    *
    * @param length the desired length of the word
    * @return the word
    */
   public static String getWord(int length) {
      if (length < 4 || length > 6) {
         throw new RuntimeException("getWord() called with invalid length " +
               length + ". Length must be in the range [4, 6].");
      }
      List<String> wordList = WORD_MAP.get(length);
      return wordList.get(RNG.nextInt(wordList.size()));
   }
}

/**
 * Name: Patrick Kariuki.
 * Date: 11/09/2022
 *
 * Gateway Computing: Java
 * Fall 2022
 *
 * Project 5A: Lay your cards on the table.
 * Provides unit tests for the Card class.
 */
public class TestCard {
   
   /**
   * A helper method to assert that two integers are equal.
   * @param exp expected integer value.
   * @param act actual integer value.
   * @param dsc description of the test.
   */
   public static void assertEquals(int exp, int act, String dsc) {
      if (exp == act) {
         System.out.println("PASS: " + dsc);
      }
      else {
         System.out.println("FAIL: " + dsc);
      }
   }
   
   /**
   * A helper method to assert that two booleans are equal.
   * @param exp expected boolean value.
   * @param act actual boolean value.
   * @param dsc description of the test.
   */
   public static void assertEquals(boolean exp, boolean act, String dsc) {
      if (exp == act) {
         System.out.println("PASS: " + dsc);
      }
      else {
         System.out.println("FAIL: " + dsc);
      }
   }
   
   public static void main(String[] args) {
      Card kingOfClubs = new Card(13, 1);
      System.out.println(kingOfClubs);     // must print “King of Clubs”
      assertEquals(13, kingOfClubs.getRank(), 
         "Rank is correctly assigned.");
      assertEquals(1, kingOfClubs.getSuit(), 
         "Suit is correctly assigned.");
      
      //tests for constructor and getter methods
      Card fiveOfDiamonds = new Card(5, 2);
      System.out.println(fiveOfDiamonds);
      assertEquals(5, fiveOfDiamonds.getRank(),
              "Rank is correctly assigned.");
      assertEquals(2, fiveOfDiamonds.getSuit(),
              "Suit is correctly assigned.");

      Card nineOfHearts = new Card(9, 3);
      System.out.println(nineOfHearts);
      assertEquals(9, nineOfHearts.getRank(),
              "Rank is correctly assigned.");
      assertEquals(3, nineOfHearts.getSuit(),
              "Suit is correctly assigned.");

      Card jackOfSpades = new Card(11, 4);
      System.out.println(jackOfSpades);
      assertEquals(11, jackOfSpades.getRank(), 
         "Rank is correctly assigned.");
      assertEquals(4, jackOfSpades.getSuit(), 
         "Suit is correctly assigned.");
      
      Card invalidCardOne = new Card(0, 0);
      System.out.println(invalidCardOne);
      assertEquals(0, invalidCardOne.getRank(), 
         "Rank is correctly assigned to 0.");
      assertEquals(0, invalidCardOne.getSuit(), 
         "Suit is correctly assigned to 0.");
      
      Card invalidCardTwo = new Card(10, 0);
      System.out.println(invalidCardTwo);
      assertEquals(0, invalidCardTwo.getRank(), 
         "Rank is correctly assigned to 0.");
      assertEquals(0, invalidCardTwo.getSuit(), 
         "Suit is correctly assigned to 0.");

      Card invalidCardThree = new Card(20, 7);
      System.out.println(invalidCardThree);
      assertEquals(0, invalidCardThree.getRank(), 
         "Rank is correctly assigned to 0.");
      assertEquals(0, invalidCardThree.getSuit(), 
         "Suit is correctly assigned to 0.");
      
      //equals methods tests
      assertEquals(false, kingOfClubs.equals(fiveOfDiamonds), 
         "Equals compares cards correctly.");
      assertEquals(false, jackOfSpades.equals(nineOfHearts),
              "Equals compares cards correctly.");

      Card secondJackOfSpades = new Card(11, 4);
      assertEquals(true, jackOfSpades.equals(secondJackOfSpades),
              "Equals compares cards correctly.");

      Card secondNineOfHearts = new Card(9, 3);
      assertEquals(true, nineOfHearts.equals(secondNineOfHearts),
              "Equals compares cards correctly.");

      //compareTo method tests
      assertEquals(-1, kingOfClubs.compareTo(nineOfHearts),
              "CompareTo compares cards correctly.");
      assertEquals(1, jackOfSpades.compareTo(fiveOfDiamonds),
              "CompareTo compares cards correctly.");
      assertEquals(1, nineOfHearts.compareTo(fiveOfDiamonds),
              "CompareTo compares cards correctly.");
      assertEquals(-1, fiveOfDiamonds.compareTo(jackOfSpades),
              "CompareTo compares cards correctly.");
      assertEquals(0, jackOfSpades.compareTo(secondJackOfSpades),
              "CompareTo compares cards correctly.");
      assertEquals(0, nineOfHearts.compareTo(secondNineOfHearts),
              "CompareTo compares cards correctly.");
   }
}

import java.util.Random;
/**
 * Name: Patrick Kariuki.
 * JHED ID: pkariuk1
 * Date: 09/11/2022
 *
 * Gateway Computing: Java
 * Fall 2022
 *
 * Project 5A: Lay your cards on the table.
 * The Deck class represents a standard deck of 52 cards.
 * The deck of card is un-shuffled on initialization.
 * Each card has a suit,which can either be Clubs, Diamonds,
 * Hearts or Spades, and a rank, which can either be Ace, 1,
 * 2, 3, 4, 5, 6, 7,8, 9, Jack, Queen or King.
 */
 
public class Deck {
   /** An array of Card object*/
   private Card[] cards;
   /** Defines a random variable to use */
   private Random gen = new Random(0);
   
   
    /**
    * Constructs a standard deck of cards.
    * Initially, the cards are in a sorted order.
    */
   public Deck() {
      cards = new Card[52];
      int countCards = 0; //keep track of the number of cards created
      for (int suit = 1; suit <= 4; suit++) {
         for (int rank = 1; rank <= 13; rank++) {
            cards[countCards] = new Card(rank, suit);
            countCards++;
         }
      }
   }

    /**
     * Shuffles the deck into a random order.
     */
   public void shuffle() {
      Card[] copy = new Card[100]; //oversize array
      int k = 51;
      
      for (int idx = 0; idx < cards.length; idx++) {
         copy[idx] = cards[idx];
      }
      
      for (int i = 0; i <= 51; i++) {
         int j = gen.nextInt(k + 1);
         cards[i] = copy[j];
         copy[j] = copy[k];
         k--;
      }
   }
   
   /**
    * Create a string that "textually represents" this object.
    * @return  a string representation of the deck of cards.
    */
   @Override
   public String toString() {
      String result = "";
      for (int i = 0; i < cards.length; i++) {
         result += cards[i].toString() + "\n";
      }
      return result;
   }

   /**
    * Sorts the deck of cards by traversing though the cards.
    */
   public void sort() {
      for (int j = 0; j <= 51; j++) {
         Card c = cards[j];
         int i = j - 1;
         while (i >= 0 && cards[i].compareTo(c) > 0) {
            cards[i + 1] = cards[i];
            i--;
         }
         cards[i + 1] = c;
      }
   }
}

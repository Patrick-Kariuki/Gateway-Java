/**
 * Name: Patrick Kariuki.
 * Date: 11/09/2022
 *
 * Gateway Computing: Java
 * Fall 2022
 *
 * Project 5A: Lay your cards on the table.
 * The Card objects represents a playing card from
 * the standard deck of cards. Each card has a suit,
 * which can either be Clubs, Diamonds, Hearts or Spades,
 * and a rank, which can either be Ace, 1, 2, 3, 4, 5, 6, 7,
 * 8, 9, Jack, Queen or King.
 */
 
public class Card {
   // Defines cards suits as integers
   private static final int CLUBS = 1;
   private static final int DIAMONDS = 2;
   private static final int HEARTS = 3;
   private static final int SPADES = 4;

   // Defines cards numbers for non-numeric cards
   private static final int ACE = 1;
   private static final int JACK = 11;
   private static final int QUEEN = 12;
   private static final int KING = 13;
   
   // Card suit representing either of the 4 suits
   private int suit;
   // card number 1 through 13
   private int cardRank;
   
   /**
   * Creates a card based on the passed suit and card number passed.
   * Pre-conditions:
   *     1 <= cardRank <= 13
   *     1 <= suit <= 4
   * @param cardRank the card number
   * @param suit the suit number
   */
   public Card(int cardRank, int suit) {
      if (isValid(cardRank, suit)) {
         this.cardRank = cardRank;
         this.suit = suit;
      }
      else {
         this.cardRank = 0;
         this.suit = 0;
      }
   }

   /**
    * Returns the rank of the card.
    * @return cardRank the card rank 1 through 13
    */
   public int getRank() {
      return this.cardRank;
   }
   
   /**
    * Returns the suit of the card.
    * @return suit the suit of the card
    */
   public int getSuit() {
      return this.suit;
   }
   
   /**
    * Create a string that "textually represents" Card object.
    * @return  a string representation of the card object.
    */
   @Override
   public String toString() {
      if (this.cardRank == 0 && this.suit == 0) {
         return "Invalid card";
      }
      return getCardRankAsString() + " of " + getSuitAsString();
   }
   
   /**
    * Indicates whether two Card objects are equal or not.
    * @param other an object of type Card.
    * @return true if the cards  have the same values for
    * cardRank and suit; and false otherwise.
    */
   @Override
   public boolean equals(Object other) {
      boolean result = false;
      if (other instanceof Card) {
         Card otherCard = (Card) other;
         result = this.cardRank == otherCard.cardRank
                 && this.suit == otherCard.suit;
      }
      return result;
   }
   
   /**
    * Returns true if suit and cardRank are valid:
    *     1 <= cardRank <= 13
    *     1 <= suit <= 4
    * @param cardRank the value of the card.
    * @param suit the value of suit.
    * @return true if suit and cardRank are both valid
    * and false otherwise.
    */
   private static boolean isValid(int cardRank, int suit) {
      boolean validCardRank = cardRank >= 1 && cardRank <= 13;
      boolean validSuit = suit >= 1 && suit <= 4;
      return validCardRank && validSuit;
   }
   
   /**
    * Compare this Card with the specified otherCard for order.
    * @param otherCard the other Card object to be compared.
    * @return a negative integer, zero, or a positive integer as
    * this object is less than, equal to, or greater than the otherCard.
    */
   public int compareTo(Card otherCard) {
      if (this.suit > otherCard.suit) {
         return 1;
      }
      if (this.suit < otherCard.suit) {
         return -1;
      }
      // if suits are the same, check ranks
      if (this.cardRank > otherCard.cardRank) {
         return 1;
      }
      if (this.cardRank < otherCard.cardRank) {
         return -1;
      }
      else {
         return 0;
      }
   }
   
   /**
    * Maps the suit name as a string.
    * @return the suit name
    */
   private String getSuitAsString() {
      switch (suit) {
         case SPADES:
            return "Spades";
         case HEARTS:
            return "Hearts";
         case DIAMONDS:
            return "Diamonds";
         default:
            return "Clubs";
      }
   }
   
   /**
   * Maps the card rank as a string.
   * @return the card rank as a string
   */
   private String getCardRankAsString() {
      switch (cardRank) {
         case 1:
            return "Ace";
         case 2:
            return "2";
         case 3:
            return "3";
         case 4:
            return "4";
         case 5:
            return "5";
         case 6:
            return "6";
         case 7:
            return "7";
         case 8:
            return "8";
         case 9:
            return "9";
         case 10:
            return "10";
         case 11:
            return "Jack";
         case 12:
            return "Queen";
         default:
            return "King";
      }
   }
}

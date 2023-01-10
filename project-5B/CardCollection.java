/*
 * @author Gateway Instructors
 * @version 1.0
 */


/**
 * This class encapsulates an collection of playing cards.
 */
public class CardCollection {

   /** The maximum size of a collection of cards. */
   public static final int MAXSIZE = 52;

   /** The array that holds the cards. */
   protected Card[] cards;
   
   /** The label naming this collection. */
   protected String label;

   /** The number of cards currently in this collection. */
   protected int numFilled;

   /**
    * Construct an empty array of type Card, with a default label.
    */
   public CardCollection() {
      this.cards = new Card[MAXSIZE];
      this.numFilled = 0;
      this.label = "CardCollection";
   }
   

   /**
    * Construct an empty array of type Card, with a specified label.
    * @param theLabel the label naming the collection
    */
   public CardCollection(String theLabel) {
      this();
      this.label = theLabel;
   }


   /**
    * Add the specified card to an array of cards, if it is not full.
    * @param card the card
    */
   public void addCard(Card card) {
      if (numFilled < MAXSIZE) {
         cards[numFilled] = card;
         numFilled++;
      }
   }
   
   /**
    * Return (but do not remove) the top card from the collection.
    * @return the top card (or throw exception if the collection is empty).
    */
   public Card top() {
      if (!this.isEmpty()) {
         return this.cards[this.numFilled - 1];
      } else {
         throw new RuntimeException("Called top() on an empty CardCollection");
      }
   }

   /**
    * Removes the "top" card from a collection, assuming one exists.
    * @return the last card in array of cards, or throw exception if empty
    */
   public Card discard() {

      // If the card collection holds no cards, we can't remove one
      if (isEmpty()) {
         throw new RuntimeException("should not be empty");
      }

      // If execution makes it here, then collection holds at least one card, so
      // remove it from array, decrement numFilled, and return the card.
      Card cardToReturn = this.cards[this.numFilled - 1];
      numFilled--;
      this.cards[this.numFilled] = null;
      return cardToReturn;

   }

   /**
    * Return the number of cards in this collection.
    * @return the number of cards in the collection
    */
   public int size() {
      return this.numFilled;
   }

   /**
    * Determine if the card collection is empty or not.
    * @return true if the collection is empty, and false otherwise
    */
   public boolean isEmpty() {
      return this.numFilled == 0;
   }

}

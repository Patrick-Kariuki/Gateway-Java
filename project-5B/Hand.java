/*
 * @author Gateway Instructors
 * @version 1.0
 */

/**
 * This class represents a hand: the cards held by one player.
 */
public class Hand extends CardCollection {

   /**
    * Create a new (empty) hand using the label "Hand".
    */
   public Hand() {
      super("Hand");
   }
   
   /**
    * Create a new (empty) hand with the specified label.
    * @param label the name used to label the hand
    */
   public Hand(String label) {
      super(label);
   }

   /**
    * Remove and return the card in Hand at the specified index. 
    * [This method overloads discard() in the CardCollection class.]
    * The cards remaining in the hand after the specified one is removed
    * are, if necessary, shifted towards the low-index end of the array,
    * so that they are all contiguous in the Hand at the completion of
    * this method, and remain in the same order they started in.
    * @param i the zero-based index of a card in this hand
    * @return the specified card (or throw an exception if no card exists 
    *    at the specified index)
    */
   public Card discard(int i) {

      // Raise an appropriate exception if the argument value describes 
      // an illegal Card position in this hand
      if (i >= this.size() || i < 0) {
         throw new IllegalArgumentException("invalid card index in Hand");
      }
      


      // TODO: implement the rest of this method
      return null; // Replace this stub



   }


   /**
    * Return (but do not remove) the card
    * in Hand at the given index.
    *
    * @param i the (zero-based) index of a card in Hand.
    * @return the card, or throw exception if no card exists at that index
    */
   public Card getCard(int i) {
      if (i >= this.size() || i < 0) {
         throw new IllegalArgumentException("invalid card index in Hand");
      } else {
         return this.cards[i];
      }
   }


   /* Return a String representation of the Hand. */
   @Override
   public String toString() {
      StringBuilder output = new StringBuilder();
      output.append(label);
      output.append("'s hand:\n");
      if (this.numFilled == 0) {
         output.append("empty!");
      }
      else {
         for (int i = 0; i < this.numFilled; i++) {
            output.append("[");
            output.append(Integer.toString(i + 1));
            output.append("]: ");
            output.append(cards[i]);
            output.append("\n");
         }
      }
      return output.toString();
   }

}

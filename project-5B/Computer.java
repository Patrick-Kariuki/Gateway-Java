/*
 * @author Gateway Instructors
 * @version 1.0
 */

/**
 * The class implements a player strategy executed by the computer.
 */
public class Computer extends Player {

   /**
    * Create a new computer player with the specified name and an empty hand.
    * @param theName the name of the computer player
    */
   public Computer(String theName) {
      super(theName);
   }

   /**
    * Execute this player's turn in the game. 
    * @param crazyEight gives access to the "draw pile"
    * @param top the card at the top of the "discard pile"
    * @return a matching card from the player's hand
    */
   public Card makeMove(Game crazyEight, Card top) {
      Card card = searchForMatch(top);
      if (card == null) { //no matching card in hand, so draw
         card = drawForMatch(crazyEight, top);
      }
      return card;
   }

   /**
    * Search the player's hand for a card that matches in the
    * sense of Crazy Eights.
    * @param prev the previously-played card (top of "draw pile")
    * @return a card from the player's hand, or null if none match
    */
   private Card searchForMatch(Card prev) {
      for (int i = 0; i < hand.size(); i++) {
         Card card = hand.getCard(i);
         if (Game.cardMatches(card, prev)) {
            return hand.discard(i);
         }
      }
      // If we reach this spot, we didn't find a matching card
      return null;
   }

   /**
    * Draw cards from "draw pile" until a Crazy Eights match is found.
    * @param crazyEight gives access to the "draw pile".
    * @param prev the previously-played card (top of "draw pile")
    * @return a card that matches prev
    */
   private Card drawForMatch(Game crazyEight, Card prev) {

      // Draw one card
      Card card = crazyEight.draw();
      System.out.println("*** " + name + " draws " + card + "\n");
      
      // Contine drawing cards until most recent one matches prev;
      // put non-matching cards into the hand
      while (!Game.cardMatches(card, prev)) {
         hand.addCard(card);
         card = crazyEight.draw();
         System.out.println("*** " + name + " draws " + card + "\n");
      }

      // Return the matched card
      return card;
   }

}

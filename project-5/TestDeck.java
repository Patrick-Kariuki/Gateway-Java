/** Unit tests for Deck class. */
public class TestDeck {

    /**
     * Execution starts here.
     * @param args command-line arguments.
     */
   public static void main(String[] args) {
        
        
      //Output must display all 52 playing cards in order
      Deck deck = new Deck();
      deck.shuffle();
      deck.sort();
      System.out.println(deck.toString());

   }

}
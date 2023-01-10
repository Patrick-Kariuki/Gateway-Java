/*
 * @author Gateway Instructors
 * @version 1.0
 */

import java.util.Scanner;

/**
 * The class creates a crazy eights game and maintains its state.
 */
public class Game {

   /** Formatting string to use in game output. */
   private static final String LINE_OF_DASHES =
      "-----------------------------------";


   /**
    * The collection of Players.
    */
   private Player[] players;

   /**
    * The draw pile (stock).
    */
   private Deck drawPile;

   /**
    * The discard pile (waste).
    */
   private CardCollection discardPile;

   /**
    * The input mechanism.
    */
   private Scanner input;
   
   /**
    * The index of the current player.
    */
   private int current;

   /**
    * Instantiate a new game with two computer players.
    * This is useful for a demo.
    */
   public Game() {
   
      // Input will come from the keyboard
      input = new Scanner(System.in);

      // Create array of two computer players
      players = new Player[2];
      players[0] = new Computer("Player A");
      players[1] = new Computer("Player B");

      // Set up the draw pile, deal out the hands, set up discard pile
      initializeGame();
   }

   /**
    * Instantiate a new game with a specified number of users
    * between 2 and 5, inclusive.
    * @param numPlayers the number of (human) players
    * @param in the Scanner used for collecting input
    */
   public Game(int numPlayers, Scanner in) {

      // Allow only 2, 3, 4 or 5 players
      if (numPlayers < 2 || numPlayers > 5) {
         throw new IllegalArgumentException("invalid number of players: " 
            + numPlayers);
      }

      // Initialize the input instance variable using specified argument
      input = in;
      
      // Create an array of Players of exactly the right size
      players = new Player[numPlayers];

      // Collect type and name info and create each Player
      addPlayers();
      
      // Set up the draw pile, deal out the hands, set up discard pile
      initializeGame();
      
      System.out.println();
      System.out.println(LINE_OF_DASHES);
      System.out.println();
   }
   
   /**
    * Get player information (type and name) from input and add to game.
    */
   public void addPlayers() {

      // Collect info about each player and populate the Player array
      for (int p = 0; p < players.length; p++) {

         System.out.print("Enter player type (computer/human) " +
            "followed by name: ");
         String type = input.next();
         String name = input.nextLine().trim();

         // If user specfied anything other than "computer', player is human
         if ("computer".equalsIgnoreCase(type)) {
            players[p] = new Computer(name);
         } else {
            players[p] = new User(name, input);
         }
      }
   }


   /**
    * Initialize the state of the game.
    */
   private void initializeGame() {
   
      // Start current player number at -1 since we call 
      // nextPlayer() to advance to 0 before calling takeTurn()
      current = -1; 
         
      // Create and shuffle a pack (full deck)
      drawPile = new Deck("DrawPile");
      drawPile.shuffle();
   
      // Size of a hand differs depending on total number of players
      int handSize = 5;
      if (players.length == 2) {
         handSize = 7;
      } 
      
      
      // TODO: implement the missing portion of this method
      //
      // Here, you'll just need to add the part where cards
      // get dealt out to each player in round-robin fashion, i.e.,
      // deal one card to each player in player order, then a second 
      // card to each player in player order, then a third card, etc., 
      // until each player has the appropriate number of cards 





   
      // Move top card from draw pile into discard pile, face up
      discardPile = new CardCollection("DiscardPile");
      discardPile.addCard(drawPile.discard());
   }


   /**
    * Remove one card from draw pile and return it.
    * Note: If the draw pile is empty, the discard pile (not including
    * its the top card) is shuffled and becomes the new draw pile before
    * the card is drawn.
    * @return a card from the draw pile.
    */
   public Card draw() {
      // Empty draw pile means we need to reintroduce cards from discard pile
      if (drawPile.isEmpty()) {
         reshuffle();
      }
      return drawPile.discard();
   }


   /**
    * Move cards from the discard pile to the draw pile and shuffle.
    * The top card in the discard pile remains in the discard pile.
    * Note: this is to be done whenever the draw pile becomes empty.
    */
   private void reshuffle() {
   
      // Save the top card - it shouldn't be moved to drawPile
      Card top = discardPile.discard();
   
      // Move the rest of the cards from discardPile to drawPile
      while (!discardPile.isEmpty()) {
         drawPile.addCard(discardPile.discard());
      }
      
      // Put the top card back into discardPile
      discardPile.addCard(top);
   
      // Shuffle the draw pile
      drawPile.shuffle();
   }

   /**
    * Return the next player in player order. If the top card on the 
    * discard pile is a Queen, the next player is skipped (and this
    * method utilizes displaySkippedPlayer to output a message), the
    * game waits for the user to type enter, and then outputs the
    * current state of the game.
    * @return the next player
    */
   private Player nextPlayer() {



      // TODO: implement me
      return null; // Replace this stub


   }


   /**
    * Execute one player's turn.
    * @param player the player who should play now
    */
   private void takeTurn(Player player) {

      Card prev = discardPile.top();
      System.out.println(LINE_OF_DASHES);

      // Allow one player to make a move; put played card into discard pile   
      Card next = player.makeMove(this, prev);
      discardPile.addCard(next);
   
      // Report the played card
      System.out.println("*** " + player.getName() + " plays " + next);
      System.out.println(LINE_OF_DASHES);
      System.out.println();
   }

  /**
    * Determine if two cards match in the sense of Crazy Eights. Cards are
    * said to match if their rank is the same, or if their suit is the same
    * or if one of the cards has a rank of 8.
    * @param card1 the first card
    * @param card2 the second card
    * @return true if card1 matches card2, and false otherwise
    */
   public static boolean cardMatches(Card card1, Card card2) {



      //TODO: implement me
      return false; // Replace this stub
      
 
 
   }


   /**
    * Check if game is done after current player completes a turn.
    * @return true if the current player's hand is empty; false otherwise
    */
   private boolean gameOver() {

      
      // TODO: implement me
      return false; // Replace this stub
      
            
   }


   /**
    * Play the game until a winner is determined.
    */
   public void playGame() {
      
      Player player;
         
      // Keep playing until there's a winner
      do {
         displayGameState();
         player = nextPlayer();
         displayCurrentPlayerHand();
         takeTurn(player);
         waitForUser();
      } while (!gameOver());
      
      // Display the winner
      System.out.println(player.name + " wins!");
   }
   

   /**
    * Output the state of the game, including information about
    * both the draw pile and the discard pile.
    */
   private void displayGameState() {
   
      // Report draw pile information
      System.out.print(">>> Total remaining in draw pile: ");
      System.out.println(drawPile.size() + " cards");
      
      // Report discard pile information
      System.out.print(">>> Total in discard pile: ");
      System.out.println(discardPile.size() + " cards");
      System.out.println(">>> Last played (discarded) card: " 
         + discardPile.top());
   }

   
   /**
    * Output the contents of the hand of the current player.
    */
   private void displayCurrentPlayerHand() {
      System.out.print("\n>>> Current player ");
      System.out.print(players[current].getHand());
   }


   /**
    * Output a message indicating that a player's turn has been skipped.
    * @param playerName the name of the player whose turn has been skipped.
    */
   private void displaySkippedPlayer(String playerName) {
      System.out.println(LINE_OF_DASHES);
      System.out.println("*** " + playerName + "'s turn is skipped!");         
      System.out.println(LINE_OF_DASHES + "\n");
   }


   /**
    * Wait for the user to press enter.
    */
   private void waitForUser() {
      System.out.println(">>> Press Enter to continue! >>>\n");
      input.nextLine();
   }


}


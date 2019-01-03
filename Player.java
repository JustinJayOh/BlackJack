/**
* Player class for Blackjack Game.
*
* @author Justin Oh (jjoh1993@gmail.com)
* @version 01/02/2019
*/

public class Player {

   private static final int MAX = 13;
   private int card, cardValue = 0, cardValue1 = 0, hitNumber = 0, 
      win = 0, cardValueSplit = 0;
   private static int numberOfCards = 312;
   private static int[] randomCards = new int[312];
   private static int[] possibleCards = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
   private static int pot = 0, wager = 0;
   private double pocket, money;
   
   public int winToken = 0;
   
 // Constructor
 /** Initializes a player with bankroll of 1000. */
   public Player() {
      pocket = 1000;
   }
   
  // Methods
  /** Sets winValue to 1 to conclude card match up.
  * @param winValue to 1 for conclusion, 0 for inconclusive
  */
   public void setWin(int winValue) {
      win = winValue;
   }
   
  /** Returns an integer for winValue.
  * @return int for win
  */
   public int getWin() {
      return win;
   }
   
   /** Sets winValue to 0.
   */
   public void clearWin() {
      win = 0;
   }
	
   /** Returns sum of cards in player's hand.
   * @return int for cardValue
   */
   public int getHandSum() {
      return cardValue;
   }
   
   /** Returns number of cards in player's hand.
   * @return int for hitNumber
   */
   public int numberOfCards() {
      return hitNumber;
   }
	
   /** Returns a random card from ace to king and adds to hand.
   * @return int for cardValue
   */
   public int playerHit() {
      int i = ((int) (Math.random() * numberOfCards));
      int randomCard = randomCards[i];
      
      for (int j = i; j < randomCards.length - 1; j++) {
         randomCards[j] = randomCards[j + 1];
      }
      randomCards[numberOfCards - 1] = 0;
      numberOfCards--;
      
      if (randomCard == 1 && cardValue < 11) {
         randomCard = 11;
         cardValue += randomCard;
      }
      
      else if (randomCard == 0) {
         do {
            i = ((int) (Math.random() * numberOfCards));
            randomCard = randomCards[i];
         } while (randomCard == 0);
         cardValue += randomCard;
      }
      
      else {
         cardValue += randomCard;
      }
      hitNumber++;
      return randomCard;
   }

	
   /** Places a bet.
   * @param wager for bet amount
   */
   public void playerBet(int wager) {
      pot = wager * 2;
      pocket -= wager;
   }
   
   /** Clears the current bet to 0.
   */
   public void playerBetClear() {
      wager = pot / 2;
      pocket += wager;
   }
   
   /** Conclusion: both players' hands equal.
   * Returns bet to player' pocket
   */
   public void push() {
      pocket += pot / 2;
   }
   
   /** Returns money in Player's pocket.
   * @return double for pocket
   */
   public double getPocket() {
      return pocket;
   }
   
   /** Sets pocket to amount.
   * @param money
   */
   public void setPocket(double money) {
      pocket = money;
   }
   
   /** Adds amount to pocket.
   * @param money 
   */
   public void addPocket(double money) {
      pocket += money;
   }
   
   /** Player wins pot.
   */
   public void winPot() {
      pocket += pot;
   }
   
   /** Conclusion: Player hits blackjack.
   */
   public void blackjack() {
      pocket += pot * 1.25;
   }
   
   /** Clears players hand to 0.
   */
   public void clearCards() {
      cardValue = 0;
   }
   
   /** Returns number of cards.
   */
   public int deckNumber() {
      return numberOfCards;
   }
   
   /** Reshuffles the deck
   * @return String if shuffled
   */
   public static String deckCheck() {
      String output = "";
      int i = 0;
      if (numberOfCards <= 78) {
         resetDeck();
         output = "---------- Deck was Shuffled ----------";
      }
      return output;   
   }
   
   /** Sets deck back to 6-deck
   */
   public static void resetDeck() {
      for (int i = 0; i < randomCards.length; i++) {
         int j = i % 13;
         randomCards[i] = possibleCards[j];
      }
   }
   
  
}
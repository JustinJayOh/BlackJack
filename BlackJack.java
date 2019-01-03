/** 
* Main program for Blackjack game.
*
* @author Justin Oh (jjoh1993@gmail.com)
* @version 01/02/2019
*/

import java.util.Scanner;

public class BlackJack {

   public static void main(String[] args) {
   	
      Scanner scan = new Scanner(System.in);
      Player Me = new Player();
      Player Dealer = new Player();
      int doubleBet = 0, bet = 0;
   	
      int userInput;
   	
      // Initalize game
      System.out.println("Game Start");
      System.out.println("You Start with $ " + Me.getPocket());
      System.out.println("Dealing...");
      Me.resetDeck();
   	
      // Loop for Betting and Game 
      do {
      
      // Initialize cards for hand
         Me.playerHit();
         Me.playerHit();
         Dealer.playerHit();
      
      // Set bet amount
         System.out.print("Enter an amount to bet: ");
         bet = scan.nextInt();
      
      // Checks for valid bet amount
         while (bet > Me.getPocket() || bet <= 0) {
            System.out.println("Invalid Bet Amount.");
            System.out.print("Enter an amount to bet: ");
            bet = scan.nextInt();
         }
         
      // Initialize Double Down Variable to Twice Bet Amount
         doubleBet =  2 * bet;
         
      // Initial hand for player and dealer
         Me.playerBet(bet);
         System.out.println("\tYou have a hand of " + Me.getHandSum());
         System.out.println("\tDealer is showing " + Dealer.getHandSum());
         
      // Dealer Hole Card
         Dealer.playerHit();
      
      // Loop for player hit or stay
         do {
            Player.deckCheck();
            System.out.print("Press 1 to hit, 2 to stay, 3 to double down: ");
            userInput = scan.nextInt();
            
         
            switch (userInput) {
               // Player Hit
               case 1:
                  Me.playerHit(); 
                  System.out.println("\tYou have a hand of " + Me.getHandSum());
                  break;
               // Player Stay
               case 2:
                  break;
               // Double Down
               case 3: 
                  Me.playerBetClear();
                  if (doubleBet <= Me.getPocket()) {
                     Me.playerBet(doubleBet);
                     Me.playerHit();
                     System.out.println("\tYou have a hand of " + Me.getHandSum());
                     userInput = 2;
                     break;
                  }
                  if (doubleBet > Me.getPocket() || doubleBet < 0) {
                     System.out.println("Insufficient Bankroll");
                     Me.playerBet(bet);
                     break;
                  }
               // Split
               case 4:
                  Me.playerBetClear();
                  if (doubleBet <= Me.getPocket()) {
                     Me.playerBet(doubleBet);
                  
                  }
                  if (doubleBet > Me.getPocket() || doubleBet < 0) {
                     System.out.println("Insufficient Bankroll");
                     break;
                  }
               // Incorrect Input
               default:
                  System.out.println("Incorrect Input");
                  break;     
            }
         } while (userInput != 2);
      
      // Dealer intelligence (to build out)
         while (Dealer.getHandSum() < 17) {
            Dealer.playerHit();
         }
         System.out.println("\tDealer has a hand of " + Dealer.getHandSum());
      
      // Player bust
         if (Me.getHandSum() > 21 && Me.getWin() == 0) {
            System.out.println("You Bust");
            System.out.println("You Lost");
            Me.setWin(1);
            Me.clearCards();
            Dealer.clearCards();
            System.out.println("You currently have " + Me.getPocket());
         }
      
      // Player blackjack
         if (Me.getHandSum() == 21 && Dealer.getHandSum() != 21 
         && Me.getWin() == 0) {
            System.out.println("BLACKJACK");
            Me.blackjack();
            Me.setWin(1);
            Me.clearCards();
            Dealer.clearCards();
            System.out.println("You currently have " + Me.getPocket());
         }
         
      // Dealer bust
         if (Dealer.getHandSum() > 21 && Me.getWin() == 0) {
            System.out.println("Dealer Bust");
            System.out.println("You Won");
            Me.winPot();
            Me.setWin(1);
            Me.clearCards();
            Dealer.clearCards();
            System.out.println("You currently have " + Me.getPocket());
         }
         
      // Dealer blackjack
         if (Dealer.getHandSum() == 21 && Me.getHandSum() != 21 
         && Me.getWin() == 0) {
            System.out.println ("DEALER BLACKJACK");
            Me.setWin(1);
            Me.clearCards();
            Dealer.clearCards();
            System.out.println("You currently have " + Me.getPocket());
         }
         
      // Player wins Dealer   
         if (Me.getHandSum() <= 21 && Dealer.getHandSum() <= 21 
         && Me.getHandSum() > Dealer.getHandSum() && Me.getWin() == 0) {
            System.out.println("You Won");
            Me.winPot();
            Me.setWin(1);
            Me.clearCards();
            Dealer.clearCards();
            System.out.println("You currently have " + Me.getPocket());
         }
         
      // Dealer beats player
         if (Me.getHandSum() <= 21 && Dealer.getHandSum() <= 21 
         && Me.getHandSum() < Dealer.getHandSum() && Me.getWin() == 0) {
            System.out.println("You Lost");
            Me.setWin(1);
            Me.clearCards();
            Dealer.clearCards();
            System.out.println("You currently have " + Me.getPocket());
         }
         
      // Push
         if (Me.getHandSum() <= 21 && Dealer.getHandSum() <= 21 
         && Me.getHandSum() == Dealer.getHandSum() && Me.getWin() == 0) {
            System.out.println("Push");
            Me.setWin(1);
            Me.push();
            Me.clearCards();
            Dealer.clearCards();
            System.out.println("You currently have " + Me.getPocket());
         }
            
      // Clears win for next game
         Me.clearWin();
         Me.deckCheck();
         
      } while (Me.getPocket() != 0);
      
      
      
      System.out.println("You Lost Your Bankroll \nGame Over!");
   } 
}
package com.txtlearn.blackjackdemo02;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;

public class Blackjack {
	
	public static void main(String[] args) throws InterruptedException{
		
		System.out.println(" _     _            _    _            _    \r\n" + 
				"| |   | |          | |  (_)          | |   \r\n" + 
				"| |__ | | __ _  ___| | ___  __ _  ___| | __\r\n" + 
				"| '_ \\| |/ _` |/ __| |/ / |/ _` |/ __| |/ /\r\n" + 
				"| |_) | | (_| | (__|   <| | (_| | (__|   < \r\n" + 
				"|_.__/|_|\\__,_|\\___|_|\\_\\ |\\__,_|\\___|_|\\_\\\r\n" + 
				"                       _/ |                \r\n" + 
				"                      |__/         ");
		
		//playingDeck will be the deck the dealer holds
		Deck playingDeck = new Deck();
		playingDeck.createFullDeck();
		playingDeck.shuffle();
		
		//playerCards will be the cards the player has in their hand
		Deck playerCards = new Deck();
		//playerMoney holds players cash - we will be lazy and use doubles instead of bigdecimals
		double playerMoney = 100.0;
		//dealerCards will be the cards the dealer has in their hand
		Deck dealerCards = new Deck();
		
		//Scanner for user input
		//Scanner for user input
		Scanner userInput = new Scanner(System.in);
		
		//Play the game while the player has money
		//Game loop
while(playerMoney>0){
	//Take Bet
	System.out.println("Je hebt " + playerMoney + " Euro, hoeveel wil je inzetten?");
	double playerBet = userInput.nextDouble();
	boolean endRound = false;
	if(playerBet > playerMoney){
		//Break if they bet too much
		System.out.println("Je kan niet meer inzetten dan je hebt. DOEI");
		break;
	}
	
	System.out.println("\nDe kaarten worden uitgedeeld... \n");
	TimeUnit.SECONDS.sleep(1);
	
	//Player gets two cardsa
	playerCards.draw(playingDeck);
	playerCards.draw(playingDeck);
	
	//Dealer gets two cards
	dealerCards.draw(playingDeck);
	//dealerCards.draw(playingDeck);
			
			//While loop for drawing new cards
			while(true)
			{
				//Display player cards
				System.out.println("_______________________________________________");
				System.out.println("Spelers hand:" + playerCards.toString());
				//Display Value
				System.out.println("De hand is " + playerCards.cardsValue() +" punten waard" );
				System.out.println("_______________________________________________");
				TimeUnit.SECONDS.sleep(1);
				//Display dealer cards
				System.out.println("Dealer hand: \n" + dealerCards.getCard(0).toString() + " \n[VERBOGEN KAART]");
				System.out.println("De dealer hand is " + dealerCards.cardsValue() +" punten waard" );
				System.out.println("_______________________________________________");
				//What do they want to do
				System.out.println("Wil je nog een kaart(1), of blijf je staan?(2)");
				int response = userInput.nextInt();	
				//They hit
				if(response == 1){
					playerCards.draw(playingDeck);
					System.out.println("\nJe hebt een:" + playerCards.getCard(playerCards.deckSize()-1).toString() + " gekregen");
					//Bust if they go over 21
					if(playerCards.cardsValue() > 21){
						System.out.println("JE HEBT VERLOREN : " + playerCards.cardsValue());
						playerMoney -= playerBet;
						endRound = true;
						break;
					}
				}
				
				//Stand
				if(response == 2){
					break;
				}
				
			}
				
			//Reveal Dealer Cards
			System.out.println("Dealer kaart " + dealerCards.toString());
			//See if dealer has more points than player
			if((dealerCards.cardsValue() > playerCards.cardsValue())&&endRound == false){
				System.out.println("Dealer verslaat je " + dealerCards.cardsValue() + " to " + playerCards.cardsValue());
				playerMoney -= playerBet;
				endRound = true;
			}
			//Dealer hits at 16 stands at 17
			while((dealerCards.cardsValue() < 17) && endRound == false){
				dealerCards.draw(playingDeck);
				System.out.println("Dealer kaart " + dealerCards.getCard(dealerCards.deckSize()-1).toString());
			}
			//Display value of dealer
			System.out.println("Dealer hand waarde " + dealerCards.cardsValue());
			//Determine if dealer busted
			if((dealerCards.cardsValue()>21)&& endRound == false){
				System.out.println("De dealer is dood, jij wint!");
				playerMoney += playerBet;
				endRound = true;
			}
			//Determine if push
			if((dealerCards.cardsValue() == playerCards.cardsValue()) && endRound == false){
				System.out.println("Push.");
				endRound = true;
			}
			//Determine if player wins
			if((playerCards.cardsValue() > dealerCards.cardsValue()) && endRound == false){
				System.out.println("You win the hand.");
				playerMoney += playerBet;
				endRound = true;
			}
			else if(endRound == false) //dealer wins
			{
				System.out.println("Dealer wins.");
				playerMoney -= playerBet;
			}

			//End of hand - put cards back in deck
			playerCards.moveAllToDeck(playingDeck);
			dealerCards.moveAllToDeck(playingDeck);
			
		}
		//Game is over
		System.out.println(" VERLOREN JAMMER JOH!");
		
		//Close Scanner
		userInput.close();
		
	}
	
	
}

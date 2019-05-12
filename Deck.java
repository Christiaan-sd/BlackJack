package com.txtlearn.blackjackdemo02;

import java.util.ArrayList;
import java.util.Random;

public class Deck {

	private ArrayList<Card> cards;
	
	public Deck(){
		//Create a new deck of playing cards
		this.cards = new ArrayList<Card>();
	
	}
	
	//Add 52 playing cards to a deck
	public void createFullDeck(){
		//Generate Cards
		//Loop Through Suits
		for(Suit cardSuit : Suit.values()){
			//Loop through Values
			for(Value cardValue : Value.values()){
				//Add new card to the mix
				this.cards.add(new Card(cardSuit,cardValue));
			}
		}
	}
	
	
//Shuffle deck of cards
public void shuffle(){
	//Create a new arraylist to hold the shuffled cards temporarily
	ArrayList<Card> tmpDeck = new ArrayList<Card>();
	//Randomly pick from the old deck and copy values to the new deck
	Random random = new Random();
	int randomCardIndex = 0;
	int originalSize = this.cards.size();
	for(int i = 0; i<originalSize;i++){
		//gen random num according to int randomNum = rand.nextInt((max - min) + 1) + min;
		randomCardIndex = random.nextInt((this.cards.size()-1 - 0) + 1) + 0;
		//throw random card into new deck
		tmpDeck.add(this.cards.get(randomCardIndex));
		//remove picked from old deck
		this.cards.remove(randomCardIndex);
	}
	//set this.deck to our newly shuffled deck
	this.cards = tmpDeck;
}
	
	
	//Remove a card from the deck
	public void removeCard(int i){
		this.cards.remove(i);
	}
	//Get card from deck
	public Card getCard(int i){
		return this.cards.get(i);
	}
	
	//Add card to deck
	public void addCard(Card addCard){
		this.cards.add(addCard);
	}
	
	//Draw a top card from deck
	public void draw(Deck comingFrom){
		//Add card to this deck from whatever deck its coming from
		this.cards.add(comingFrom.getCard(0));
		//Remove the card in the deck its coming from
		comingFrom.removeCard(0);
	}
	
	//Use to print out deck
	public String toString(){
		String cardListOutput = "";
		int i = 0;
		for(Card aCard : this.cards){
			cardListOutput += "\n" + aCard.toString();
			i++;
		}
		return cardListOutput;
	}
	
	public void moveAllToDeck(Deck moveTo){
		int thisDeckSize = this.cards.size();
		//put cards in moveTo deck
		for(int i = 0; i < thisDeckSize; i++){
			moveTo.addCard(this.getCard(i));
		}
		//empty out the deck
		for(int i = 0; i < thisDeckSize; i++){
			this.removeCard(0);
		}
	}
	
	public int deckSize(){
		return this.cards.size();
	}
	
	//Calculate the value of deck
	public int cardsValue(){
		int totalValue = 0;
		int aces = 0;
		//For every card in the deck
		for(Card aCard : this.cards){
			//Switch of possible values
			switch(aCard.getValue()){
			case TWEE: totalValue += 2; break;
			case DRIE: totalValue += 3; break;
			case VIER: totalValue += 4; break;
			case VIJF: totalValue += 5; break;
			case ZES: totalValue += 6; break;
			case ZEVEN: totalValue += 7; break;
			case ACHT: totalValue += 8; break;
			case NEGEN: totalValue += 9; break;
			case TIEN: totalValue += 10; break;
			case BOER: totalValue += 10; break;
			case VROUW: totalValue += 10; break;
			case KONING: totalValue += 10; break;
			case As: aces += 1; break;
			}			
		}
		
		//Determine the total current value with aces
		//Aces worth 11 or 1 - if 11 would go over 21 make it worth 1
		for(int i = 0; i < aces; i++){
			//If they're already at over 10 getting an ace valued at 11 would put them up to 22, so make ace worth one
			if (totalValue > 10){
				totalValue += 1;
			}
			else{
				totalValue += 11;
			}
		}
		
		//Return
		return totalValue;
	
	}
	
	
}

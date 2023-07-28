package unoquattro;

/**
*
* @author Jinhwan Kim_20225927 13/07/2022
*/

import java.util.*;
import java.util.ArrayList;

//QuattroGame class controls the flow of real games.
public class QuattroGameAdd {

    // This game calls key methods in our sample solution. 
    // You can implement in your own ways.
    // Please refer QuattroGameTester class for more information
    // If you choose to implement these method, uncomment it so the tester can call.
    
	private ArrayList<Card> cardListStack = new ArrayList<Card>(); //A collection of cards for draw pile
	private ArrayList<Card> cardListDiscard = new ArrayList<Card>(); //A collection of cards for discard pile
	private ArrayList<Player> playerList= new ArrayList<Player>(); //A collection of players(1 for human, and 2, 3, 4, are bots.)
	private int numberOfPlayers = 4;  
	private int startingCardNumber;
	private int cardCodeSeed = 0;
	private boolean isValidCard;	
	private boolean check = true;
	
	SpecialColourCard sc2wild1 = new SpecialColourCard(61, "Red", "draw2");
	SpecialColourCard sc4wild2 = new SpecialColourCard(62, "Green", "draw4");
	WildCard wcReverse = new WildCard(63, "reverse");
	WildCard wcSkip = new WildCard(64, "skip");
	
	//the method to control the order of play the game and it will be invocated in the main method in the QuattroGameTester 
    public void playGame(){
        createCards(); //create cards for play
        createPlayers(); //create players in the game
        dealCards(); //deal cards to players
        playFirstCard(); //turn over top card
        playByTurn(); //play cards until a winner is determined
    }
	
    //recursion 연습
    public ArrayList<Player> reverse(ArrayList<Player> arrayList) {
    	//base condition when the list size is 1
    	if (arrayList.size() > 1) {
    		Player value = arrayList.remove(0);
    		reverse(arrayList);
    		arrayList.add(value);
    	} 
		return arrayList;    	
    }
    
    //Creating the deck of cards.(Total 40 cards.)
	private void createCards() { 
		for (int i = 0; i < 10; i++) {
			StandardColourCard standardColourRedi = new StandardColourCard(i + 1, "Red", i);
			StandardColourCard standardColourGreeni = new StandardColourCard(i + 11, "Green", i);
			StandardColourCard standardColourBluei = new StandardColourCard(i + 21, "Blue", i);
			StandardColourCard standardColourYellowi = new StandardColourCard(i + 31, "Yellow", i);
			
			//extend the card
			StandardColourCard standardColourOrangei = new StandardColourCard(i + 41, "Orange", i);
			StandardColourCard standardColourPuplei = new StandardColourCard(i + 51, "Puple", i);
			StandardColourCard standardColourBlacki = new StandardColourCard(i + 71, "Black", i);
			
			cardListStack.add(standardColourRedi);
			cardListStack.add(standardColourGreeni);
			cardListStack.add(standardColourBluei);
			cardListStack.add(standardColourYellowi);
			cardListStack.add(standardColourBlacki);			
			
			//extend the card
			cardListStack.add(standardColourOrangei);
			cardListStack.add(standardColourPuplei);
			
		}				
		cardListStack.add(sc2wild1);
		cardListStack.add(sc4wild2);
		cardListStack.add(wcReverse); 
		cardListStack.add(wcSkip); 		
	}
	
	//Create four players and add them to the playerList  
	private void createPlayers() {		
		//creating instances the ArrayList cards for respective player 
		ArrayList<Card> playerCards1 = new ArrayList<Card>();
		ArrayList<Card> playerCards2 = new ArrayList<Card>();
		ArrayList<Card> playerCards3 = new ArrayList<Card>();
		ArrayList<Card> playerCards4 = new ArrayList<Card>();
		//extend the player
		ArrayList<Card> playerCards5 = new ArrayList<Card>();
		//Human player		
		Player player1 = new Player("", playerCards1, true);
		player1.setName("");
		playerList.add(player1);
		
		//Three bot players
		Player player2 = new Player("Player 2", playerCards2, false);
		playerList.add(player2);

		Player player3 = new Player("Player 3", playerCards3, false);
		playerList.add(player3);

		Player player4 = new Player("Player 4", playerCards4, false);
		playerList.add(player4);	
		
		//extend the player
		Player player5 = new Player("Player 5", playerCards5, false);
		playerList.add(player5);	
	}
	
	//Mix and deal cards to the all players 
	private void dealCards() {
	//shuffle the draw pile cards 
	Collections.shuffle(cardListStack); 		
		
		//deals cards to all of players (each 5cards)
		for (int i = 0; i < numberOfPlayers + 1; i++) { //cards number
			for (int j = 0; j <= numberOfPlayers; j++) { //to player 
				playerList.get(j).addCardToHand(cardListStack.get(j)); 
				cardListStack.remove(j);
			}
		}
	}

	//to show the first card to play to the user
	private void playFirstCard() {
		//First(top) card is moved from the draw pile to discard pile
		cardListDiscard.add(cardListStack.get(0));
		cardListStack.remove(0);
	}
		
	//play the game by turn of player until the winner is decided 
	private void playByTurn() {		
		while (true) {
			//repeat to play four players
			for (int i = 0; i < numberOfPlayers + 1; i++) {
				//if one of player's hand is not empty, the game will continue. 
				startingCardNumber = playerList.get(i).getCardList().size(); 				
				if(playerList.get(i).getCardList().size() != 0) {					
					//if player is a human, user's turn					
					if (playerList.get(i).getIsHuman() == true) {
						//The user re-enters the code number until get the right code
						do {			
							Scanner keyboard = new Scanner(System.in);
							isValidCard = false; 																	
							//user and card information that human is having in present
							System.out.println("Cards that " + playerList.get(i).getName() + " has are");
							for (int x = 0; x < playerList.get(i).getCardList().size(); x++) {
								//check the cards to indicate to the human player if there are no valid cards in hand
								if ((cardListDiscard.get(cardListDiscard.size() - 1).getCardCode() == playerList.get(i).getCardList().get(x).getCardCode())
										|| (cardListDiscard.get(cardListDiscard.size() - 1).getCardColour() == playerList.get(i).getCardList().get(x).getCardColour())
										|| (cardListDiscard.get(cardListDiscard.size() - 1).getCardNumber() == playerList.get(i).getCardList().get(x).getCardNumber())
										|| (cardListDiscard.get(cardListDiscard.size() - 1).getCardCode() == 63)
										|| (cardListDiscard.get(cardListDiscard.size() - 1).getCardCode() == 64)) {
									isValidCard = true;																
								}	
								if (playerList.get(i).getCardList().get(x).getCardCode() == sc2wild1.getCardCode() 
										|| playerList.get(i).getCardList().get(x).getCardCode() == sc4wild2.getCardCode()
										|| playerList.get(i).getCardList().get(x).getCardCode() == wcReverse.getCardCode()
										|| playerList.get(i).getCardList().get(x).getCardCode() == wcSkip.getCardCode()) {
									isValidCard = true;	
								}
								if (playerList.get(i).getCardList().get(x).getCardCode() == 61) {
									System.out.println("Card Code:" + sc2wild1.getCardCode() + " - " + sc2wild1.getString()); 
								}
								else if (playerList.get(i).getCardList().get(x).getCardCode() == 62) {
									System.out.println("Card Code:" + sc4wild2.getCardCode() + " - " + sc4wild2.getString());
								}
								else if (playerList.get(i).getCardList().get(x).getCardCode() == 63) {
									System.out.println(wcReverse.getString());
								}
								else if (playerList.get(i).getCardList().get(x).getCardCode() == 64) {
									System.out.println(wcSkip.getString());
								}
								else 
									System.out.println(playerList.get(i).getCardList().get(x).getString());								
																										
							}
							if (isValidCard == false) {
								System.out.println("There is no valid card. Press '0' to get the card from the draw pile.");
							}	
							System.out.println();
					
							//First(top) card is show
							System.out.println("The top card is: ");			
							
							if (cardListDiscard.get(cardListDiscard.size() - 1).getCardCode() == 61) {
								System.out.println(sc2wild1.getCardCode() + " " + sc2wild1.getString());
								System.out.println("Enter the Card Code of the card you would like to play");
								System.out.println("If you don't have a valid card enter 0");
							}
							else if (cardListDiscard.get(cardListDiscard.size() - 1).getCardCode() == 62) {
								System.out.println(sc4wild2.getCardCode() + " " + sc4wild2.getString());
								System.out.println("Enter the Card Code of the card you would like to play");
								System.out.println("If you don't have a valid card enter 0");
							}
							else if (cardListDiscard.get(cardListDiscard.size() - 1).getCardCode() == 63) {
								System.out.println(wcReverse.getCardCode() + " " + wcReverse.getSpecialAbility());
								System.out.println("Enter the Card Code of the card you would like to play");
								System.out.println("If you don't have a valid card enter 0");
							}
							else if (cardListDiscard.get(cardListDiscard.size() - 1).getCardCode() == 64) {
								System.out.println(wcSkip.getCardCode() + " " + wcSkip.getSpecialAbility());
								System.out.println("Enter the Card Code of the card you would like to play");
								System.out.println("If you don't have a valid card enter 0");
							}
							else {
								System.out.println(cardListDiscard.get(cardListDiscard.size() - 1).getCardColour() + " " 
										+ cardListDiscard.get(cardListDiscard.size() - 1).getCardNumber());
								System.out.println("Enter the Card Code of the card you would like to play");
								System.out.println("If you don't have a valid card enter 0");
							}
							//if the human player enters the code, that value is saved in a cardCodeSeed variable
							cardCodeSeed = playerList.get(i).exceptionHandling(cardCodeSeed);
							
							//exceptions if the human player makes an invalid selection
							try {												
								//if there is valid card in hand to submit	
								isValidCard = false; 
								if (cardCodeSeed != 0) {								
									for (int x = 0; x < playerList.get(i).getCardList().size(); x++) {												
										//human plays the valid card into the discard pile
										if (cardCodeSeed == playerList.get(i).getCardList().get(x).getCardCode()) {
											//When I put out 2 cards for punishment to next player
											if (playerList.get(i).getCardList().get(x).getCardCode() == 61) {  
												isValidCard = true;	
												startingCardNumber--;
												//Place the wildcard in discard 
												cardListDiscard.add(playerList.get(i).getCardList().get(x));
												playerList.get(i).removeCardFromHand(playerList.get(i).getCardList().get(x));	
												System.out.println(playerList.get(i).getName() + " has played " + sc2wild1.getString());
												System.out.println(playerList.get(i).getName() + " has the following number of cards: " + startingCardNumber);
												//Next turn adds 2 cards
												if (i >= 4) {
													for (int j = 0; j < 2; j++) {
														playerList.get(0).addCardToHand(cardListStack.get(0)); 
														cardListStack.remove(0);	
													}
													System.out.println("Two cards has been added to " + playerList.get(0).getName() + "'s hand.");
													System.out.println(playerList.get(0).getName() + " has the following number of cards now: " + playerList.get(0).getCardList().size());
													System.out.println();
													check = true;
													break;
												}
												else {
													for (int j = 0; j < 2; j++) {
														playerList.get(i + 1).addCardToHand(cardListStack.get(0)); 
														cardListStack.remove(0);
													}	
													System.out.println("Two cards has been added to " + playerList.get(i + 1).getName() + "'s hand.");
													System.out.println(playerList.get(i + 1).getName() + " has the following number of cards now: " + playerList.get(i + 1).getCardList().size());
													System.out.println();
													check = true;
													break;
												}																		
											}
											//When I put out 4 cards for punishment to next player
											else if (playerList.get(i).getCardList().get(x).getCardCode() == 62) {
												isValidCard = true;	
												startingCardNumber--;
												//Place the wildcard in discard 
												cardListDiscard.add(playerList.get(i).getCardList().get(x));
												playerList.get(i).removeCardFromHand(playerList.get(i).getCardList().get(x));	
												System.out.println(playerList.get(i).getName() + " has played " + sc4wild2.getString());
												System.out.println(playerList.get(i).getName() + " has the following number of cards: " + startingCardNumber);
												//Next turn adds 4 cards
												if (i >= 4) {
													for (int j = 0; j < 4; j++) {
														playerList.get(0).addCardToHand(cardListStack.get(0)); 
														cardListStack.remove(0);	
													}
													System.out.println("Four cards has been added to " + playerList.get(0).getName() + "'s hand.");
													System.out.println(playerList.get(0).getName() + " has the following number of cards now: " + playerList.get(0).getCardList().size());
													System.out.println();
													check = true;
													break;
												}
												else {
													for (int j = 0; j < 4; j++) {
														playerList.get(i + 1).addCardToHand(cardListStack.get(0)); 
														cardListStack.remove(0);
													}	
													System.out.println("Four cards has been added to " + playerList.get(i + 1).getName() + "'s hand.");
													System.out.println(playerList.get(i + 1).getName() + " has the following number of cards now: " + playerList.get(i + 1).getCardList().size());
													System.out.println();
													check = true;
													break;
												}								
											}
											//When I put out reverse card
											else if (playerList.get(i).getCardList().get(x).getCardCode() == 63) {
												isValidCard = true;	
												startingCardNumber--;
												//Place the wildcard in discard 
												cardListDiscard.add(playerList.get(i).getCardList().get(x));
												playerList.get(i).removeCardFromHand(playerList.get(i).getCardList().get(x));	
												System.out.println(playerList.get(i).getName() + " has played " + wcReverse.getString());
												wcReverse.activateAbility();												
												System.out.println(playerList.get(i).getName() + " has the following number of cards now: " + startingCardNumber);
												System.out.println();
												reverse(playerList);	
												int temp = i;
												for (temp = 0; temp < 2 * i + 1; temp++) {
													Player lastIndex = playerList.remove(playerList.size() - 1);
													playerList.add(0, lastIndex);    		
												}
												check = true;
												break;
											}
											//When I put out skip card
											else if (playerList.get(i).getCardList().get(x).getCardCode() == 64) {
												isValidCard = true;	
												startingCardNumber--;
												//Place the wildcard in discard 
												cardListDiscard.add(playerList.get(i).getCardList().get(x));
												playerList.get(i).removeCardFromHand(playerList.get(i).getCardList().get(x));	
												System.out.println(playerList.get(i).getName() + " has played " + wcSkip.getString());
												wcSkip.activateAbility();												
												System.out.println(playerList.get(i).getName() + " has the following number of cards now: " + startingCardNumber);
												System.out.println();
												int temp = i;
												if (i >= 4) {
													temp = 0;
													i = temp;													
												}
												else {
													temp = i + 1;
													i = temp;														
												}																							
												check = true;
												break;
											}											
											
											else if (cardListDiscard.get(cardListDiscard.size() - 1).getCardCode() == 61 || cardListDiscard.get(cardListDiscard.size() - 1).getCardCode() == 62
													|| cardListDiscard.get(cardListDiscard.size() - 1).getCardCode() == 63
													|| cardListDiscard.get(cardListDiscard.size() - 1).getCardCode() == 64) {
												if (cardListDiscard.get(cardListDiscard.size() - 1).getCardColour() == playerList.get(i).getCardList().get(x).getCardColour()
														|| cardListDiscard.get(cardListDiscard.size() - 1).getCardCode() == 63
														|| cardListDiscard.get(cardListDiscard.size() - 1).getCardCode() == 64) {
													isValidCard = true;	
													System.out.println(playerList.get(i).getName() + " has played " + playerList.get(i).getCardList().get(x).getCardColour() 
															+ " " + playerList.get(i).getCardList().get(x).getCardNumber());
													cardListDiscard.add(playerList.get(i).getCardList().get(x));
													playerList.get(i).removeCardFromHand(playerList.get(i).getCardList().get(x));	
													startingCardNumber--;
													System.out.println(playerList.get(i).getName() + " has the following number of cards: " + startingCardNumber);
													check = true;
													break;
												}
											}
											//when the card on the discard pile has matching with the card on my hand 
											else if ((cardListDiscard.get(cardListDiscard.size() - 1).getCardColour() == playerList.get(i).getCardList().get(x).getCardColour())
													|| (cardListDiscard.get(cardListDiscard.size() - 1).getCardNumber() == playerList.get(i).getCardList().get(x).getCardNumber())) {												
												isValidCard = true;	
												System.out.println(playerList.get(i).getName() + " has played " + playerList.get(i).getCardList().get(x).getCardColour() 
														+ " " + playerList.get(i).getCardList().get(x).getCardNumber());
												cardListDiscard.add(playerList.get(i).getCardList().get(x));
												playerList.get(i).removeCardFromHand(playerList.get(i).getCardList().get(x));	
												startingCardNumber--;
												System.out.println(playerList.get(i).getName() + " has the following number of cards: " + startingCardNumber);
												check = true;
												break;
											}																						
											//if human puts the card but, that card doesn't match with the card on the discard pile. Exception will arise
											else {
												check = false;	
												throw new NoSuchElementException("Please enter the matching code with the card on the discard pile");
											}
										}											
									}
									//if human doesn't select the card code that he has in hand, exception will arise
									if (isValidCard == false) {
										check = false;	
										throw new ArrayIndexOutOfBoundsException("The code you entered is not in your hand");				
									}																
									
									//if one of human's hand is empty, the game will be finished. 
									if (playerList.get(i).getCardList().isEmpty() == true) {
										System.out.println("Player " + playerList.get(i).getName() + " is the winner");
										System.exit(0);
									}
									System.out.println();									
								}
								//if there is not valid card in human's hand to play
								else {
									isValidCard = false;
									//if human entered 0 but, there is a valid card in hand. Exception will arise
									for (int x = 0; x < playerList.get(i).getCardList().size(); x++) {
										if ((cardListDiscard.get(cardListDiscard.size() - 1).getCardColour() == playerList.get(i).getCardList().get(x).getCardColour())
												|| (cardListDiscard.get(cardListDiscard.size() - 1).getCardNumber() == playerList.get(i).getCardList().get(x).getCardNumber())) {											
											check = false;											
											throw new Exception("You have a valid card in your hand");																				
										}
									}
									//if draw pile cards are empty, move the all cards from the discard pile and mix again 
									if (cardListStack.size() <= 4) {
										//move the all cards from the discard pile to draw pile
										cardListStack.addAll(cardListDiscard);
										cardListDiscard.removeAll(cardListDiscard);
										//move the last card of the draw pile(It was the top card of discard pile)
										cardListDiscard.add(cardListStack.get(cardListStack.size() - 1));
										cardListStack.remove(cardListStack.get(cardListStack.size() - 1));
										//shuffle the draw pile cards 
										Collections.shuffle(cardListStack); 
									}
									
									//human gets a card from the cardListStack											
									if (playerList.get(i).getCardList().size() == 1) {
										playerList.get(i).addCardToHand(cardListStack.get(0)); 
										cardListStack.remove(0);	
										startingCardNumber++;
										playerList.get(i).addCardToHand(cardListStack.get(0)); 
										cardListStack.remove(0);
										startingCardNumber++;
										System.out.println("Two cards has been added to your hand!!! :F");
										for (int x = 0; x < playerList.get(i).getCardList().size(); x++) {
											if (playerList.get(i).getCardList().get(x).getCardCode() == 61) {
												System.out.println("Card Code:" + sc2wild1.getCardCode() + " - " + sc2wild1.getString()); 
											}
											else if (playerList.get(i).getCardList().get(x).getCardCode() == 62) {
												System.out.println("Card Code:" + sc4wild2.getCardCode() + " - " + sc4wild2.getString());
											}
											else if (playerList.get(i).getCardList().get(x).getCardCode() == 63) {
												System.out.println(wcReverse.getString());
											}
											else if (playerList.get(i).getCardList().get(x).getCardCode() == 64) {
												System.out.println(wcSkip.getString());
											}										
											else 
												System.out.println(playerList.get(i).getCardList().get(x).getString());	
										}
										System.out.println(playerList.get(i).getName() + " has the following number of cards now: " + startingCardNumber);										
										System.out.println();
										check = true;
										break;
									}	
									else {
										playerList.get(i).addCardToHand(cardListStack.get(0)); 
										cardListStack.remove(0);	
										startingCardNumber++;
										System.out.println("A card has been added to your hand.");
										System.out.println("Cards that " + playerList.get(i).getName() + " has are");		
										for (int x = 0; x < playerList.get(i).getCardList().size(); x++) {
											if (playerList.get(i).getCardList().get(x).getCardCode() == 61) {
												System.out.println("Card Code:" + sc2wild1.getCardCode() + " - " + sc2wild1.getString()); 
											}
											else if (playerList.get(i).getCardList().get(x).getCardCode() == 62) {
												System.out.println("Card Code:" + sc4wild2.getCardCode() + " - " + sc4wild2.getString());
											}
											else if (playerList.get(i).getCardList().get(x).getCardCode() == 63) {
												System.out.println(wcReverse.getString());
											}
											else if (playerList.get(i).getCardList().get(x).getCardCode() == 64) {
												System.out.println(wcSkip.getString());
											}										
											else 
												System.out.println(playerList.get(i).getCardList().get(x).getString());	
										}
										System.out.println(playerList.get(i).getName() + " has the following number of cards now: " + startingCardNumber);
										System.out.println();
										check = true;
										break;
									}								
								}
							} 
							catch (Exception e) {	
								System.out.println();
								System.out.println(e.getMessage());						
								System.out.println("Please input the right code!");	 
								System.out.println();
							}	
						} while (!check);
					}
					
					//if player is not a human, computer's turn
					else {
						isValidCard = false;
						//checking the bot player's card whether they have the matching card 
						for (int j = 0; j < playerList.get(i).getCardList().size(); j++) {
							//When the computer has a wild card (2 cards penalty)
							if (playerList.get(i).getCardList().get(j).getCardCode() == 61) {
								isValidCard = true;	
								//Place the wildcard in discard and the
								System.out.println("Player " + playerList.get(i).getName() + " has played the following card: ");
								System.out.println(sc2wild1.getString());
								sc2wild1.activateAbility();
								cardListDiscard.add(playerList.get(i).getCardList().get(j));
								playerList.get(i).removeCardFromHand(playerList.get(i).getCardList().get(j));	
								startingCardNumber--;
								System.out.println("Player " + playerList.get(i).getName() + " has the folloing number of cards: " + playerList.get(i).getCardList().size());
								//if one of computer's hand is empty, the game will be finished. 
								if (playerList.get(i).getCardList().isEmpty() == true) {
									System.out.println("Player " + playerList.get(i).getName() + " is the winner");
									System.exit(0);
								}	
								System.out.println();
								//Next turn adds 2 cards
								if (i >= 4) {
									playerList.get(0).addCardToHand(cardListStack.get(0)); 
									cardListStack.remove(0);													
									playerList.get(0).addCardToHand(cardListStack.get(0)); 
									cardListStack.remove(0);
									System.out.println("Two cards has been added to " + playerList.get(0).getName() + "'s hand.");
									System.out.println(playerList.get(0).getName() + " has the following number of cards now: " + playerList.get(0).getCardList().size());
									System.out.println();
									isValidCard = true;	
									break;
								}
								else {
									playerList.get(i+1).addCardToHand(cardListStack.get(0)); 
									cardListStack.remove(0);													
									playerList.get(i+1).addCardToHand(cardListStack.get(0)); 
									cardListStack.remove(0);
									System.out.println("Two cards has been added to " + playerList.get(i+1).getName() + "'s hand.");
									System.out.println(playerList.get(i+1).getName() + " has the following number of cards now: " + playerList.get(i+1).getCardList().size());
									System.out.println();
									isValidCard = true;	
									break;
								}								
							}
							else if (playerList.get(i).getCardList().get(j).getCardCode() == 62) {
								isValidCard = true;	
								//discard wild card to the discard pile
								System.out.println("Player " + playerList.get(i).getName() + " has played the following card: ");
								System.out.println(sc4wild2.getString());
								sc4wild2.activateAbility();
								cardListDiscard.add(playerList.get(i).getCardList().get(j));
								playerList.get(i).removeCardFromHand(playerList.get(i).getCardList().get(j));	
								startingCardNumber--;
								System.out.println("Player " + playerList.get(i).getName() + " has the folloing number of cards: " + playerList.get(i).getCardList().size());
								//if one of computer's hand is empty, the game will be finished. 
								if (playerList.get(i).getCardList().isEmpty() == true) {
									System.out.println("Player " + playerList.get(i).getName() + " is the winner");
									System.exit(0);
								}	
								System.out.println();
								//Add 4 cards to the next player
								if (i >= 4) {
									for (int x = 0; x < 4; x++) {
										playerList.get(0).addCardToHand(cardListStack.get(0)); 
										cardListStack.remove(0);	
									}
									System.out.println("Four cards has been added to " + playerList.get(0).getName() + "'s hand.");
									System.out.println(playerList.get(0).getName() + " has the following number of cards now: " + playerList.get(0).getCardList().size());
									System.out.println();
									isValidCard = true;	
									break;
								}
								else {
									for (int x = 0; x < 4; x++) {
										playerList.get(i+1).addCardToHand(cardListStack.get(0)); 
										cardListStack.remove(0);
									}																				
									System.out.println("Four cards has been added to " + playerList.get(i+1).getName() + "'s hand.");
									System.out.println(playerList.get(i+1).getName() + " has the following number of cards now: " + playerList.get(i+1).getCardList().size());
									System.out.println();
									isValidCard = true;	
									break;
								}												
							}
							else if (playerList.get(i).getCardList().get(j).getCardCode() == 63) {
								isValidCard = true;	
								startingCardNumber--;
								//Place the wildcard in discard and the
								cardListDiscard.add(playerList.get(i).getCardList().get(j));
								playerList.get(i).removeCardFromHand(playerList.get(i).getCardList().get(j));	
								System.out.println("Player " + playerList.get(i).getName() + " has played the following card: ");	
								System.out.println(wcReverse.getString());								
								wcReverse.activateAbility();
								System.out.println(playerList.get(i).getName() + " has the following number of cards now: " + playerList.get(i).getCardList().size());
								//if one of computer's hand is empty, the game will be finished. 
								if (playerList.get(i).getCardList().isEmpty() == true) {
									System.out.println("Player " + playerList.get(i).getName() + " is the winner");
									System.exit(0);
								}	
								System.out.println();
								//reverse players
								reverse(playerList);
								int temp = i;
								for (temp = 0; temp < 2 * i + 1; temp++) {
									Player lastIndex = playerList.remove(playerList.size() - 1);
									playerList.add(0, lastIndex);   													
								}
								break;
							}
							else if (playerList.get(i).getCardList().get(j).getCardCode() == 64) {
								isValidCard = true;	
								startingCardNumber--;
								//Place the wildcard in discard and the
								cardListDiscard.add(playerList.get(i).getCardList().get(j));
								playerList.get(i).removeCardFromHand(playerList.get(i).getCardList().get(j));	
								System.out.println("Player " + playerList.get(i).getName() + " has played the following card: ");	
								System.out.println(wcSkip.getString());								
								wcSkip.activateAbility();
								System.out.println(playerList.get(i).getName() + " has the following number of cards now: " + playerList.get(i).getCardList().size());
								//if one of computer's hand is empty, the game will be finished. 
								if (playerList.get(i).getCardList().isEmpty() == true) {
									System.out.println("Player " + playerList.get(i).getName() + " is the winner");
									System.exit(0);
								}	
								System.out.println();
								//skip the next player
								int temp = i;
								if (i >= 4) {
									temp = 0;
									i = temp;									
								}
								else {
									temp = i + 1;
									i = temp;	
								}														
								break;
							}
							else if (cardListDiscard.get(cardListDiscard.size() - 1).getCardCode() == 61 || cardListDiscard.get(cardListDiscard.size() - 1).getCardCode() == 62
									|| cardListDiscard.get(cardListDiscard.size() - 1).getCardCode() == 63
									|| cardListDiscard.get(cardListDiscard.size() - 1).getCardCode() == 64) {
								if (cardListDiscard.get(cardListDiscard.size() - 1).getCardColour() == playerList.get(i).getCardList().get(j).getCardColour()
										|| cardListDiscard.get(cardListDiscard.size() - 1).getCardCode() == 63
										|| cardListDiscard.get(cardListDiscard.size() - 1).getCardCode() == 64) {
									isValidCard = true;											
									System.out.println("Player " + playerList.get(i).getName() + " has played the following card: ");								
									System.out.println(playerList.get(i).getCardList().get(j).getCardColour() + " " + playerList.get(i).getCardList().get(j).getCardNumber());
									cardListDiscard.add(playerList.get(i).getCardList().get(j));
									playerList.get(i).removeCardFromHand(playerList.get(i).getCardList().get(j));	
									startingCardNumber--;									
									System.out.println("Player " + playerList.get(i).getName() + " has the folloing number of cards: " + playerList.get(i).getCardList().size());
									//if one of computer's hand is empty, the game will be finished. 
									if (playerList.get(i).getCardList().isEmpty() == true) {
										System.out.println("Player " + playerList.get(i).getName() + " is the winner");
										System.exit(0);
									}
									System.out.println();		
									break;
								}
							}
							//if bot player has the matching card to play
							else {									
								if ((cardListDiscard.get(cardListDiscard.size() - 1).getCardColour() == playerList.get(i).getCardList().get(j).getCardColour())
										|| (cardListDiscard.get(cardListDiscard.size() - 1).getCardNumber() == playerList.get(i).getCardList().get(j).getCardNumber())) {										
									isValidCard = true;											
									System.out.println("Player " + playerList.get(i).getName() + " has played the following card: ");								
									System.out.println(playerList.get(i).getCardList().get(j).getCardColour() + " " + playerList.get(i).getCardList().get(j).getCardNumber());
									cardListDiscard.add(playerList.get(i).getCardList().get(j));
									playerList.get(i).removeCardFromHand(playerList.get(i).getCardList().get(j));	
									startingCardNumber--;
									System.out.println("Player " + playerList.get(i).getName() + " has the folloing number of cards: " + playerList.get(i).getCardList().size());
									//if one of computer's hand is empty, the game will be finished. 
									if (playerList.get(i).getCardList().isEmpty() == true) {
										System.out.println("Player " + playerList.get(i).getName() + " is the winner");
										System.exit(0);
									}
									System.out.println();											
									break;
								}
							}
						}
						//if bot player doesn't have a card to play, take the card from the discard pile
						if (isValidCard == false) {
							//if draw pile cards are empty, move the all cards from the discard pile and mix again 
							if (cardListStack.size() <= 4) {
								//move the all cards from the discard pile to draw pile
								cardListStack.addAll(cardListDiscard);
								cardListDiscard.removeAll(cardListDiscard);
								//move the last card of the draw pile(It was the top card of discard pile)
								cardListDiscard.add(cardListStack.get(cardListStack.size() - 1));
								cardListStack.remove(cardListStack.get(cardListStack.size() - 1));
								//shuffle the draw pile cards 
								Collections.shuffle(cardListStack); 
							}								
							else if (playerList.get(i).getCardList().size() == 1) {
								playerList.get(i).addCardToHand(cardListStack.get(0)); 
								cardListStack.remove(0);
								startingCardNumber++;								
								playerList.get(i).addCardToHand(cardListStack.get(0)); 
								cardListStack.remove(0);
								startingCardNumber++;		
								System.out.println("Player " + playerList.get(i).getName() + " has picked up two cards!!");							
								System.out.println("Player " + playerList.get(i).getName() + " has the following number of cards: " + playerList.get(i).getCardList().size());
								System.out.println();
							}	
							else {
								playerList.get(i).addCardToHand(cardListStack.get(0)); 
								cardListStack.remove(0);
								startingCardNumber++;
								System.out.println("Player " + playerList.get(i).getName() + " has picked up a card");							
								System.out.println("Player " + playerList.get(i).getName() + " has the following number of cards: " + playerList.get(i).getCardList().size());
								System.out.println();
							}													
						}																											
					}			
				}			
			}
		}
	} 
}

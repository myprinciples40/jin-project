package unoquattro;

/**
*
* @author Jinhwan Kim_20225927
*/

import java.util.*;
import java.util.ArrayList;

//QuattroGame class controls the flow of real games.
public class QuattroGame{

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
	
	//the method to control the order of play the game and it will be invocated in the main method in the QuattroGameTester 
    public void playGame(){
        createCards(); //create cards for play
        createPlayers(); //create players in the game
        dealCards(); //deal cards to players
        playFirstCard(); //turn over top card
        playByTurn(); //play cards until a winner is determined
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
			
			cardListStack.add(standardColourRedi);
			cardListStack.add(standardColourGreeni);
			cardListStack.add(standardColourBluei);
			cardListStack.add(standardColourYellowi);
			
			//extend the card
			cardListStack.add(standardColourOrangei);
			cardListStack.add(standardColourPuplei);
			
		}		
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
		
		//deals cards to all of players (each 3cards)
//		for (int i = 0; i < numberOfPlayers - 1; i++) {
//			for (int j = 0; j <= numberOfPlayers - 1; j++) {
//				playerList.get(j).addCardToHand(cardListStack.get(j)); 
//				cardListStack.remove(j);
//			}
//		}
		
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
							System.out.println("Cards that " + playerList.get(0).getName() + " has are");
							for (int x = 0; x < playerList.get(0).getCardList().size(); x++) {
								System.out.println(playerList.get(0).getCardList().get(x).getString());
								//playerList.get(0).toString();
								//check the cards to indicate to the human player if there are no valid cards in hand
								if ((cardListDiscard.get(cardListDiscard.size() - 1).getCardColour() == playerList.get(0).getCardList().get(x).getCardColour())
										|| (cardListDiscard.get(cardListDiscard.size() - 1).getCardNumber() == playerList.get(0).getCardList().get(x).getCardNumber())) {
									isValidCard = true;																
								}																			
							}
							if (isValidCard == false) {
								System.out.println("There is no valid card. Press '0' to get the card from the draw pile.");
							}	
							System.out.println();
					
							//First(top) card is show
							System.out.println("The top card is: ");							
							System.out.println(cardListDiscard.get(cardListDiscard.size() - 1).getCardColour() + " " 
									+ cardListDiscard.get(cardListDiscard.size() - 1).getCardNumber());
							System.out.println("Enter the Card Code of the card you would like to play");
							System.out.println("If you don't have a valid card enter 0");
							
							//if the human player enters the code, that value is saved in a cardCodeSeed variable
							cardCodeSeed = playerList.get(0).exceptionHandling(cardCodeSeed);
							
							//exceptions if the human player makes an invalid selection
							try {												
								//if there is valid card in hand to submit	
								isValidCard = false; 
								if (cardCodeSeed != 0) {								
									for (int x = 0; x < playerList.get(0).getCardList().size(); x++) {												
										//human plays the valid card into the discard pile
										if (cardCodeSeed == playerList.get(0).getCardList().get(x).getCardCode()) {
											if ((cardListDiscard.get(cardListDiscard.size() - 1).getCardColour() == playerList.get(0).getCardList().get(x).getCardColour())
													|| (cardListDiscard.get(cardListDiscard.size() - 1).getCardNumber() == playerList.get(0).getCardList().get(x).getCardNumber())) {												
												isValidCard = true;	
												System.out.println(playerList.get(0).getName() + " has played " + playerList.get(0).getCardList().get(x).getCardColour() 
														+ " " + playerList.get(0).getCardList().get(x).getCardNumber());
												cardListDiscard.add(playerList.get(0).getCardList().get(x));
												playerList.get(0).removeCardFromHand(playerList.get(0).getCardList().get(x));										
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
									startingCardNumber--;
									System.out.println(playerList.get(0).getName() + " has the following number of cards: " + startingCardNumber);
									//if one of human's hand is empty, the game will be finished. 
									if (startingCardNumber == 0) {
										System.out.println("Player " + playerList.get(0).getName() + " is the winner");
										System.exit(0);
									}
									System.out.println();									
								}
								//if there is not valid card in human's hand to play
								else {
									isValidCard = false;
									//if human entered 0 but, there is a valid card in hand. Exception will arise
									for (int x = 0; x < playerList.get(0).getCardList().size(); x++) {
										if ((cardListDiscard.get(cardListDiscard.size() - 1).getCardColour() == playerList.get(0).getCardList().get(x).getCardColour())
												|| (cardListDiscard.get(cardListDiscard.size() - 1).getCardNumber() == playerList.get(0).getCardList().get(x).getCardNumber())) {											
											check = false;											
											throw new Exception("You have a valid card in your hand");																				
										}
									}
									//human gets a card from the cardListStack
									playerList.get(0).addCardToHand(cardListStack.get(0)); 
									cardListStack.remove(0);									
									//if draw pile cards are empty, move the all cards from the discard pile and mix again 
									if (cardListStack.isEmpty()) {
										//move the all cards from the discard pile to draw pile
										cardListStack.addAll(cardListDiscard);
										cardListDiscard.removeAll(cardListDiscard);
										//move the last card of the draw pile(It was the top card of discard pile)
										cardListDiscard.add(cardListStack.get(cardListStack.size() - 1));
										cardListStack.remove(cardListStack.get(cardListStack.size() - 1));
										//shuffle the draw pile cards 
										Collections.shuffle(cardListStack); 
									}
									System.out.println("A card has been added to your hand.");
									System.out.println("Cards that " + playerList.get(0).getName() + " has are");		
									//show the list of cards in human's hand 								
									for (int x = 0; x < playerList.get(0).getCardList().size(); x++) {
										System.out.println(playerList.get(0).getCardList().get(x).getString());													
									}
									System.out.println();
									startingCardNumber++;
									System.out.println(playerList.get(0).getName() + " has the following number of cards: " + startingCardNumber);
									System.out.println();
									check = true;
									break;
								}
							} 
							catch (Exception e) {	
								System.out.println();
								System.out.println(e.getMessage());						
								System.out.println("Please input the right code!");	 
							}	
						} while (!check);
					}
					
					//if player is not a human, computer's turn
					else {
						isValidCard = false;
						//checking the bot player's card whether they have the matching card 
						for (int j = 0; j < playerList.get(i).getCardList().size(); j++) {
							//if bot player has the matching card to play
							if ((cardListDiscard.get(cardListDiscard.size() - 1).getCardColour() == playerList.get(i).getCardList().get(j).getCardColour())
									|| (cardListDiscard.get(cardListDiscard.size() - 1).getCardNumber() == playerList.get(i).getCardList().get(j).getCardNumber())) {
								isValidCard = true;																	
								System.out.println("Player " + i + " has played the following card: ");								
								System.out.println(playerList.get(i).getCardList().get(j).getCardColour() + " " + playerList.get(i).getCardList().get(j).getCardNumber());
								cardListDiscard.add(playerList.get(i).getCardList().get(j));
								playerList.get(i).removeCardFromHand(playerList.get(i).getCardList().get(j));	
								startingCardNumber--;
								//if one of computer's hand is empty, the game will be finished. 
								if (startingCardNumber == 0) {
									System.out.println("Player " + i + " is the winner");
									System.exit(0);
								}
								System.out.println("Player " + i + " has the folloing number of cards: " + startingCardNumber);
								System.out.println();		
								break;
							}
						}
						//if bot player doesn't have a card to play, take the card from the discard pile
						if (isValidCard == false) {
							playerList.get(i).addCardToHand(cardListStack.get(0)); 
							cardListStack.remove(0);							
							//if draw pile cards are empty, move the all cards from the discard pile and mix again 
							if (cardListStack.isEmpty()) {
								//move the all cards from the discard pile to draw pile
								cardListStack.addAll(cardListDiscard);
								cardListDiscard.removeAll(cardListDiscard);
								//move the last card of the draw pile(It was the top card of discard pile)
								cardListDiscard.add(cardListStack.get(cardListStack.size() - 1));
								cardListStack.remove(cardListStack.get(cardListStack.size() - 1));
								//shuffle the draw pile cards 
								Collections.shuffle(cardListStack); 
							}
							startingCardNumber++;
							System.out.println("Player " + i + " has picked up a card");
							System.out.println("Player " + i + " has the following number of cards: " + startingCardNumber);
							System.out.println();
						}																					
					}
				}			
			}			
		}
	}
} 
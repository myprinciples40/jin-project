
package unoquattro;

/**
*
* @author Jinhwan Kim_20225927
*/

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

//This class for expressing the player playing the game with having the cards in the QuattroGame (Control the player's turn and cards)
public class Player {
	//variables
	private String name;
	private ArrayList<Card> cardList;
	private Boolean isHuman = false;
	
	//Constructor
	public Player (String name, ArrayList<Card> cardList, Boolean isHuman) {
		this.name = name;
		this.cardList = cardList;
		this.isHuman = isHuman;
	}

	//getter method - save the name information.
	public String getName() {
		return name;
	}

	//getter method - save the ArrayList<Card> information.
	public ArrayList<Card> getCardList() {
		return this.cardList;
	}

	//getter method - save the boolean information whether the player is human or not.
	public Boolean getIsHuman() {
		return isHuman;
	}
	
	//setter method - to catch if the human player input some space in the name, exception will arise
	public void setName(String name) {
		Scanner keyboard = new Scanner(System.in);		
		try {
			System.out.println("Enter Player Name");
			name = keyboard.nextLine();
			while (name == "" || name.contains(" ")) { // name.isEmpty() 왼쪽 대체가능 
				System.out.println("Enter a name with one or more non-space characters");
				System.out.println("Enter Player Name");
				name = keyboard.nextLine();
			}
			System.out.println("Player name is: " + name);
		}
		catch (NullPointerException e) {
			System.out.println("Enter a name with one or more non-space characters");
		}			
		this.name = name;
	}
	
	//helper method to have a role of adding the card in hand when the player gets the card 
    public void addCardToHand(Card card){
        this.cardList.add(card);
    }
	
    //helper method to have a role of removing the card in hand when the player plays the card 
	public void removeCardFromHand(Card card) {
		this.cardList.remove(card);
	}
	
	//helper method to catch if the human player enters non-integer value, exception will arise
	public int exceptionHandling(int inputNumber) {
		Scanner keyboard = new Scanner(System.in);				
		try { 
			if (!keyboard.hasNextInt()) { //check if non-integer value exist				
				throw new InputMismatchException("Please enter the int value");
			}
			inputNumber = keyboard.nextInt();
		} catch (InputMismatchException e) { 
			System.out.println();
			System.out.println("Please enter the int value");
		}		
		return inputNumber;
	}
}
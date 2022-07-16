
package unoquattro;

/**
*
* @author Jinhwan Kim_20225927
*/

//WildCard class extends Card class and implements SpecialAbility interface 
//So, this class for expressing the ability of a wild card(special card) to distinguish it from a normal colour card.
public class WildCard extends Card implements SpecialAbility {
	//variable
	private String specialAbility;
	
	//constructor
	public WildCard (int cardCode, String specialAbility) {
		super(cardCode);
		this.specialAbility = specialAbility;
	}

	public String getSpecialAbility() {
		return specialAbility;
	}
	
	public void setSpecialAbility(String specialAbility) {
		this.specialAbility = specialAbility;
	}
	
	//Override methods
	//overriding method to use activateAbility method for SpecialAvility information
	@Override
	public void activateAbility() {
		if (getSpecialAbility() == "draw2") {
			System.out.println("Two Draw card has submitted. Next player gets two cards from the draw pile");
		}
		else if (getSpecialAbility() == "draw4") {
			System.out.println("Four Draw card has submitted. Next player gets four cards from the draw pile");			
		}
		else if (getSpecialAbility() == "skip") {
			System.out.println("Skip card has submitted. Next player's turn will be skipped");			
		}
		else if (getSpecialAbility() == "reverse") {
			System.out.println("Reverse card has submitted. The order of play has been reversed.");			
		}
	}

	//getter method - to save the wild card's specialAbility information with String type
	@Override
	public String getString() {		
		return "Card Code:" + super.getCardCode() + " - " + getSpecialAbility();
	}

	//getter method - to save the wild card colour information	
	@Override
	public String getCardColour() {		
		return null;
	}

	//getter method - to save the wild card number information	
	@Override
	public int getCardNumber() {		
		return 0;
	}
}

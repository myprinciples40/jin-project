
package unoquattro;

/**
*
* @author Jinhwan Kim_20225927
*/

//SpecialColourCard class extends ColourCard class and implements SpecialAbility interface 
//So, this class for expressing the ability of a special color card(special card) to distinguish it from a standard colour card.
public class SpecialColourCard extends ColourCard implements SpecialAbility {
	//variable
	private String specialAbility;

	//constructor
	public SpecialColourCard(int cardCode, String cardColour, String specialAbility) {
		super(cardCode, cardColour);
		this.specialAbility = specialAbility;
	}

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

	public String getSpecialAbility() {
		return specialAbility;
	}

	public void setSpecialAbility(String specialAbility) {
		this.specialAbility = specialAbility;
	}	
	
	@Override
	public String getString() {		
		return super.getCardColour() + " " + getSpecialAbility();
	}
}

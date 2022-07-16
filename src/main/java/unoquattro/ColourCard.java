
package unoquattro;

/**
*
* @author Jinhwan Kim_20225927
*/

//ColourCard class extends Card class and it is available to use all variable and methods in the Card class.
public class ColourCard extends Card {
	//variable
	private String cardColour;
	
	//constructor
	public ColourCard(int cardCode, String cardColour) {
		super(cardCode);
		this.cardColour = cardColour;
	}

	//getter methods - methods overriding to save and express the new variable in the colourCard
	//getter method - save the card colour information
	@Override
	public String getCardColour() {
		return this.cardColour;
	}
	
	//getter method - just used this to cover the abstract method in here.
	@Override
	public int getCardNumber() {
		return 0;
	}
	
	//getter method - save the card code and colour information with String type.
	@Override
	public String getString() {
		return "Card Code:" + super.getCardCode() + " - " + this.getCardColour();
	}
}
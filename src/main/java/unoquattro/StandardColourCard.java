
package unoquattro;

/**
*
* @author Jinhwan Kim_20225927
*/

//StandardColourCard class extends ColourCard class and it is available to use all variable and methods in the Card and ColourCard class.
public class StandardColourCard extends ColourCard {
	//variable
	private int cardNumber;
	
	//constructor
	public StandardColourCard(int cardCode, String cardColour, int cardNumber) {
		super(cardCode, cardColour);
		this.cardNumber = cardNumber;
	}

	//getter methods - methods overriding to save and express the new variable in the StandardColourCard
	//getter method - save the card number information
	@Override
	public int getCardNumber() {
		return this.cardNumber;
	}
	
	//getter method - save the card code, colour, and number information with String type.
	@Override
	public String getString() {
		return"Card Code:" + super.getCardCode() + " - " + super.getCardColour() + " " + this.cardNumber;
	}
}

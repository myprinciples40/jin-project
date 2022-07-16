
package unoquattro;

/**
 *
 * @author Jinhwan Kim_20225927
 */

//Card Class is the abstract class to use in ColourCard class and StandardColourCard class.  
public abstract class Card {
	//variable
	protected int cardCode;
	
	//constructor
	public Card (int cardCode) {
		this.cardCode = cardCode;
	}
	
	//getter method
	public int getCardCode() {
		return this.cardCode;
	}
	
	//abstract methods - They will use according to their use and it is for without casting later 
	protected abstract String getCardColour();
	
	public abstract int getCardNumber();
	
	protected abstract String getString();
}


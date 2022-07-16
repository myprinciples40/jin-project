package unoquattro;

/**
*
* @author Jinhwan Kim_20225927
*/

//main class for excuting the game
public class QuattroGameTester {
    //main entry class for the program
    public static void main(String[] args) 
    {
        // This block assumes you have a method in QuattroGame that call all 
        // neccessary methods already. An example is provided in QuattroGame class: method playGame
        // You can change the way you implement for testing.
        
    	
//      QuattroGame game = new QuattroGame();                        
        QuattroGameAdd game = new QuattroGameAdd();                        
        game.playGame();
    }
}

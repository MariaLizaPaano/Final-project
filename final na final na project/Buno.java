import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

public class Buno {

	private Card current; // the current card or previously played card or starting card
	private Deck deck;// deck
	private ArrayList<Card> cardpile; //when players throw card, it piles up here. 
	private Scanner choice;
	private Player p1,p2; //player 1 and 2
	private int pick; // players pick
	
		public Buno() { //constructor
			deck = new Deck();
			deck.shuffle();
			current = getStartingCard(); 
			cardpile = new ArrayList<Card>();
			cardpile.add(current);
			choice = new Scanner(System.in);
			p1 = new Player("Player 1");
			p2 = new Player("Player 2");
			distributecards();	
			instructions();			
		}
	
	public void game() { //player turns / rounds
		playGame(p1, p2);
		playGame(p2, p1);
		
		while(!gameOver(p1,p2)) {
			
			if(p1.hasToken(p1)) {
				setCurrent(p1);      //bypass the color rule after winning round
				playGame(p2, p1);
			}
			else if (p2.hasToken(p2)) {
				setCurrent(p2);
				playGame(p1, p2);
			}
			
		}
		
	}
	
	
	public void playGame(Player p, Player other) { //game
		
		
		decorate();
		
		clearConsole();
		pause();
		decorate();
		showBoard(p);
		decorate();
	
		if(!hasColor(p) ) {
				Card pick=null;	
				System.out.println("You dont have a valid card to play, so you have to pick cards.");
				
				while(!hasColor(p)) {
					if(deck.isEmpty()){
						for (Card i:Cardpile()){
							deck.Addtodeck(i);
						}
					}
							pause();
							pick = deck.getTopCard();
							p.pickCards(pick);
							System.out.println("You picked:\n"+pick);
					
				}
			
			System.out.println("You recieved a valid card!");
			pause();
			System.out.println("You have the following cards: ");
			p.showCards();
			showBoard(p);
		}
		
		System.out.println("Please pick a card:");
		try
		{
		    pick = choice.nextInt()-1;
		    if(!deck.isEmpty()){ 
		    	int i = deck.remainingCards();
			System.out.println(i + " Cards left on deck");
			pause();

		}
		}    
		catch (Exception e)
		{
		    System.out.println("You may only enter integers as an age. Try again.");
		}

		while(!isValidChoice(p,pick)) {
			
			System.out.println("Invalid pick. Please pick a valid card.");
			pick = choice.nextInt()-1;			
		}
		
		Card play = p.throwCard(pick);           // compare value to identify who wins the round 
		if (current.value > play.value) {       
		p.setToken(false); 
		other.setToken(true);
		}
		else if (current.value < play.value) { 
		p.setToken(true);                        
		other.setToken(false);
		
		}
		
		current = play;
		cardpile.add(play);
		System.out.println(p+" picked:\n"+play);
	}
	
	public void instructions(){
		System.out.println("**INSTRUCTIONS**");
		System.out.println("1. Choose the same color for card to be valid");
		System.out.println("2. The game is played by rounds. You keep your turn if your card value is higher than your opponent");
		System.out.println("3. After winning the round, you are free to choose whatever color you think your opponent lacks");
		System.out.println("4. The first player to have zero cards left wins the game");
		System.out.println("5. GOODLUCK!");
		
	}
	
	//this method distributes cards to the players
	private void distributecards() { 
	
		for(int i=0;i<10;i++) {
			if(i%2==0) {                      // draws 5 cards each player
				p1.pickCards(deck.getTopCard());
			}
			else {
				p2.pickCards(deck.getTopCard());
			}
		}
	}
	//check if card is valid
	private boolean isValidChoice(Player p,int choice) {

		if(choice < p.PlayerCards().size()) {
			
			if(p.PlayerCards().get(choice).getColor().equals(current.getColor())) {
				return true;
			}
		}
		return false;
	}
	
	
	private void pause() {
		
		System.out.println("Press enter to continue......");
		choice.nextLine();	
	}
	
	/** check if the player has card of the same color as the current card that is being played
	  *if the same it will return true
	**/
	private boolean hasColor(Player p) {
		
		for(Card c:p.PlayerCards()) {
			
			if(c.getColor().equals(current.getColor())) {
				return true;
			}
		}
		return false;
	}
	
	private void decorate() {
		
		System.out.println("_________________________B__U__N__O________________________");
	}
	
	//starting card of the game
	private Card getStartingCard() {

		Card temp = deck.peek();
		
		temp = deck.getTopCard();
		return temp;
	}
	
	public boolean gameOver(Player p1,Player p2) {
	
	if(p1.hasWon()) {
		System.out.println("__________________________________________________");
		System.out.println("Player 1 has won");
		System.out.println("__________________________________________________");
		return true;
	}
	
	else if(p2.hasWon()) {
		System.out.println("__________________________________________________");
		System.out.println("Player 2 has won");
		System.out.println("__________________________________________________");
		return true;
	}
	
	return false;
}
	public void showBoard(Player p) {
		
		System.out.println(p);             
		System.out.println(current);   
		
		if(p.toString().equals("Player 1")) {
			System.out.println("                Player 1");
			p1.showCards();
			p2.hideCards();
			System.out.println("                Player 2");
			System.out.println("");
		}
		else {
			
			System.out.println("                Player 1");
			p1.hideCards();
			p2.showCards();
			System.out.println("                Player 2");
			System.out.println("");
			
		}	
	}
	
	public void setCurrent(Player p){ //sets current after ROUND WIN
		System.out.println(p + " wins the round!");
		pause();
		System.out.println("Please pick a NEW card:");
		showBoard(p);
		pick = choice.nextInt()-1;
		Card play = p.throwCard(pick);
		current = play;
		cardpile.add(play);
	}
	public ArrayList<Card> Cardpile(){
	
		return cardpile;
	}
	
	public final static void clearConsole(){
		try
		{
			final String os = System.getProperty("os.name");
			if (os.contains("Windows")){
				Runtime.getRuntime().exec("cls");
			}
			else{
				Runtime.getRuntime().exec("clear");
			}
		}
		catch(final Exception e){

		}
	}
}
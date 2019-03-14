import java.util.ArrayList;
import java.util.Scanner;


public class Player {
	private ArrayList<Card> playercards; //cards in hand
	private String name; //player name
	public boolean token;
	
	public Player(String name) { //constructor

		boolean token = false;
		playercards = new ArrayList<Card>();
		this.name = name;	
	}
	
	//playercard size
	public int numberOfCards() {
	
		return playercards.size();
	}
	
	public ArrayList<Card> PlayerCards(){
	
		return playercards;
	}
	
	
	public void pickCards(Card c) {
		
		playercards.add(c);
	}
	
	//player throws a card from hand which is at position 'c'
	public Card throwCard(int c) {
	
		return playercards.remove(c);
	}
	
	public void showCards() {

		String[] card = {" ----- ", "|     |", "|     |", " ----- "};
		String c = "";
	
		for(int i=0;i<card.length;i++) {
	
			for(int j=0;j<playercards.size();j++) {
					
				playercards.get(j);
					if(i==1) {	
						c = c +"| "+playercards.get(j).getColor()+" |"+" ";	
					}
					else if(i==2) {
						c = c + "|  "+playercards.get(j).getValue()+"  |"+" ";
					}
					else {
						c = c + card[i]+" "; 
					}
			}	
				c +="\n";		
		}
		System.out.print(c);
	}
	
	// to hide player cards
	public void hideCards() {
		
		String[] card = {" ----- ","|     |","|     |"," ----- "};
		String c = "";
		
		for(int i=0;i<card.length;i++) {	
			for(int j=0;j<playercards.size();j++) {
				c = c + card[i]+" ";
			}
		c+="\n";
		}
		
		System.out.print(c);
	}
	
	// check is the player has won or not
	public boolean hasWon() {
		
		if(playercards.size()==0) {
			return true;
		}
		return false;
	}
	
	public String getName() {
	
		return this.name;
	}
	
	public boolean hasToken(Player player) {
		return player.token;
	}
	
	public void setToken(boolean token) {
		this.token = token;
	}
	
}
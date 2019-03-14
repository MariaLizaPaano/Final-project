import java.util.Random;
public class Deck {
	
	private Card deck[];//Array of cards
	private final int Total_numbers_cards = 40;
	private int top;
	
		public Deck() {
			String color[] = {"red", "wht", "blu", "ylw"};
			int value[] = {0,1, 2 , 3 , 4 , 5 , 6 , 7 , 8 , 9 };
			deck = new Card[Total_numbers_cards];
			top = 0;	
			
			for (; top < deck.length;top++){
				deck[top] = new Card(value[top%10],color[top/10]);
			}
		}
	
	public void shuffle(){
	//shuffle the cards
		
		for(int i = 0; i < this.deck.length; i++){
			
			//swaping
			int index = (int)(Math.random() * deck.length);
			Card temp = deck[i];
			deck[i] = deck[index];
			deck[index] = temp;
		}
	}

	public void print(){
		for(int i = 0; i<this.top; i++){
			System.out.println(this.deck[i]);
		}
	}
	
	public boolean isEmpty() { //check if the deck empty
		if(top==0)
			return true;
		
		else
			return false;
		
	}
	
	public Card peek(){
		return deck[deck.length-1];
	}
	
	public Card getTopCard(){
		Card temp;
		temp = deck[top-1];
		top--;
		return temp;
	}
	
	public void Addtodeck(Card i){
		deck[top] = i;
		top++;
	}

	public int remainingCards() {
		return top;
	}
}
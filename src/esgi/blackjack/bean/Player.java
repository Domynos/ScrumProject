package esgi.blackjack.bean;

public class Player {
	public int score;
	
	public Player(){
		score = 0;
	}
	
	public void addCard(int _cardNumber){
		score += _cardNumber;
	}
}

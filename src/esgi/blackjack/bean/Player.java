package esgi.blackjack.bean;

public class Player {
	private int score;
	private int tapis;
	
	private int currentBet;
	
	public Player(){
		score = 0;
	}

	public int getScore() {
		return score;
	}
	
	public int getTapis() {
		return tapis;
	}
	
	public void setTapis(int _tapis) {
		tapis = _tapis;
	}
	
	public void setBet(int bet) {
		currentBet = bet;
	}
	
	public void addCard(int _cardNumber){
		score += _cardNumber;
	}

		
	
	public void win(int pot){
		tapis += pot;
	}
	
	public void lose(){
		tapis -= currentBet;
	}
}

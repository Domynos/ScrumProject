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
		return (tapis - currentBet);
	}
	
	public void setTapis(int _tapis) {
		tapis = _tapis;
	}
	
	public void setBet(int bet) {
		currentBet = bet;
	}
	
	public int getBet(){
		return currentBet;
	}
	
	public void addCard(int _cardNumber){
		score += _cardNumber;
	}

		
	
	public void win(boolean blackjack){
		if(blackjack)
			tapis += ((int)(currentBet * 2));
		else
			tapis += ((int)(currentBet * 1.5));
		
		tapis -= currentBet;
		currentBet = 0;
	}
	
	public void lose(){
		tapis -= currentBet;
		currentBet = 0;
	}
}

package esgi.blackjack.bean;

import java.util.LinkedList;
import java.util.List;

public class CardPack {
	public List<Card> deck;
	
	public CardPack() {
		// TODO Auto-generated constructor stub
		deck = new LinkedList<Card>();
		for(int y=1;y<5;y++)
		for(int i=1;i<14;i++)
			deck.add(new Card(i,y));
	}
}

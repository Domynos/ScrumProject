package esgi.blackjack.bean;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import esgi.blackjack.utils.Utils;

public class CardPack {
	private List<Card> deck;
	private List<Integer> indexesDone = new ArrayList<Integer>();
	
	public CardPack() {
		// TODO Auto-generated constructor stub
		deck = new LinkedList<Card>();
		for(int y=1;y<5;y++)
		for(int i=1;i<14;i++)
			deck.add(new Card(i,y));
	}
	
	public Card getCard() {
		
		if(indexesDone.size() == 52)
			return null;
		
		int index = 0;
		
		do {
			index = Utils.randCard();
		} while(indexesDone.contains(index));
		
		indexesDone.add(index);
			
		return deck.get(index);
		
	}
}

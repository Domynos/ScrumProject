package esgi.blackjack.utils;

import java.util.Random;

public class Utils {

	public static int randInt(int min, int max) {

	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
	public static int randCard(){
		return Utils.randInt(0, 12)+13*(Utils.randInt(0, 3));
	}
}

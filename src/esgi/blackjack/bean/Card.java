package esgi.blackjack.bean;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Card extends JPanel{
	public int cardNumber;
	public int signe;
	private BufferedImage image;
	
	/*
	 * Signes : 
	 * 1 = pique
	 * 2 = trefle
	 * 3 = carreau
	 * 4 = coeur
	 */
	public Card(int _cardNumber, int _signe){
		cardNumber = _cardNumber;
		signe = _signe;
		try {
			String stringSigne = "";
			String stringNumber = "";
			
			if(signe==1)stringSigne="pique";
			else if(signe==2)stringSigne="trefle";
			else if(signe==3)stringSigne="carreau";
			else if(signe==4)stringSigne="coeur";
			
			if(cardNumber < 10)stringNumber="0"+cardNumber;
			else if(cardNumber > 10){
				switch(cardNumber){
				case 11:stringNumber="V";break;
				case 12:stringNumber="D";break;
				case 13:stringNumber="R";break;
				}
			}
			else
				stringNumber=cardNumber+"";
			
			String cardPath ="img/cartes/"+stringNumber+"-"+stringSigne+"-img.png";
			image = ImageIO.read(new File(cardPath));
		} catch (IOException ex) {
			// handle exception...
			System.out.println("Image exception");
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters            
	}
}

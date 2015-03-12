package esgi.blackjack.bean;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Card extends JPanel{
	public int cardNumber;
	public int signe;
	private BufferedImage image;
	private boolean toRight = false;
	
	/*
	 * Signes : 
	 * 1 = pique
	 * 2 = trefle
	 * 3 = carreau
	 * 4 = coeur
	 */
	public Card(int _cardNumber, int _signe){
		this(_cardNumber, _signe, false);
	}
	
	public Card(int _cardNumber, int _signe, boolean _toRight){
		this.toRight = _toRight;
		
		Color trnasparentColor=new Color(1f,0f,0f,0 );
		this.setBackground(trnasparentColor);
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
	
	public void setToRight(boolean _toRight) {
		this.toRight = _toRight;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		int x = 0;
		int y = 0;
		
		if(this.toRight) {
			x = this.getWidth() - image.getWidth();
		}
		
		g.drawImage(image, x, y, null); // see javadoc for more info on the parameters            
	}
}

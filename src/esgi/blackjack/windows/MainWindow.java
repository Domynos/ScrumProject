package esgi.blackjack.windows;
	
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import esgi.blackjack.bean.Card;
import esgi.blackjack.bean.CardPack;
import esgi.blackjack.bean.PlayMat;
import esgi.blackjack.bean.Player;

@SuppressWarnings("serial")
public class MainWindow extends JFrame{
	JPanel bankCardPanel, playerPanel;
	PlayMat bankPanel;
	
	JTextField fieldBanque;
	Card aCardBanque;
	JButton tirerCardBanque;
	JButton finirBanque;
	
	
	List<JPanel> listPlayerPanel;
	List<Player> players = new ArrayList<Player>();
	Player banque = new Player();
	List<JButton> askForCardButtons = new ArrayList<JButton>();
	List<JButton> finishButtons = new ArrayList<JButton>();
	Color transparentColor=new Color(1f,0f,0f,0 );

	CardPack cardPack;
	JButton leaveTableButton;
	
	private int nbPlayer = 1;

	public MainWindow(){
		if(nbPlayer > 4)
			nbPlayer = 4;
		else if(nbPlayer < 0)
			nbPlayer = 1;
		
		
		cardPack = new CardPack();
		initUI();
		initZones();
		initPlayerPanel();
		initBankPanel();
		distribute();
	}

	private void initUI() {
		setTitle("Black Jack");
		setSize(1200, 700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new GridLayout(2,0));
	}

	private void initZones(){
		bankPanel = new PlayMat("table.jpeg");
		playerPanel = new JPanel();
		listPlayerPanel = new LinkedList<JPanel>();
		
		for(int i=0;i<nbPlayer;i++){
			PlayMat aPanel = new PlayMat("playerBackground.jpeg");
			aPanel.setLayout(new GridBagLayout());
			listPlayerPanel.add(aPanel);
		}
		
//		bankPanel.setBackground(Color.green);

		
		bankPanel.setLayout(new GridLayout(0,3));
		
		playerPanel.setBackground(Color.blue);
		playerPanel.setLayout(new GridLayout(0,nbPlayer));
		
		for (JPanel aPanel : listPlayerPanel) {
			aPanel.setBackground(transparentColor);

			playerPanel.add(aPanel);
		}
		this.add(bankPanel);
		this.add(playerPanel);
	}
	
	private void initPlayerPanel(){
		askForCardButtons.clear();
		finishButtons.clear();
		players.clear();
		
		for(int i = 0 ; i < this.listPlayerPanel.size() ; ++i) {
			players.add(new Player());

			JButton currentCardButton = new JButton("Prendre une carte");
			JButton currentFinish = new JButton("Terminer");
						
			currentCardButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					addCard(e.getSource());
				}
			});
			currentFinish.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					finTourJoueur(e.getSource());
				}
			});
			
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx=0;
			gbc.gridy=0;
			gbc.weightx = gbc.weighty = 1;
			gbc.fill=GridBagConstraints.BOTH;
			gbc.gridheight=1;
			
			listPlayerPanel.get(i).add(currentCardButton,gbc);
			
			gbc.gridx=1;
			listPlayerPanel.get(i).add(currentFinish,gbc);

			askForCardButtons.add(currentCardButton);
			finishButtons.add(currentFinish);
		}
	}
	
	private void initBankPanel(){
		leaveTableButton = new JButton("Quitter la table");
		tirerCardBanque = new JButton("Tirer Carte");
		finirBanque = new JButton("Finir pour la banque"); 
		
		aCardBanque = cardPack.getCard();

		if(aCardBanque == null) {
			JOptionPane.showMessageDialog(null,"Plus de carte dans le deck !");
			return;
		}
		
		this.banque.addCard(aCardBanque.cardNumber);
		System.out.println("La banque reçoit la valeur "+ aCardBanque.cardNumber + " En première carte ");
		
		
		
		leaveTableButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		tirerCardBanque.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				tirerCarteBanque();
			}
		});
		
		finirBanque.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				resultFinDeGameMotherFUckaaaaa();
			}
		});
		
		tirerCardBanque.setVisible(false);
				
		JPanel tempPanel = new JPanel();
		
		tempPanel.add(leaveTableButton);
		tempPanel.add(tirerCardBanque);
		tempPanel.add(finirBanque);
		
		tempPanel.setBackground(transparentColor);
		tempPanel.validate();
		fieldBanque = new JTextField(String.valueOf(this.banque.score)); 
		fieldBanque.setEditable(false);
		
		
		
		
		bankPanel.add(tempPanel);
		bankPanel.add(fieldBanque);
		bankPanel.add(aCardBanque);
	
		
		bankCardPanel = new JPanel();

		bankCardPanel.setBackground(transparentColor);
		bankCardPanel.setLayout(new GridLayout(2,0));
			
		
		bankPanel.add(bankCardPanel);		
	}
	
	private void distribute(){
		
		for(int i = 0 ; i < this.players.size() ; ++i) {
			
			Card firstCard = cardPack.getCard();
			Card secondCard = cardPack.getCard();
		
			players.get(i).addCard(firstCard.cardNumber);
			players.get(i).addCard(secondCard.cardNumber);
			System.out.println(players.get(i).score);
		
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx=0;
			gbc.gridy=1;
			gbc.fill=GridBagConstraints.BOTH;
			gbc.weightx = 1;
			gbc.weighty = 40;
			
			listPlayerPanel.get(i).add(firstCard,gbc);
			
			gbc.gridx=1;
			listPlayerPanel.get(i).add(secondCard,gbc);
		}

	}
	
	
	private void addCard(Object source){
		
		Card aCard = cardPack.getCard();
		
		if(aCard == null) {
			JOptionPane.showMessageDialog(null,"Plus de carte dans le deck !");
			return;
		}
		
		int indexPlayer = this.askForCardButtons.indexOf(source);
		
		players.get(indexPlayer).addCard(aCard.cardNumber);
		System.out.println(players.get(indexPlayer).score);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.fill=GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 40;
		listPlayerPanel.get(indexPlayer).remove(2);
		listPlayerPanel.get(indexPlayer).add(listPlayerPanel.get(indexPlayer).getComponent(2),gbc);
		gbc.gridx = 1;
		listPlayerPanel.get(indexPlayer).add(aCard,gbc);
		validate();
	}
	
	
	
	private void finTourJoueur(Object source) {
		aCardBanque = cardPack.getCard();

		if(aCardBanque == null) {
			JOptionPane.showMessageDialog(null,"Plus de carte dans le deck !");
			return;
		}
		
		
		bankPanel.remove(2);
		bankPanel.add(aCardBanque,2);
		aCardBanque.repaint();
		aCardBanque.validate();
		aCardBanque.updateUI();
		bankPanel.repaint();
		bankPanel.validate();
		bankPanel.updateUI();
		System.out.println("La banque pioche en deuxième carte "+aCardBanque);
		this.banque.addCard(aCardBanque.cardNumber);
		this.fieldBanque.setText(String.valueOf(this.banque.score));
		
		tirerCardBanque.setVisible(true);
		
		if(source.getClass().equals(JButton.class))
			((JButton)source).setEnabled(false);
	}

	
	private void tirerCarteBanque() {
		aCardBanque = cardPack.getCard();

		if(aCardBanque == null) {
			JOptionPane.showMessageDialog(null,"Plus de carte dans le deck !");
			return;
		}
		
		
		bankPanel.remove(2);
		bankPanel.add(aCardBanque,2);
		aCardBanque.repaint();
		aCardBanque.validate();
		aCardBanque.updateUI();
		bankPanel.repaint();
		bankPanel.validate();
		bankPanel.updateUI();
		System.out.println("La banque pioche en deuxième carte "+aCardBanque);
		this.banque.addCard(aCardBanque.cardNumber);
		this.fieldBanque.setText(String.valueOf(this.banque.score));
		
		if(this.banque.score>21){
			this.fieldBanque.setText(String.valueOf(this.banque.score) + " - La banque dépasse 21 elle PERD !!!");
			this.tirerCardBanque.setVisible(false);
		}
		
		if(this.banque.score<=17){
			this.tirerCardBanque.setVisible(false);
		}
	}
	
	private void resultFinDeGameMotherFUckaaaaa() {
		
		boolean unJoueurGagnant = false;
		for(Player tempJoueur : this.players){
			if(tempJoueur.score >= this.banque.score){
				this.fieldBanque.setText("Le joueur gagne : " + tempJoueur.score +" - La banque perd : "+this.banque.score);
				unJoueurGagnant = true;
			}
		}
		if(!unJoueurGagnant){
			this.fieldBanque.setText("La banque gagne avec un score de : " +this.banque.score);	
		}
	}
}

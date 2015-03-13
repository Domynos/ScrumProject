package esgi.blackjack.windows;
	
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import esgi.blackjack.bean.BetPanel;
import esgi.blackjack.bean.Card;
import esgi.blackjack.bean.CardPack;
import esgi.blackjack.bean.PlayMat;
import esgi.blackjack.bean.Player;
import esgi.blackjack.utils.Utils;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {
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
	List<JButton> betButtons = new ArrayList<JButton>();
	
	List<JLabel> scoreLabels = new ArrayList<JLabel>();
	List<JLabel> betLabels = new ArrayList<JLabel>();
	List<JLabel> tapisLabels = new ArrayList<JLabel>();
	
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
		
		scoreLabels.clear();
		betLabels.clear();
		tapisLabels.clear();
		
		JLabel descScoreLabel;
		JLabel descBetLabel;
		JLabel descTapisLabel;
		
		for(int i = 0 ; i < this.listPlayerPanel.size() ; ++i) {
			Player currentPlayer = new Player();
			players.add(currentPlayer);
			
			currentPlayer.setTapis(500);


			descScoreLabel = new JLabel("Score : ");
			descScoreLabel.setHorizontalAlignment(JLabel.RIGHT);
			descScoreLabel.setVerticalAlignment(JLabel.CENTER);
			descScoreLabel.setForeground(Color.WHITE);
			
			descBetLabel = new JLabel("Mise : ");
			descBetLabel.setHorizontalAlignment(JLabel.RIGHT);
			descBetLabel.setVerticalAlignment(JLabel.CENTER);
			descBetLabel.setForeground(Color.WHITE);
			
			descTapisLabel = new JLabel("Tapis : ");
			descTapisLabel.setHorizontalAlignment(JLabel.RIGHT);
			descTapisLabel.setVerticalAlignment(JLabel.CENTER);
			descTapisLabel.setForeground(Color.WHITE);
			
			JButton currentCardButton = new JButton("Prendre une carte");
			// espaces rajoutés pour que les boutons prennes la même place...
			JButton currentBetButton = new JButton("           Miser           ");
			JButton currentFinish = new JButton("Terminer");

			JLabel currentScoreLabel = new JLabel(""+currentPlayer.getScore());
			currentScoreLabel.setHorizontalAlignment(JLabel.LEFT);
			currentScoreLabel.setVerticalAlignment(JLabel.CENTER);
			currentScoreLabel.setForeground(Color.WHITE);
			
			JLabel currentBetLabel = new JLabel("0");
			currentBetLabel.setHorizontalAlignment(JLabel.LEFT);
			currentBetLabel.setVerticalAlignment(JLabel.CENTER);
			currentBetLabel.setForeground(Color.WHITE);
			
			JLabel currentTapisLabel = new JLabel(""+currentPlayer.getTapis());	
			currentTapisLabel.setHorizontalAlignment(JLabel.LEFT);
			currentTapisLabel.setVerticalAlignment(JLabel.CENTER);
			currentTapisLabel.setForeground(Color.WHITE);
						
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
			currentBetButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					playerBet(e.getSource());
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
			listPlayerPanel.get(i).add(currentBetButton,gbc);
			gbc.gridx=0;
			gbc.gridy=1;
			gbc.gridwidth=2;
			listPlayerPanel.get(i).add(currentFinish,gbc);

			askForCardButtons.add(currentCardButton);
			betButtons.add(currentBetButton);
			finishButtons.add(currentFinish);

			// saute une ligne pour laisser la place aux cartes
			gbc.gridy=3;
			gbc.gridx=0;
			gbc.gridwidth=1;
			listPlayerPanel.get(i).add(descScoreLabel, gbc);
			gbc.gridx=1;
			listPlayerPanel.get(i).add(currentScoreLabel,gbc);

			gbc.gridy=4;
			gbc.gridx=0;
			listPlayerPanel.get(i).add(descBetLabel,gbc);
			gbc.gridx=1;
			listPlayerPanel.get(i).add(currentBetLabel,gbc);

			gbc.gridy=5;
			gbc.gridx=0;
			listPlayerPanel.get(i).add(descTapisLabel,gbc);
			gbc.gridx=1;
			listPlayerPanel.get(i).add(currentTapisLabel,gbc);
			
			scoreLabels.add(currentScoreLabel);
			betLabels.add(currentBetLabel);
			tapisLabels.add(currentTapisLabel);
		}
	}
	
	private void initBankPanel(){
		leaveTableButton = new JButton("Nouvelle partie");
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
				//System.exit(0);
				nouvellePartie();
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
		fieldBanque = new JTextField(String.valueOf(this.banque.getScore())); 
		fieldBanque.setPreferredSize(new Dimension(100,50));
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
		
			firstCard.setToRight(true);
			players.get(i).addCard(firstCard.cardNumber);
			players.get(i).addCard(secondCard.cardNumber);
			System.out.println(players.get(i).getScore());
		
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.gridx=0;
			gbc.gridy=2;
			gbc.fill=GridBagConstraints.BOTH;
			gbc.weightx = 1;
			gbc.weighty = 40;
			
			listPlayerPanel.get(i).add(firstCard,gbc);
			
			gbc.gridx=1;
			listPlayerPanel.get(i).add(secondCard,gbc);
			scoreLabels.get(i).setText(""+players.get(i).getScore());
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
		System.out.println(players.get(indexPlayer).getScore());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.fill=GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 40;
		listPlayerPanel.get(indexPlayer).remove(9);
		
		Card previousCard = ((Card) listPlayerPanel.get(indexPlayer).getComponent(9));
		previousCard.setToRight(true);
		listPlayerPanel.get(indexPlayer).add(previousCard,gbc);
		gbc.gridx = 1;
		listPlayerPanel.get(indexPlayer).add(aCard,gbc);
		
		scoreLabels.get(indexPlayer).setText(""+players.get(indexPlayer).getScore());
		
		if(players.get(indexPlayer).getScore() > 21){
			JOptionPane.showMessageDialog(null,"Perdu !\nVous avez dépassé 21");
			askForCardButtons.get(indexPlayer).setVisible(false);
			betButtons.get(indexPlayer).setVisible(false);
		}
		validate();
	}
	
	
	
	private void finTourJoueur(Object source) {
		JButton button = (JButton) source;
		int indexPlayer = -1;
		for(int i=0;i<finishButtons.size();i++){
			if(finishButtons.get(i) == button){
				indexPlayer = i;
			}
		}

		askForCardButtons.get(indexPlayer).setVisible(false);
		betButtons.get(indexPlayer).setVisible(false);
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
		this.fieldBanque.setText(String.valueOf(this.banque.getScore()));
		this.fieldBanque.setMaximumSize(new Dimension(100,30));
		
		tirerCardBanque.setVisible(true);
		
		if(this.banque.getScore()>21){
			this.fieldBanque.setText("La banque PERD : " +this.banque.getScore()+"\nVous avez gagné : "+players.get(indexPlayer).getBet()*1.5);
			this.finirBanque.setVisible(false);
			this.tirerCardBanque.setVisible(false);
		}
		
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
		this.fieldBanque.setText(String.valueOf(this.banque.getScore()));
		
		if(this.banque.getScore()>21){
			this.fieldBanque.setText(String.valueOf(this.banque.getScore()) + " - La banque dépasse 21 elle PERD !!!");
			this.tirerCardBanque.setVisible(false);
			this.finirBanque.setVisible(false);
		}
		
	}
	
	private void playerBet(Object source) {
		int indexPlayer = this.betButtons.indexOf(source);
		
		BetPanel betPanel = new BetPanel(indexPlayer, players.get(indexPlayer).getTapis());
        if(JOptionPane.showConfirmDialog(null, betPanel,
                        "Sélectionner votre mise", JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {
    		players.get(indexPlayer).setBet(betPanel.getBet());
    		betLabels.get(indexPlayer).setText(""+betPanel.getBet());
    		tapisLabels.get(indexPlayer).setText(""+players.get(indexPlayer).getTapis());
        }
	}
	
	private void resultFinDeGameMotherFUckaaaaa() {
		
		boolean unJoueurGagnant = false;
		int i = 0;
		for(Player tempJoueur : this.players){
			if(tempJoueur.getScore() >= this.banque.getScore() && tempJoueur.getScore() <= 21){ 
				int gain = (int) (tempJoueur.getBet());
				
				if(tempJoueur.getScore() == 21)
					gain = gain*2;
				else
					gain = ((int)(gain*1.5));
				
				this.fieldBanque.setText("Le joueur "+i+" gagne : " + tempJoueur.getScore() +" - La banque perd : "+this.banque.getScore()+" - Vous avez gagné : "+gain);
				
				tempJoueur.win(tempJoueur.getScore() == 21);
				unJoueurGagnant = true;
			} else {
				tempJoueur.lose();
			}

			betLabels.get(i).setText(""+tempJoueur.getBet());
			tapisLabels.get(i).setText(""+tempJoueur.getTapis());
			
			i++;
		}
		if(!unJoueurGagnant){
			this.fieldBanque.setText("La banque gagne avec un score de : " +this.banque.getScore());	
		}
	}
	
	private void nouvellePartie(){
		Utils.restartApplication();
	}
}

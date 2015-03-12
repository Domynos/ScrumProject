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

import esgi.blackjack.bean.Card;
import esgi.blackjack.bean.CardPack;
import esgi.blackjack.bean.Player;
import esgi.blackjack.listener.FinishListener;
import esgi.blackjack.utils.ImageBankPanel;

@SuppressWarnings("serial")
public class MainWindow extends JFrame{
	JPanel bankPanel, bankCardPanel, playerPanel;
	
	List<JPanel> listPlayerPanel;
	List<Player> players = new ArrayList<Player>();
	List<JButton> askForCardButtons = new ArrayList<JButton>();
	List<JButton> finishButtons = new ArrayList<JButton>();
	
	CardPack cardPack;
	JButton leaveTableButton;
	
	private int nbPlayer = 1;

	public MainWindow(){
		if(nbPlayer > 4)
			nbPlayer = 4;
		else if(nbPlayer < 0)
			nbPlayer = 1;
		
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
		bankPanel = new JPanel();
		playerPanel = new JPanel();
		listPlayerPanel = new LinkedList<JPanel>();
		
		for(int i=0;i<nbPlayer;i++){
			JPanel aPanel = new JPanel();
			if((i%2)==0)aPanel.setBackground(Color.red);else aPanel.setBackground(Color.yellow);
			aPanel.setLayout(new GridBagLayout());
			listPlayerPanel.add(aPanel);
		}
		
		bankPanel.setBackground(Color.green);
		bankPanel.setLayout(new GridLayout(0,3));
		playerPanel.setBackground(Color.black);
		playerPanel.setLayout(new GridLayout(0,nbPlayer));
		
		for (JPanel aPanel : listPlayerPanel) {
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
			currentFinish.addActionListener(new FinishListener());
			
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
		JPanel tempPanel = new JPanel();
		tempPanel.add(leaveTableButton);
		tempPanel.setBackground(bankPanel.getBackground());
		bankPanel.add(tempPanel);
		
		bankCardPanel = new JPanel();
		bankCardPanel.setBackground(bankPanel.getBackground());
		bankCardPanel.setLayout(new GridLayout(2,0));
		
		/* Ajout image et cartes */
		bankCardPanel.add(new ImageBankPanel());
		/* */
		
		bankPanel.add(bankCardPanel);
	}
	
	private void distribute(){
		cardPack = new CardPack();
		
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
}

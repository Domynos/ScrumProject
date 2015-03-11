package esgi.blackjack.windows;
	
import java.awt.Button;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import esgi.blackjack.bean.Card;
import esgi.blackjack.bean.CardPack;
import esgi.blackjack.bean.Player;
import esgi.blackjack.listener.FinishListener;
import esgi.blackjack.utils.ImageBankPanel;
import esgi.blackjack.utils.Utils;

public class MainWindow extends JFrame{
	JPanel bankPanel, bankCardPanel, playerPanel;
	List<JPanel> listPlayerPanel;
	CardPack cardPack;
	JButton askForCardButton;
	JButton finishButton;
	JButton leaveTableButton;
	Player player;

	public MainWindow(){
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
		
		for(int i=0;i<4;i++){
			JPanel aPanel = new JPanel();
			if((i%2)==0)aPanel.setBackground(Color.red);else aPanel.setBackground(Color.yellow);
			aPanel.setLayout(new GridBagLayout());
			listPlayerPanel.add(aPanel);
		}
		
		bankPanel.setBackground(Color.green);
		bankPanel.setLayout(new GridLayout(0,3));
		playerPanel.setBackground(Color.black);
		playerPanel.setLayout(new GridLayout(0,4));
		
		for (JPanel aPanel : listPlayerPanel) {
			playerPanel.add(aPanel);
		}
		this.add(bankPanel);
		this.add(playerPanel);
	}
	
	private void initPlayerPanel(){
		player = new Player();
		askForCardButton = new JButton("Prendre une carte");
		finishButton = new JButton("Terminer");
		
		askForCardButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				addCard();
			}
		});
		finishButton.addActionListener(new FinishListener());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.weightx = gbc.weighty = 1;
		gbc.fill=GridBagConstraints.BOTH;
		gbc.gridheight=1;
		
		listPlayerPanel.get(0).add(askForCardButton,gbc);
		
		gbc.gridx=1;
		listPlayerPanel.get(0).add(finishButton,gbc);
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
		Card firstCard = cardPack.deck.get(Utils.randCard());
		Card secondCard = cardPack.deck.get(Utils.randCard());
		player.addCard(firstCard.cardNumber);
		player.addCard(secondCard.cardNumber);
		System.out.println(player.score);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx=0;
		gbc.gridy=1;
		gbc.fill=GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 40;
		
		listPlayerPanel.get(0).add(firstCard,gbc);
		
		gbc.gridx=1;
		listPlayerPanel.get(0).add(secondCard,gbc);

	}
	
	private void addCard(){
		Card aCard = cardPack.deck.get(Utils.randCard());
		player.addCard(aCard.cardNumber);
		System.out.println(player.score);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.fill=GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 40;
		listPlayerPanel.get(0).remove(2);
		listPlayerPanel.get(0).add(listPlayerPanel.get(0).getComponent(2),gbc);
		gbc.gridx = 1;
		listPlayerPanel.get(0).add(aCard,gbc);
		validate();
	}
}

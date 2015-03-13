package esgi.blackjack.bean;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.Hashtable;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class BetPanel extends JPanel implements ChangeListener {

	int playerIndex = 0;
	
	private int minBet = 0;
	private int maxBet = 0;
	
	private int currentBet = 0;
	private JLabel currentBetLabel = null;

	public BetPanel(int _playerIndex, int playerTapis) {
		super();
		
		playerIndex = _playerIndex;
		maxBet = playerTapis;
		currentBetLabel = new JLabel(""+minBet);
		setLayout(new BorderLayout());
		setContent();
	}
	
	private void setContent() {
		currentBetLabel.setFont(new Font(currentBetLabel.getFont().getName(), Font.PLAIN, currentBetLabel.getFont().getSize() + 10));
		currentBetLabel.setHorizontalAlignment(JLabel.CENTER);
		this.add(currentBetLabel, BorderLayout.PAGE_START);
		
		JSlider bet = new JSlider(JSlider.HORIZONTAL, minBet, maxBet, minBet);
		
		Hashtable<Integer, JLabel> table = new Hashtable<Integer, JLabel>();
		table.put( new Integer( minBet ), new JLabel(""+minBet) );
		table.put( new Integer( maxBet/2 ), new JLabel(""+(maxBet/2)) );
		table.put( new Integer( maxBet ), new JLabel(""+maxBet) );
		bet.setLabelTable(table);
		bet.setPaintLabels(true);
		
		bet.addChangeListener(this);
		this.add(bet, BorderLayout.PAGE_END);
	}
	
	public int getBet() {
		return currentBet;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		currentBet = ((JSlider)e.getSource()).getValue();
		currentBetLabel.setText(""+currentBet);
	}
}

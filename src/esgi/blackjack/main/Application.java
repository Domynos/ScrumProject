package esgi.blackjack.main;

import java.awt.EventQueue;

import esgi.blackjack.windows.MainWindow;

public class Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Runnable r = new Runnable() {
			public void run() {
				MainWindow first = new MainWindow();
				first.setVisible(true);
			}
		};
		EventQueue.invokeLater(r);
	}

}

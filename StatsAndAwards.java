package schoolWork;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class StatsAndAwards {
	JFrame statsAndAwardsFrame;
	JPanel statsAndAwardsPanel;
	JButton createAccountOkButton;
	JLabel playerGamerTagPrompt, welcomePrompt;
	JTextField playerGamerTagField;
	ArrayList<CheckersAccount> checkersAccounts = new ArrayList<>();

	public StatsAndAwards() {
		statsAndAwardsFrame = new JFrame("Stats and Awards");
		statsAndAwardsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		statsAndAwardsFrame.setSize(290, 270);
		statsAndAwardsFrame.setLocation(550, 200);
		statsAndAwardsFrame.setVisible(true);
		statsAndAwardsPanel = new JPanel();
	}

}

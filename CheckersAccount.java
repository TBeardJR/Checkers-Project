package schoolWork;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CheckersAccount {
	private String gamerTag = "";
	private int wins = 0;
	private int losses = 0;
	private int matchesPlayed = 0;
	JFrame createAccountFrame;
	JPanel createAccountPanel;
	JButton createAccountOkButton;
	JLabel playerGamerTagPrompt, welcomePrompt;
	JTextField playerGamerTagField;
	ArrayList<CheckersAccount> checkersAccounts = new ArrayList<>();
	
	public CheckersAccount()
	{
		//gamerTag = gamerTag_;		
		createAccountFrame = new JFrame("Enter GamerTag");
		createAccountFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createAccountFrame.setSize(290, 270);
		createAccountFrame.setLocation(550, 200);
		createAccountFrame.setVisible(true);
		createAccountPanel = new JPanel();
		createAccountFrame.add(createAccountPanel);
		createAccountFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		welcomePrompt = new JLabel("Welcome to Checkers!");
		playerGamerTagPrompt = new JLabel("Enter your GamerTag ");
		playerGamerTagField = new JTextField("", 15);
		playerGamerTagField.addKeyListener(new KeyAdapter() {
			// Validate gamertag

			public void keyReleased(KeyEvent e) {
				int ENTER_KEY = 10;
				int SHIFT_KEY = 16;
				int CAPS_LOCK_KEY = 20;
				int SPACE_BAR_KEY = 32;
				int LEFT_ARROW_KEY = 37;
				int UP_ARROW_KEY = 38;
				int RIGHT_ARROW_KEY = 39;
				int DOWN_ARROW_KEY = 40;
				// Get a reference to the text field
				JTextField textField = (JTextField) e.getSource();

				// Capture text in the text field
				String text = textField.getText();

				// Capture each key pressed.
				int keyCode = e.getKeyCode();

				// Make sure the gamer tag is no longer than 15
				// characters
				if ((text.length() > 15 || text.length() == 0)) {
					// Gamertag too long. Stop them from clicking the OK
					createAccountOkButton.setEnabled(false);
					JOptionPane
							.showMessageDialog(
									null,
									"Gamertag must be between 1 and 15 characters.",
									"Validation Error",
									JOptionPane.ERROR_MESSAGE);
				} else {
					createAccountOkButton.setEnabled(true);
				}

				// Need to make sure only letters and numbers are
				// allowed
				if (keyCode == ENTER_KEY) {
					createAccountOkButton.doClick();
				} else if (Character.isLetterOrDigit(keyCode) == false
						&& Character.isIdentifierIgnorable(keyCode) == false
						&& keyCode != SHIFT_KEY
						&& keyCode != CAPS_LOCK_KEY
						&& keyCode != SPACE_BAR_KEY
						&& keyCode != LEFT_ARROW_KEY
						&& keyCode != UP_ARROW_KEY
						&& keyCode != RIGHT_ARROW_KEY
						&& keyCode != DOWN_ARROW_KEY) {
					textField.setText("");
					JOptionPane
							.showMessageDialog(
									null,
									"Only letters and numbers can be used in your gamertag.",
									"Validation Error",
									JOptionPane.ERROR_MESSAGE);
				}
			}
		});		
		playerGamerTagField.setText("Player 1");
		createAccountPanel.setLayout(new GridLayout(6, 5, 0, 0));
		createAccountPanel.add(welcomePrompt);
		welcomePrompt.setLocation(150, 0);
		createAccountPanel.add(new JLabel());
		createAccountPanel.add(playerGamerTagPrompt);
		createAccountPanel.add(playerGamerTagField);
		createAccountPanel.add(new JLabel());
		createAccountOkButton = new JButton("Ok");		
		createAccountPanel.add(createAccountOkButton);		
		if(checkersAccounts.isEmpty() == true){
			createAccountFrame.setVisible(true);
			JOptionPane
			.showMessageDialog(
					createAccountFrame,
					"Welcome to Checkers! To get started you have to create an account!",
					"Create Account",
					JOptionPane.INFORMATION_MESSAGE);			
		}
	}	
	public CheckersAccount(String gamerTag_, int wins_, int losses_, int matchesPlayed_)
	{
		gamerTag = gamerTag_;
		wins = wins_;
		losses = losses_;
		matchesPlayed = matchesPlayed_;
	}

	public void updateAccount(boolean isWinner, boolean isLoser)
	{
		matchesPlayed++;
		if(isWinner == true)
			wins++;
		else
			losses++;
	}	
	public void setGamerTag(String gamerTag_)
	{
		gamerTag = gamerTag_;
	}
	public String getGamerTag()
	{
		return gamerTag;
	}
	public int getNumberOfWins()
	{
		return wins;
	}
	public int getNumberOfLosses()
	{
		return losses;
	}
	public int getNumberOfMatchesPlayed()
	{
		return matchesPlayed;
	}
	
}

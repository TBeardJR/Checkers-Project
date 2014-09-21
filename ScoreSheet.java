package schoolWork;
import javax.swing.*;
import javax.swing.UIManager.*;
import javax.swing.table.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
public class ScoreSheet {
	
	{
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			{
				// If Nimbus is not available, you can set the GUI to another
				// look and feel.
			}
		}
	}
	
	JFrame scoreSheetFrame;
	JPanel upperScoreSheetPanel, lowerScoreSheetPanel, buttonPanel;
	JButton nextRoundButton, nextPlayersTurnButton;
	static JButton finishButton;
	public static final int OPTION_YES = 0;
    public static final int OPTION_NO = 1;
    public int optionResponse = OPTION_NO;
    public Object[] options = { "Yes", "No" };
	
	//Table data
	//Upper Section
	JTable upperSectionScoreSheet;
	JScrollPane upperScrollPane;
	String[] upperSectionColumnNames = {"UpperSection", "How to Score", "Game #1" , "Game #2", "Game #3", "Game #4"};
	Object[][] upperSectionGrid = {
			{"Aces = 1", "Count and add only one","","","",""},
			{"Twos = 2", "Count and add only two","","","",""},
			{"Threes = 3", "Count and add only three","","","",""},
			{"Fours = 4", "Count and add only four","","","",""},
			{"Fives = 5", "Count and add only five","","","",""},
			{"Sixes = 6", "Count and add only one","","","",""},
			{"TOTAL SCORE", "---->","","","",""},
			{"BONUS", "If score is 63 or more","","","",""},
			{"UPPER TOTAL", "---->","","","",""}
	};
	//Lower Section
	JTable lowerSectionScoreSheet;
	JScrollPane lowerScrollPane;
	String[] lowerSectionColumnNames = {"LowerSection", "", "" , "", "", ""};
	Object[][] lowerSectionGrid = {
			{"3 of a Kind", "Add Total of all dice","","","",""},
			{"4 of a Kind", "Add Total of all dice","","","",""},
			{"Full House", "Score 25","","","",""},
			{"Sm. Straight", "Score 30","","","",""},
			{"Lg. Straight", "Score 40","","","",""},
			{"YAHTZEE", "Score 50","","","",""},
			{"Chance", "Score total of all 5 dice","","","",""},
			{"YAHTZEE BONUS", "Check for each bonus","","","",""},
			{"LOWER TOTAL", "---->","","","",""},
			{"GRAND TOTAL", "---->","","","",""}
	};
	
	

	
	

	public ScoreSheet() {
		
		
		scoreSheetFrame = new JFrame("Yahtzee Score Sheet");
		scoreSheetFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		upperScoreSheetPanel = new JPanel();		
		lowerScoreSheetPanel = new JPanel();
		buttonPanel = new JPanel();
		scoreSheetFrame.setLayout(new BorderLayout() );
		//upper score sheet
		scoreSheetFrame.add(upperScoreSheetPanel);
		scoreSheetFrame.add(upperScoreSheetPanel, BorderLayout.NORTH);
		upperSectionScoreSheet = new JTable(upperSectionGrid, upperSectionColumnNames);
		upperScoreSheetPanel.setLayout(new BorderLayout());
		upperScoreSheetPanel.add(upperSectionScoreSheet.getTableHeader(), BorderLayout.PAGE_START);
		upperScoreSheetPanel.add(upperSectionScoreSheet, BorderLayout.NORTH);
		upperSectionScoreSheet.setEnabled(false);
		upperScrollPane = new JScrollPane(upperSectionScoreSheet);
		upperSectionScoreSheet.setFillsViewportHeight(true);
		upperScoreSheetPanel.add(upperSectionScoreSheet);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( DefaultTableCellRenderer.CENTER );
		for(int x = 0 ; x < upperSectionColumnNames.length; x++){
			upperSectionScoreSheet.getColumnModel().getColumn(x).setCellRenderer( centerRenderer );
	        }
		
		
		//lower score sheet		
		scoreSheetFrame.add(lowerScoreSheetPanel);
		scoreSheetFrame.add(lowerScoreSheetPanel, BorderLayout.CENTER);
		lowerSectionScoreSheet = new JTable(lowerSectionGrid, lowerSectionColumnNames);
		lowerScoreSheetPanel.setLayout(new BorderLayout());
		lowerScoreSheetPanel.add(lowerSectionScoreSheet.getTableHeader(), BorderLayout.PAGE_START);
		lowerScoreSheetPanel.add(lowerSectionScoreSheet, BorderLayout.CENTER);
		lowerSectionScoreSheet.setEnabled(false);
		lowerScrollPane = new JScrollPane(lowerSectionScoreSheet);
		lowerSectionScoreSheet.setFillsViewportHeight(true);
		lowerScoreSheetPanel.add(lowerSectionScoreSheet);
		scoreSheetFrame.add(buttonPanel, BorderLayout.SOUTH);
		nextPlayersTurnButton = new JButton();	
		buttonPanel.add(nextPlayersTurnButton);
		nextRoundButton = new JButton("Next Round");
		buttonPanel.add(nextRoundButton);
		nextRoundButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scoreSheetFrame.setVisible(false);				
			}
		});
		finishButton = new JButton("Finish");	
		buttonPanel.add(finishButton);	
		centerRenderer.setHorizontalAlignment( DefaultTableCellRenderer.CENTER );
		for(int x = 0 ; x < lowerSectionColumnNames.length; x++){
			lowerSectionScoreSheet.getColumnModel().getColumn(x).setCellRenderer( centerRenderer );
	        }
		
		scoreSheetFrame.setSize(860, 430);	
		//scoreSheetFrame.pack();
		scoreSheetFrame.setVisible(false);		
		
	}
	public void updateUpperScoreSheet(int updateScoreEvent, int score, int game)
	{
		upperSectionGrid[updateScoreEvent][game] = score;
		upperScoreSheetPanel.revalidate();
		upperScoreSheetPanel.repaint();
		
	}
	public void updateLowerScoreSheet(int updateScoreEvent, int score, int game)
	{		
		lowerSectionGrid[updateScoreEvent][game] = score;
		lowerScoreSheetPanel.revalidate();
		lowerScoreSheetPanel.repaint();
	}
	public void promptPlayerForNewGame()
	{
		optionResponse = JOptionPane.showOptionDialog(scoreSheetFrame, "Do you want to play again?", "Game Finished", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
			    options[0]);
	}
	public static void main(String[] args)
	{
		new ScoreSheet();
	}

}

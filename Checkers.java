package schoolWork;

import java.awt.event.*;
import java.awt.datatransfer.*;
import java.awt.*;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

public class Checkers extends TransferHandler {	
	public JFrame checkersFrame, checkersMainMenuFrame, createAccountFrame;
	public JPanel checkersPanel, checkersMainMenuPanel, createAccountPanel, titlePanel;
	public JButton eventButton, sourceButton, sourceButton2, singlePlayerButton, multiplayerButton, howToPlayButton, leaderBoardsButton,
					achievementsButton, statsAndAwardsButton;
	public JButton[][] checkersSpaces = new JButton[8][8]; 
	public JButton[] mainMenuButtons = { singlePlayerButton, multiplayerButton, howToPlayButton, leaderBoardsButton,
							achievementsButton, statsAndAwardsButton };
	public String[] mainMenuButtonTitles = { "Single Player", "Multiplayer", "How to Play", "Leaderboards", "Achievements",
						"Stats and Awards" };	
	
	public ImageIcon playerOneCheckerPiece, playerTwoCheckerPiece, playerOneMoveReadyIcon, playerTwoMoveReadyIcon,
			playerOneKingCheckerPiece, playerOneKingMoveReadyIcon, playerTwoKingCheckerPiece, playerTwoKingMoveReadyIcon;
	public Icon sourceIcon;		
	public ActionListener[][] checkersEvents = new ActionListener[8][8];
	public String[] possibleMoves = new String[8];
	public JButton[] checkerPiecesCPU = new JButton[12];
	public boolean canDeliver = true;
	public boolean isMoveReady = false;
	public boolean isMoveLegal = false;	
	public boolean isAttackLegal = false;
	public boolean isMultipleAttack = false;
	public boolean isPlayerOneVictor = false;
	public boolean isPlayerTwoVictor = false;
	public boolean isNorthEast = false;
	public boolean isNorthWest = false;
	public boolean isSouthEast = false;
	public boolean isSouthWest = false;
	public boolean isNorthEastJump = false;
	public boolean isNorthWestJump = false;
	public boolean isSouthEastJump = false;
	public boolean isSouthWestJump = false;
	public boolean[] isMoveLegalPositions = new boolean[4];
	public int turnCounter = 0;	
	public int targetRow, targetColumn, previousRow, previousColumn;	 
	public static final int OPTION_YES = 0;
    public static final int OPTION_NO = 1;
    public int optionResponse = OPTION_NO;
    public Object[] options = { "Yes", "No" };
    CheckersCharacters checkersCharacters = new CheckersCharacters();
    CheckersAccount checkersAccount;

	public Checkers() {
//		{
//			try {
//				for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
//					if ("Nimbus".equals(info.getName())) {
//						UIManager.setLookAndFeel(info.getClassName());
//						break;
//					}
//				}
//			} catch (Exception e) {
//				{
//					// If Nimbus is not available, you can set the GUI to another
//					// look and feel.
//				}
//			}
//		}
		//Main Menu Frame
		checkersMainMenuFrame = new JFrame("Checkers");
		checkersMainMenuPanel = new JPanel();
		//titlePanel = new JPanel();
		//checkersMainMenuFrame.add(titlePanel, BorderLayout.NORTH);
		checkersMainMenuPanel.setLayout(new GridLayout(1, 1, 0, 0));
		checkersMainMenuFrame.add(checkersMainMenuPanel, BorderLayout.CENTER);
		Font font = new Font("serif", Font.ITALIC, 32);
		for(int x = 0; x < mainMenuButtons.length; x++)
		{
			mainMenuButtons[x] = new JButton(mainMenuButtonTitles[x]);
			checkersMainMenuPanel.setBackground(Color.red);
			mainMenuButtons[x].setBackground(Color.black);
			mainMenuButtons[x].setForeground(Color.red);					
			mainMenuButtons[x].setFont(font);
			checkersMainMenuPanel.add(mainMenuButtons[x]);
		}		
		//Multi-player Button
		mainMenuButtons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {							
				checkersCharacters.chooseCharacterPanel.add(checkersCharacters.chooseCharacterOkButton);
				checkersCharacters.chooseCharacterFrame.setVisible(true);
				JOptionPane
				.showMessageDialog(
						checkersCharacters.chooseCharacterFrame,
						"Player 1 choose your character.",
						"Character Choice",
						JOptionPane.INFORMATION_MESSAGE);

			}
		});
		checkersMainMenuPanel.setLayout(new GridLayout(3, 2, 0, 0));
		checkersMainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		checkersMainMenuFrame.setLocation(350, 30);
		checkersMainMenuFrame.setSize(700, 700);
		checkersMainMenuFrame.setVisible(false);
		//Create Account frame
		checkersAccount = new CheckersAccount();
		checkersAccount.createAccountOkButton.setActionCommand("Ok");
		checkersAccount.createAccountOkButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				checkersAccount.setGamerTag(checkersAccount.playerGamerTagField.getText());	
				CheckersAccount newAccount = new CheckersAccount(checkersAccount.getGamerTag(),0,0,0);
				checkersAccount.checkersAccounts.add(newAccount);
				checkersAccount.createAccountFrame.setVisible(false);				
				checkersMainMenuFrame.setVisible(true);
				JOptionPane
				.showMessageDialog(
						checkersMainMenuFrame,
						"Thank you " + checkersAccount.getGamerTag() + " for making an account!\n",
						"Thank you",
						JOptionPane.INFORMATION_MESSAGE);	

			}
		});
		checkersCharacters.chooseCharacterOkButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkersCharacters.charactersChosen++;
				if(checkersCharacters.charactersChosen == 1)
				{
					playerOneCheckerPiece = checkersCharacters.playerCheckerPiece;
					playerOneMoveReadyIcon = checkersCharacters.playerMoveReadyIcon;
					playerOneKingCheckerPiece = checkersCharacters.playerKingCheckerPiece;
					playerOneKingMoveReadyIcon = checkersCharacters.playerKingMoveReadyIcon;					
					JOptionPane
					.showMessageDialog(
							checkersCharacters.chooseCharacterFrame,
							"Player 2 choose your character.",
							"Character Choice",
							JOptionPane.INFORMATION_MESSAGE);
					checkersCharacters.eventButton = checkersCharacters.sourceButton;
					checkersCharacters.sourceButton = checkersCharacters.chooseCharacterOkButton;
					checkersCharacters.chooseCharacterOkButton.setEnabled(false);
				}
				else if(checkersCharacters.charactersChosen == 2)
				{
					playerTwoCheckerPiece = checkersCharacters.playerCheckerPiece;
					playerTwoMoveReadyIcon = checkersCharacters.playerMoveReadyIcon;
					playerTwoKingCheckerPiece = checkersCharacters.playerKingCheckerPiece;
					playerTwoKingMoveReadyIcon = checkersCharacters.playerKingMoveReadyIcon;
					sourceButton2 = checkersCharacters.sourceButton;
					checkersCharacters.chooseCharacterFrame.setVisible(false);
					setCheckerBoard();
					setCheckerPieceActionListeners();
					checkersMainMenuFrame.setVisible(false);
					checkersFrame.setVisible(true);
				}

			}
		});
		
		//Actual game frame
		checkersFrame = new JFrame("Checkers");
		checkersPanel = new JPanel();
		checkersFrame.add(checkersPanel);
		checkersPanel.setLayout(new GridLayout(8, 8, 0, 0));
		checkersFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		checkersFrame.setLocation(350, 30);
		checkersFrame.setSize(700, 700);
		checkersFrame.setVisible(false);	
		checkersPanel.revalidate();
		checkersPanel.repaint();
	}
	
	public void setCheckerBoard() {
		for (int x = 0; x < checkersSpaces.length; x++) {
			for (int y = 0; y < checkersSpaces.length; y++) {
				checkersSpaces[x][y] = new JButton("");
				if (x % 2 == 0) {
					if (y % 2 == 0) {
						checkersSpaces[x][y].setBackground(Color.white);
						checkersSpaces[x][y].setEnabled(false);
					} else {
						checkersSpaces[x][y].setBackground(Color.black);
						if(x < 3)
						{
							checkersSpaces[x][y].setIcon(playerOneCheckerPiece);							
						}
						else if(x > 4)
						{
							checkersSpaces[x][y].setIcon(playerTwoCheckerPiece);							
						}
					}
				} else {
					if (y % 2 == 0) {
						checkersSpaces[x][y].setBackground(Color.black);
						if(x < 3)
						{
							checkersSpaces[x][y].setIcon(playerOneCheckerPiece);							
						}
						else if(x > 4)
						{
							checkersSpaces[x][y].setIcon(playerTwoCheckerPiece);							
						}
					} else {
						checkersSpaces[x][y].setBackground(Color.white);
						checkersSpaces[x][y].setEnabled(false);
					}
				}
				checkersPanel.add(checkersSpaces[x][y]);
			}
		}
		disablePlayerTwoSpaces();
	}
	public void setCheckerPieceActionListeners()
	{
		for (int x = 0; x < checkersSpaces.length; x++) {
			for (int y = 0; y < checkersSpaces.length; y++) {				
					checkersEvents[x][y] = new ActionListener(){						
						 public void actionPerformed(ActionEvent e){ 
							 eventButton = (JButton)e.getSource();
							 matchEventButton();
								if(eventButton.getIcon() != null && (eventButton.getIcon().equals(playerOneCheckerPiece) || eventButton.getIcon().equals(playerTwoCheckerPiece)
										||  eventButton.getIcon().equals(playerOneKingCheckerPiece) || eventButton.getIcon().equals(playerTwoKingCheckerPiece)))										
								{	
									previousRow = targetRow;
									previousColumn = targetColumn;
									sourceIcon = eventButton.getIcon();
									sourceButton = eventButton;
									if(eventButton.getIcon().equals(playerOneKingCheckerPiece))
										eventButton.setIcon(playerOneKingMoveReadyIcon);
									else if(eventButton.getIcon().equals(playerTwoKingCheckerPiece))
										eventButton.setIcon(playerTwoKingMoveReadyIcon);
									else if(eventButton.getIcon().equals(playerOneCheckerPiece))
										eventButton.setIcon(playerOneMoveReadyIcon);
									else
										eventButton.setIcon(playerTwoMoveReadyIcon);
									disableOccupiedSpaces();
									eventButton.setEnabled(true);
									isMoveReady = true;		
									
								}
								else if(eventButton.getIcon() == null && isMoveReady == true)
								{	
									if(isMoveLegit() == true)
									{
										isMoveReady = false;
										isMoveLegal = false;
										sourceButton.setIcon(null);
										if(isAttackLegal == true && canMoveAgain() == true){											
											if(sourceIcon.equals(playerOneKingCheckerPiece))
												eventButton.setIcon(playerOneKingMoveReadyIcon);
											else if(sourceIcon.equals(playerTwoKingCheckerPiece))
												eventButton.setIcon(playerTwoKingMoveReadyIcon);
											else if(sourceIcon.equals(playerOneCheckerPiece))
												eventButton.setIcon(playerOneMoveReadyIcon);
											else
												eventButton.setIcon(playerTwoMoveReadyIcon);
											previousRow = targetRow;
											previousColumn = targetColumn;
											sourceButton = eventButton;
											isMultipleAttack = true;
											isMoveReady = true;
											return;	
										}	
										isAttackLegal = false;
										eventButton.setIcon(sourceIcon);										
										enableOccupiedSpaces();								
										if(turnCounter % 2 == 0){
											disablePlayerOneSpaces();
											enablePlayerTwoSpaces();
											}else{
												disablePlayerTwoSpaces();
												enablePlayerOneSpaces();
											}
										turnCounter++;
										determineVictor();
									}									
									else
									{
										JOptionPane
										.showMessageDialog(
												null,
												"Illegal move. Please Try again.",
												"Illegal Move Attempt",
												JOptionPane.ERROR_MESSAGE);
									}
								}
								else if(eventButton.getIcon() != null)
								{									
									eventButton.setIcon(sourceIcon);
									if((sourceIcon.equals(playerOneCheckerPiece) || sourceIcon.equals(playerOneKingCheckerPiece))&& isMultipleAttack == false)
										enablePlayerOneSpaces();									
									else if((sourceIcon.equals(playerTwoCheckerPiece) || sourceIcon.equals(playerTwoKingCheckerPiece))&& isMultipleAttack == false)
										enablePlayerTwoSpaces();
									else if(sourceIcon.equals(playerOneCheckerPiece) || sourceIcon.equals(playerTwoKingCheckerPiece) ||
											(sourceIcon.equals(playerOneKingCheckerPiece) && isMultipleAttack == true)){
										enablePlayerTwoSpaces();
										disablePlayerOneSpaces();	
										turnCounter++;
									}else if(sourceIcon.equals(playerTwoCheckerPiece) || sourceIcon.equals(playerOneKingCheckerPiece) ||
											(sourceIcon.equals(playerTwoKingCheckerPiece) && isMultipleAttack == true)){
										enablePlayerOneSpaces();
										disablePlayerTwoSpaces();
										turnCounter++;
									}
									isMultipleAttack = false;
									isMoveReady = false;
								}
											
								
						 }						 
					 };							
				 checkersSpaces[x][y].addActionListener(checkersEvents[x][y]);	
			}
		}		
	}	
	public void matchEventButton()
	{
		for (int x = 0; x < checkersSpaces.length; x++) {
			for (int y = 0; y < checkersSpaces.length; y++) {
				if(checkersSpaces[x][y].equals(eventButton))
				{
					targetRow = x;
					targetColumn = y;					
				}
			}
		}
	}	
	public boolean isMoveLegit()
	{		
		if((sourceIcon.equals(playerOneCheckerPiece) || sourceIcon.equals(playerOneKingCheckerPiece) || sourceIcon.equals(playerTwoKingCheckerPiece)) && 
				targetRow - previousRow == 1 && (targetColumn - previousColumn == 1 || previousColumn - targetColumn == 1))				
		{
			isMoveLegal = true;
		}	
		else if((sourceIcon.equals(playerTwoCheckerPiece) || sourceIcon.equals(playerOneKingCheckerPiece) || sourceIcon.equals(playerTwoKingCheckerPiece)) && 
				targetRow - previousRow == -1 && (targetColumn - previousColumn == 1 || previousColumn - targetColumn == 1))				
		{
			isMoveLegal = true;
		}
		else if((sourceIcon.equals(playerOneCheckerPiece) || sourceIcon.equals(playerOneKingCheckerPiece)) && 
				targetRow - previousRow == 2 && previousColumn - targetColumn == 2)								
		{
			//Player 1/king jumps player 2 diagonally down left
			if(checkersSpaces[targetRow - 1][targetColumn + 1].getIcon() != null &&
				(checkersSpaces[targetRow - 1][targetColumn + 1].getIcon().equals(playerTwoCheckerPiece) || 
				checkersSpaces[targetRow - 1][targetColumn + 1].getIcon().equals(playerTwoKingCheckerPiece)))							
			{
				isAttackLegal = true;
				isMultipleAttack = false;
				checkersSpaces[targetRow - 1][targetColumn + 1].setIcon(null);
				checkersSpaces[targetRow - 1][targetColumn + 1].setEnabled(true);	
				isMoveLegal = true;	
			}					
		}
		
		else if((sourceIcon.equals(playerOneCheckerPiece) || sourceIcon.equals(playerOneKingCheckerPiece)) && 
				targetRow - previousRow == 2 && targetColumn - previousColumn == 2)					
		{
			//Player 1/king jumps player 2 diagonally down right
			if(checkersSpaces[targetRow - 1][targetColumn - 1].getIcon() != null &&
					(checkersSpaces[targetRow - 1][targetColumn - 1].getIcon().equals(playerTwoCheckerPiece) ||
					checkersSpaces[targetRow - 1][targetColumn - 1].getIcon().equals(playerTwoKingCheckerPiece)))
			{
				isAttackLegal = true;
				isMultipleAttack = false;
				checkersSpaces[targetRow - 1][targetColumn - 1].setIcon(null);
				checkersSpaces[targetRow - 1][targetColumn - 1].setEnabled(true);	
				isMoveLegal = true;	
			}					
		}
		else if(sourceIcon.equals(playerOneKingCheckerPiece) && targetRow - previousRow == -2 && 
				previousColumn - targetColumn == -2)				
		{
			//Player 1 king jumps player 2 diagonally up right
			if(checkersSpaces[targetRow + 1][targetColumn - 1].getIcon() != null &&
					(checkersSpaces[targetRow + 1][targetColumn - 1].getIcon().equals(playerTwoCheckerPiece) ||
					checkersSpaces[targetRow + 1][targetColumn - 1].getIcon().equals(playerTwoKingCheckerPiece)))
			{
				isAttackLegal = true;
				isMultipleAttack = false;
				checkersSpaces[targetRow + 1][targetColumn - 1].setIcon(null);
				checkersSpaces[targetRow + 1][targetColumn - 1].setEnabled(true);	
				isMoveLegal = true;	
			}			
					
		}
		else if(sourceIcon.equals(playerOneKingCheckerPiece) && targetRow - previousRow == -2 && 
				targetColumn - previousColumn == -2)				
		{			
			//Player 1 king jumps player 2 diagonally up left
			if(checkersSpaces[targetRow + 1][targetColumn + 1].getIcon() != null &&
					(checkersSpaces[targetRow + 1][targetColumn + 1].getIcon().equals(playerTwoCheckerPiece) ||
					checkersSpaces[targetRow + 1][targetColumn + 1].getIcon().equals(playerTwoKingCheckerPiece))) 
			{
				isAttackLegal = true;
				isMultipleAttack = false;
				checkersSpaces[targetRow + 1][targetColumn + 1].setIcon(null);
				checkersSpaces[targetRow + 1][targetColumn + 1].setEnabled(true);	
				isMoveLegal = true;	
			}
					
		}
		
		else if((sourceIcon.equals(playerTwoCheckerPiece) || sourceIcon.equals(playerTwoKingCheckerPiece)) && 
					targetRow - previousRow == -2 && previousColumn - targetColumn == -2)								
		{
			//Player 2 jumps player 1 diagonally up right
			if(checkersSpaces[targetRow + 1][targetColumn - 1].getIcon() != null &&
					(checkersSpaces[targetRow + 1][targetColumn - 1].getIcon().equals(playerOneCheckerPiece) || 
							checkersSpaces[targetRow + 1][targetColumn - 1].getIcon().equals(playerOneKingCheckerPiece)))
			{
				isAttackLegal = true;
				isMultipleAttack = false;
				checkersSpaces[targetRow + 1][targetColumn - 1].setIcon(null);
				checkersSpaces[targetRow + 1][targetColumn - 1].setEnabled(true);	
				isMoveLegal = true;	
			}			
					
		}
		else if((sourceIcon.equals(playerTwoCheckerPiece) || sourceIcon.equals(playerTwoKingCheckerPiece)) && 
					targetRow - previousRow == -2 && targetColumn - previousColumn == -2)
								
		{			
			//Player 2 jumps player 1 diagonally up left
			if(checkersSpaces[targetRow + 1][targetColumn + 1].getIcon() != null &&
					(checkersSpaces[targetRow + 1][targetColumn + 1].getIcon().equals(playerOneCheckerPiece) || 
					checkersSpaces[targetRow + 1][targetColumn + 1].getIcon().equals(playerOneKingCheckerPiece))) 
			{
				isAttackLegal = true;
				isMultipleAttack = false;
				checkersSpaces[targetRow + 1][targetColumn + 1].setIcon(null);
				checkersSpaces[targetRow + 1][targetColumn + 1].setEnabled(true);	
				isMoveLegal = true;	
			}
					
		}
		else if(sourceIcon.equals(playerTwoKingCheckerPiece) && 
				targetRow - previousRow == 2 && previousColumn - targetColumn == 2)								
		{
			//Player 2 king jumps player 1 diagonally down left
			if(checkersSpaces[targetRow - 1][targetColumn + 1].getIcon() != null &&
				(checkersSpaces[targetRow - 1][targetColumn + 1].getIcon().equals(playerOneCheckerPiece) || 
				checkersSpaces[targetRow - 1][targetColumn + 1].getIcon().equals(playerOneKingCheckerPiece)))							
			{
				isAttackLegal = true;
				isMultipleAttack = false;
				checkersSpaces[targetRow - 1][targetColumn + 1].setIcon(null);
				checkersSpaces[targetRow - 1][targetColumn + 1].setEnabled(true);	
				isMoveLegal = true;	
			}					
		}
		else if(sourceIcon.equals(playerTwoKingCheckerPiece) && 
				targetRow - previousRow == 2 && targetColumn - previousColumn == 2)					
		{
			//Player 2 king jumps player 1 diagonally down right
			if(checkersSpaces[targetRow - 1][targetColumn - 1].getIcon() != null &&
					(checkersSpaces[targetRow - 1][targetColumn - 1].getIcon().equals(playerOneCheckerPiece) ||
					checkersSpaces[targetRow - 1][targetColumn - 1].getIcon().equals(playerOneKingCheckerPiece)))
			{
				isAttackLegal = true;
				isMultipleAttack = false;
				checkersSpaces[targetRow - 1][targetColumn - 1].setIcon(null);
				checkersSpaces[targetRow - 1][targetColumn - 1].setEnabled(true);	
				isMoveLegal = true;	
			}					
		}
		//Make player one king
		if(sourceIcon.equals(playerOneCheckerPiece) && targetRow == 7)
		{
			sourceIcon = playerOneKingCheckerPiece;
		}
		//Make player two king
		else if(sourceIcon.equals(playerTwoCheckerPiece) && targetRow == 0)
		{
			sourceIcon = playerTwoKingCheckerPiece;
		}
		
		return isMoveLegal;			
	}
	public boolean canMoveAgain()
	{
		//Checks to see if Player 1/King can jump Player 2 diagonally down left
		if((sourceIcon.equals(playerOneCheckerPiece) || sourceIcon.equals(playerOneKingCheckerPiece)) && sourceIcon != null && (targetColumn > 1 && targetRow < 6) &&
				checkersSpaces[targetRow + 1][targetColumn - 1].getIcon() != null &&
				(checkersSpaces[targetRow + 1][targetColumn - 1].getIcon().equals(playerTwoCheckerPiece) ||
				checkersSpaces[targetRow + 1][targetColumn - 1].getIcon().equals(playerTwoKingCheckerPiece)) &&
				checkersSpaces[targetRow + 2][targetColumn - 2].getIcon() == null){
			isMoveReady = true;
		}
		//Checks to see if Player 1/King can jump Player 2 diagonally down right
		else if((sourceIcon.equals(playerOneCheckerPiece) || sourceIcon.equals(playerOneKingCheckerPiece))&& sourceIcon != null && (targetColumn < 6 && targetRow < 6) &&
				checkersSpaces[targetRow + 1][targetColumn + 1].getIcon() != null &&
				(checkersSpaces[targetRow + 1][targetColumn + 1].getIcon().equals(playerTwoCheckerPiece) ||
				checkersSpaces[targetRow + 1][targetColumn + 1].getIcon().equals(playerTwoKingCheckerPiece)) &&
				checkersSpaces[targetRow + 2][targetColumn + 2].getIcon() == null){				
			isMoveReady = true;
		}
		//Checks to see if Player 1 King can jump Player 2 diagonally up left
		else if(sourceIcon.equals(playerOneKingCheckerPiece) && sourceIcon != null && (targetColumn > 1 && targetRow > 1) &&
				checkersSpaces[targetRow - 1][targetColumn - 1].getIcon() != null &&
				(checkersSpaces[targetRow - 1][targetColumn - 1].getIcon().equals(playerTwoCheckerPiece) ||
				checkersSpaces[targetRow - 1][targetColumn - 1].getIcon().equals(playerTwoKingCheckerPiece)) &&
				checkersSpaces[targetRow - 2][targetColumn - 2].getIcon() == null){
			isMoveReady = true;
		}
		//Checks to see if Player 1 King can jump Player 1 diagonally up right
		else if(sourceIcon.equals(playerOneKingCheckerPiece) && sourceIcon != null && (targetColumn < 6 && targetRow > 1) &&
				checkersSpaces[targetRow - 1][targetColumn + 1].getIcon() != null &&
				(checkersSpaces[targetRow - 1][targetColumn + 1].getIcon().equals(playerTwoCheckerPiece) ||
				checkersSpaces[targetRow - 1][targetColumn + 1].getIcon().equals(playerTwoKingCheckerPiece)) &&
				checkersSpaces[targetRow - 2][targetColumn + 2].getIcon() == null){
			isMoveReady = true;
		}				
		//Checks to see if Player 2/King can jump Player 1 diagonally up left
		else if((sourceIcon.equals(playerTwoCheckerPiece) || sourceIcon.equals(playerTwoKingCheckerPiece)) && sourceIcon != null && (targetColumn > 1 && targetRow > 1) &&
				checkersSpaces[targetRow - 1][targetColumn - 1].getIcon() != null &&
				(checkersSpaces[targetRow - 1][targetColumn - 1].getIcon().equals(playerOneCheckerPiece) ||
				checkersSpaces[targetRow - 1][targetColumn - 1].getIcon().equals(playerOneKingCheckerPiece)) &&
				checkersSpaces[targetRow - 2][targetColumn - 2].getIcon() == null){
			isMoveReady = true;
		}
		//Checks to see if Player 2/King can jump Player 1 diagonally up right
		else if((sourceIcon.equals(playerTwoCheckerPiece) || sourceIcon.equals(playerTwoKingCheckerPiece)) && sourceIcon != null && (targetColumn < 6 && targetRow > 1) &&
				checkersSpaces[targetRow - 1][targetColumn + 1].getIcon() != null &&
				(checkersSpaces[targetRow - 1][targetColumn + 1].getIcon().equals(playerOneCheckerPiece) ||
				checkersSpaces[targetRow - 1][targetColumn + 1].getIcon().equals(playerOneKingCheckerPiece)) &&
				checkersSpaces[targetRow - 2][targetColumn + 2].getIcon() == null){
			isMoveReady = true;
				}
		//Checks to see if Player 2 King can jump Player 1 diagonally down left
		else if(sourceIcon.equals(playerTwoKingCheckerPiece) && sourceIcon != null && (targetColumn > 1 && targetRow < 6) &&
				checkersSpaces[targetRow + 1][targetColumn - 1].getIcon() != null &&
				(checkersSpaces[targetRow + 1][targetColumn - 1].getIcon().equals(playerOneCheckerPiece) ||
				checkersSpaces[targetRow + 1][targetColumn - 1].getIcon().equals(playerOneKingCheckerPiece)) &&
				checkersSpaces[targetRow + 2][targetColumn - 2].getIcon() == null){
			isMoveReady = true;
				}
		//Checks to see if Player 2 King can jump Player 1 diagonally down right
		else if(sourceIcon.equals(playerTwoKingCheckerPiece)&& sourceIcon != null && (targetColumn < 6 && targetRow < 6) &&
				checkersSpaces[targetRow + 1][targetColumn + 1].getIcon() != null &&
				(checkersSpaces[targetRow + 1][targetColumn + 1].getIcon().equals(playerOneCheckerPiece) ||
				checkersSpaces[targetRow + 1][targetColumn + 1].getIcon().equals(playerOneKingCheckerPiece)) &&
				checkersSpaces[targetRow + 2][targetColumn + 2].getIcon() == null){				
			isMoveReady = true;
				}
		return isMoveReady;
	}
	public void chooseCheckerPieceCPU()
	{
		int index = 0;
		for (int x = 0; x < checkersSpaces.length; x++) {
			for (int y = 0; y < checkersSpaces.length; y++) {	
				if(checkersSpaces[x][y].getIcon() != null && (checkersSpaces[x][y].getIcon().equals(playerTwoCheckerPiece) || 
						checkersSpaces[x][y].getIcon().equals(playerTwoKingCheckerPiece)))
				{
					checkerPiecesCPU[index] = checkersSpaces[x][y];
					index++;
				}											
			}
		}
		
	}
	public void getAllPossibleMovesCPU()
	{
		int index = 0;
		if(sourceIcon.equals(playerTwoKingCheckerPiece) && 
				targetRow - previousRow == 1 && (targetColumn - previousColumn == 1 || previousColumn - targetColumn == 1))				
		{
			if(targetColumn - previousColumn == 1)
				isSouthEast = true;
			if(previousColumn - targetColumn == 1)
				isSouthWest = true;
			//isMoveLegalPositions[index]
		}	
		if(sourceIcon.equals(playerTwoCheckerPiece) && 
				targetRow - previousRow == -1 && (targetColumn - previousColumn == 1 || previousColumn - targetColumn == 1))				
		{
			if(targetColumn - previousColumn == 1)
			isNorthEast = true;
			if(previousColumn - targetColumn == 1)
			isNorthWest = true;
		}
		//Checks to see if Player 2/King can jump Player 1 diagonally up left
		if((sourceIcon.equals(playerTwoCheckerPiece) || sourceIcon.equals(playerTwoKingCheckerPiece)) && sourceIcon != null && (targetColumn > 1 && targetRow > 1) &&
				checkersSpaces[targetRow - 1][targetColumn - 1].getIcon() != null &&
				(checkersSpaces[targetRow - 1][targetColumn - 1].getIcon().equals(playerOneCheckerPiece) ||
				checkersSpaces[targetRow - 1][targetColumn - 1].getIcon().equals(playerOneKingCheckerPiece)) &&
				checkersSpaces[targetRow - 2][targetColumn - 2].getIcon() == null){
			isNorthWestJump = false;
		}
		//Checks to see if Player 2/King can jump Player 1 diagonally up right
		if((sourceIcon.equals(playerTwoCheckerPiece) || sourceIcon.equals(playerTwoKingCheckerPiece)) && sourceIcon != null && (targetColumn < 6 && targetRow > 1) &&
				checkersSpaces[targetRow - 1][targetColumn + 1].getIcon() != null &&
				(checkersSpaces[targetRow - 1][targetColumn + 1].getIcon().equals(playerOneCheckerPiece) ||
				checkersSpaces[targetRow - 1][targetColumn + 1].getIcon().equals(playerOneKingCheckerPiece)) &&
				checkersSpaces[targetRow - 2][targetColumn + 2].getIcon() == null){
			isNorthEastJump = true;
				}
		//Checks to see if Player 2 King can jump Player 1 diagonally down left
		if(sourceIcon.equals(playerTwoKingCheckerPiece) && sourceIcon != null && (targetColumn > 1 && targetRow < 6) &&
				checkersSpaces[targetRow + 1][targetColumn - 1].getIcon() != null &&
				(checkersSpaces[targetRow + 1][targetColumn - 1].getIcon().equals(playerOneCheckerPiece) ||
				checkersSpaces[targetRow + 1][targetColumn - 1].getIcon().equals(playerOneKingCheckerPiece)) &&
				checkersSpaces[targetRow + 2][targetColumn - 2].getIcon() == null){
			isSouthWestJump = true;
				}
		//Checks to see if Player 2 King can jump Player 1 diagonally down right
		if(sourceIcon.equals(playerTwoKingCheckerPiece)&& sourceIcon != null && (targetColumn < 6 && targetRow < 6) &&
				checkersSpaces[targetRow + 1][targetColumn + 1].getIcon() != null &&
				(checkersSpaces[targetRow + 1][targetColumn + 1].getIcon().equals(playerOneCheckerPiece) ||
				checkersSpaces[targetRow + 1][targetColumn + 1].getIcon().equals(playerOneKingCheckerPiece)) &&
				checkersSpaces[targetRow + 2][targetColumn + 2].getIcon() == null){				
			isSouthEastJump = true;
				}		
	}
	public void makeMoveCPU()
	{
		
	}
	public void determineVictor()
	{
		for (int x = 0; x < checkersSpaces.length; x++) {
			for (int y = 0; y < checkersSpaces.length; y++) {
				if(checkersSpaces[x][y].getIcon() != null && (checkersSpaces[x][y].getIcon().equals(playerTwoCheckerPiece) || 
						checkersSpaces[x][y].getIcon().equals(playerTwoKingCheckerPiece)))
				{						
					isPlayerOneVictor = false;
					break;
				}
				else
				{
					isPlayerOneVictor = true;
				}					
			}
			if(isPlayerOneVictor == false)
				break;
		}	
		for (int x = 0; x < checkersSpaces.length; x++) {
			for (int y = 0; y < checkersSpaces.length; y++) {
				if(checkersSpaces[x][y].getIcon() != null && (checkersSpaces[x][y].getIcon().equals(playerOneCheckerPiece) || 
						checkersSpaces[x][y].getIcon().equals(playerOneKingCheckerPiece)))
				{						
					isPlayerTwoVictor = false;
					break;
				}
				else
				{
					isPlayerTwoVictor = true;
				}					
			}
			if(isPlayerTwoVictor == false)
				break;
		}
		if(isPlayerOneVictor == true)
		{
			optionResponse = JOptionPane.showOptionDialog(checkersFrame, "Player 1 wins!\nTotal moves made: " + turnCounter + "\nDo you want to play again?", "Game Finished", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
				    options[0]);
			startNewGame();
		}
		else if(isPlayerTwoVictor == true)
		{
			optionResponse = JOptionPane.showOptionDialog(checkersFrame, "Player 2 wins!\nTotal moves made: " + turnCounter + "\nDo you want to play again?", "Game Finished", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
				    options[0]);
			startNewGame();
		}
			
	}
	public void startNewGame()
	{
		clearBoard();
		turnCounter = 0;
		if(optionResponse == OPTION_YES)
		{
			optionResponse = OPTION_NO;			
			setCheckerBoard();
			setCheckerPieceActionListeners();			
		}
		else
		{						
			checkersFrame.setVisible(false);
			checkersMainMenuFrame.setVisible(true);
			checkersCharacters.charactersChosen = 0;
			sourceButton2.setEnabled(true);
			checkersCharacters.eventButton.setEnabled(true);
			checkersCharacters.chooseCharacterOkButton.setEnabled(false);			
		}
		checkersPanel.revalidate();
		checkersPanel.repaint();
	}
	
	public void disableOccupiedSpaces()
	{
		for (int x = 0; x < checkersSpaces.length; x++) {
			for (int y = 0; y < checkersSpaces.length; y++) {
				if(checkersSpaces[x][y].getIcon() != null)
				{						
					checkersSpaces[x][y].setDisabledIcon(checkersSpaces[x][y].getIcon());						
					checkersSpaces[x][y].setEnabled(false);
				}
			}
		}
	}
	public void disablePlayerOneSpaces()
	{
		for (int x = 0; x < checkersSpaces.length; x++) {
			for (int y = 0; y < checkersSpaces.length; y++) {
				if(checkersSpaces[x][y].getIcon() != null && checkersSpaces[x][y].getIcon().equals(playerOneCheckerPiece))
				{
					checkersSpaces[x][y].setDisabledIcon(playerOneCheckerPiece);
					checkersSpaces[x][y].setEnabled(false);
				}
				else if(checkersSpaces[x][y].getIcon() != null && checkersSpaces[x][y].getIcon().equals(playerOneKingCheckerPiece))
				{
					checkersSpaces[x][y].setDisabledIcon(playerOneKingCheckerPiece);
					checkersSpaces[x][y].setEnabled(false);
				}
			}
		}
	}
	public void disablePlayerTwoSpaces()
	{
		for (int x = 0; x < checkersSpaces.length; x++) {
			for (int y = 0; y < checkersSpaces.length; y++) {
				if(checkersSpaces[x][y].getIcon() != null && checkersSpaces[x][y].getIcon().equals(playerTwoCheckerPiece))
				{
					checkersSpaces[x][y].setDisabledIcon(playerTwoCheckerPiece);
					checkersSpaces[x][y].setEnabled(false);
				}
				else if(checkersSpaces[x][y].getIcon() != null && checkersSpaces[x][y].getIcon().equals(playerTwoKingCheckerPiece))
				{
					checkersSpaces[x][y].setDisabledIcon(playerTwoKingCheckerPiece);
					checkersSpaces[x][y].setEnabled(false);
				}
			}
		}
	}
	public void enableOccupiedSpaces()
	{
		for (int x = 0; x < checkersSpaces.length; x++) {
			for (int y = 0; y < checkersSpaces.length; y++) {
				if(checkersSpaces[x][y].getIcon() != null)
				{
					checkersSpaces[x][y].setEnabled(true);
				}
			}
		}
	}
	public void enablePlayerOneSpaces()
	{
		for (int x = 0; x < checkersSpaces.length; x++) {
			for (int y = 0; y < checkersSpaces.length; y++) {
				if(checkersSpaces[x][y].getIcon() != null && checkersSpaces[x][y].getIcon().equals(playerOneCheckerPiece))
				{
					checkersSpaces[x][y].setEnabled(true);
				}
				else if(checkersSpaces[x][y].getIcon() != null && checkersSpaces[x][y].getIcon().equals(playerOneKingCheckerPiece))
				{					
					checkersSpaces[x][y].setEnabled(true);
				}
			}
		}
	}
	public void enablePlayerTwoSpaces()
	{
		for (int x = 0; x < checkersSpaces.length; x++) {
			for (int y = 0; y < checkersSpaces.length; y++) {
				if(checkersSpaces[x][y].getIcon() != null && checkersSpaces[x][y].getIcon().equals(playerTwoCheckerPiece))
				{
					checkersSpaces[x][y].setEnabled(true);
				}
				else if(checkersSpaces[x][y].getIcon() != null && checkersSpaces[x][y].getIcon().equals(playerTwoKingCheckerPiece))
				{					
					checkersSpaces[x][y].setEnabled(true);
				}
			}
		}
	}
	public void clearBoard()
	{		
		for (int x = 0; x < checkersSpaces.length; x++) {
			for (int y = 0; y < checkersSpaces.length; y++) {				
					checkersPanel.remove(checkersSpaces[x][y]);								
			}
		}
	}
	
	public static void main(String[] args) {		
		new Checkers();
	}

}

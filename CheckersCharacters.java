package schoolWork;

import java.awt.event.*;
import java.awt.datatransfer.*;
import java.awt.*;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

public class CheckersCharacters {
	JFrame  chooseCharacterFrame;
	JPanel chooseCharacterPanel;
	ImageIcon playerCheckerPiece, playerMoveReadyIcon, playerKingCheckerPiece, playerKingMoveReadyIcon;	
	ImageIcon redCheckerPiece, blueCheckerPiece, samusIcon, gokuIcon, narutoIcon, masterChiefIcon;
	JButton sourceButton, eventButton;
	JButton chooseCharacterOkButton;
	JButton redCheckerPieceCharacterButton, blueCheckerPieceCharacterButton, samusCharacterButton, gokuCharacterButton,
	narutoCharacterButton, masterChiefCharacterButton;
	int charactersChosen = 0;
	
	public CheckersCharacters()
	{
		chooseCharacterFrame = new JFrame();
		chooseCharacterPanel = new JPanel();
		chooseCharacterFrame.add(chooseCharacterPanel);
		chooseCharacterOkButton = new JButton("Ok");		
		chooseCharacterOkButton.setEnabled(false);
		redCheckerPiece = new ImageIcon("C:\\Users\\tbeardjr\\workspace\\Java\\src\\schoolWork\\red checker piece.jpg");		
		blueCheckerPiece = new ImageIcon("C:\\Users\\tbeardjr\\workspace\\Java\\src\\schoolWork\\blue checker piece.png");
		samusIcon  = new ImageIcon("C:\\Users\\tbeardjr\\workspace\\Java\\src\\schoolWork\\samus.png");
		gokuIcon  = new ImageIcon("C:\\Users\\tbeardjr\\workspace\\Java\\src\\schoolWork\\Goku.gif");
		narutoIcon  = new ImageIcon("C:\\Users\\tbeardjr\\workspace\\Java\\src\\schoolWork\\naruto.gif");
		masterChiefIcon  = new ImageIcon("C:\\Users\\tbeardjr\\workspace\\Java\\src\\schoolWork\\Master_Chief_walking.gif");
		redCheckerPieceCharacterButton = new JButton("Red Checker Piece", redCheckerPiece);	
		blueCheckerPieceCharacterButton = new JButton("Blue Checker Piece", blueCheckerPiece);
		samusCharacterButton = new JButton("Samus", samusIcon);
		gokuCharacterButton = new JButton("Goku", gokuIcon);
		narutoCharacterButton = new JButton("Naruto", narutoIcon);
		masterChiefCharacterButton = new JButton("Master Chief", masterChiefIcon);
		chooseCharacterPanel.add(redCheckerPieceCharacterButton);
		chooseCharacterPanel.add(blueCheckerPieceCharacterButton);	
		chooseCharacterPanel.add(samusCharacterButton);
		chooseCharacterPanel.add(gokuCharacterButton);
		chooseCharacterPanel.add(narutoCharacterButton);
		chooseCharacterPanel.add(masterChiefCharacterButton);
		sourceButton = redCheckerPieceCharacterButton;
		redCheckerPieceCharacterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCharacterRedPiece();	
				chooseCharacterOkButton.setEnabled(true);				
				sourceButton.setEnabled(true);
				sourceButton = (JButton)e.getSource();				
				redCheckerPieceCharacterButton.setEnabled(false);
			}
		});
		blueCheckerPieceCharacterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCharacterBluePiece();	
				chooseCharacterOkButton.setEnabled(true);				
				sourceButton.setEnabled(true);
				sourceButton = (JButton)e.getSource();				
				blueCheckerPieceCharacterButton.setEnabled(false);
			}
		});
		samusCharacterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCharacterSamus();	
				chooseCharacterOkButton.setEnabled(true);				
				sourceButton.setEnabled(true);
				sourceButton = (JButton)e.getSource();				
				samusCharacterButton.setEnabled(false);
			}
		});
		gokuCharacterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCharacterGoku();	
				chooseCharacterOkButton.setEnabled(true);				
				sourceButton.setEnabled(true);
				sourceButton = (JButton)e.getSource();				
				gokuCharacterButton.setEnabled(false);
			}
		});
		narutoCharacterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCharacterNaruto();	
				chooseCharacterOkButton.setEnabled(true);				
				sourceButton.setEnabled(true);
				sourceButton = (JButton)e.getSource();				
				narutoCharacterButton.setEnabled(false);
			}
		});
		masterChiefCharacterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCharacterMasterChief();	
				chooseCharacterOkButton.setEnabled(true);				
				sourceButton.setEnabled(true);
				sourceButton = (JButton)e.getSource();				
				masterChiefCharacterButton.setEnabled(false);
			}
		});
		chooseCharacterPanel.setLayout(new GridLayout(2, 5, 0, 0));		
		chooseCharacterFrame.setLocation(350, 30);
		chooseCharacterFrame.setSize(700, 700);
		chooseCharacterFrame.setVisible(false);
	}
	
	public void setCharacterRedPiece()
	{
		playerCheckerPiece = new ImageIcon("C:\\Users\\tbeardjr\\workspace\\Java\\src\\schoolWork\\red checker piece.jpg");				
		playerMoveReadyIcon = new ImageIcon("C:\\Users\\tbeardjr\\workspace\\Java\\src\\schoolWork\\red circle sparkles.gif");		
		playerKingCheckerPiece = new ImageIcon("C:\\Users\\tbeardjr\\workspace\\Java\\src\\schoolWork\\Red_King.png");
		playerKingMoveReadyIcon = new ImageIcon("C:\\Users\\tbeardjr\\workspace\\Java\\src\\schoolWork\\spinning crown.gif");		
	}
	public void setCharacterBluePiece()
	{				
		playerCheckerPiece = new ImageIcon("C:\\Users\\tbeardjr\\workspace\\Java\\src\\schoolWork\\blue checker piece.png");		
		playerMoveReadyIcon = new ImageIcon("C:\\Users\\tbeardjr\\workspace\\Java\\src\\schoolWork\\blue-circle glitter.gif");	
		playerKingCheckerPiece = new ImageIcon("C:\\Users\\tbeardjr\\workspace\\Java\\src\\schoolWork\\Blue_King.png");
		playerKingMoveReadyIcon = new ImageIcon("C:\\Users\\tbeardjr\\workspace\\Java\\src\\schoolWork\\flashing-blue crown.gif");
	}
	public void setCharacterSamus()
	{				
		playerCheckerPiece = new ImageIcon("C:\\Users\\tbeardjr\\workspace\\Java\\src\\schoolWork\\samus.png");		
		playerMoveReadyIcon = new ImageIcon("C:\\Users\\tbeardjr\\workspace\\Java\\src\\schoolWork\\Samus_ScrewAttack.gif");	
		playerKingCheckerPiece = new ImageIcon("C:\\Users\\tbeardjr\\workspace\\Java\\src\\schoolWork\\dark_samus.gif");
		playerKingMoveReadyIcon = new ImageIcon("C:\\Users\\tbeardjr\\workspace\\Java\\src\\schoolWork\\dark_samus_pulsing.gif");
	}
	public void setCharacterGoku()
	{				
		playerCheckerPiece = new ImageIcon("C:\\Users\\tbeardjr\\workspace\\Java\\src\\schoolWork\\Goku.gif");		
		playerMoveReadyIcon = new ImageIcon("C:\\Users\\tbeardjr\\workspace\\Java\\src\\schoolWork\\goku kaioken.gif");	
		playerKingCheckerPiece = new ImageIcon("C:\\Users\\tbeardjr\\workspace\\Java\\src\\schoolWork\\super saiyan goku.gif");
		playerKingMoveReadyIcon = new ImageIcon("C:\\Users\\tbeardjr\\workspace\\Java\\src\\schoolWork\\goku super sayian.gif");
	}
	public void setCharacterNaruto()
	{				
		playerCheckerPiece = new ImageIcon("C:\\Users\\tbeardjr\\workspace\\Java\\src\\schoolWork\\naruto.gif");		
		playerMoveReadyIcon = new ImageIcon("C:\\Users\\tbeardjr\\workspace\\Java\\src\\schoolWork\\1 tail naruto running.gif");	
		playerKingCheckerPiece = new ImageIcon("C:\\Users\\tbeardjr\\workspace\\Java\\src\\schoolWork\\naruto 3 tails.gif");
		playerKingMoveReadyIcon = new ImageIcon("C:\\Users\\tbeardjr\\workspace\\Java\\src\\schoolWork\\4 tailed naruto.gif");
	}
	public void setCharacterMasterChief()
	{				
		playerCheckerPiece = new ImageIcon("C:\\Users\\tbeardjr\\workspace\\Java\\src\\schoolWork\\Master_Chief_walking.gif");		
		playerMoveReadyIcon = new ImageIcon("C:\\Users\\tbeardjr\\workspace\\Java\\src\\schoolWork\\Master_chief_shooting_shotgun.gif");	
		playerKingCheckerPiece = new ImageIcon("C:\\Users\\tbeardjr\\workspace\\Java\\src\\schoolWork\\Master Chief and arbitor.gif");
		playerKingMoveReadyIcon = new ImageIcon("C:\\Users\\tbeardjr\\workspace\\Java\\src\\schoolWork\\master Chief spartan lazer.gif");
	}
}

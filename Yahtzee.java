package schoolWork;

import java.awt.event.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;
import javax.swing.UIManager.*;

public class Yahtzee {

	public JFrame yahtzeeFrame, scoreChoiceFrame, yahtzeeTitleScreenFrame,
			gamerTagFrame, numberOfPlayersFrame, howToPlayFrame;
	public JPanel yahtzeePanel, scoreChoicePanel, checkBoxPanel,
			yahtzeeTitleScreenPanel, gamerTagPanel, numberOfPlayersPanel,
			howToPlayPanel;
	public JButton rollAllDiceButton, reRollDiceButton, scoreButton,
			singlePlayerButton, multiplayerButton, howToPlayButton, okButton,
			cancelButton, doneButton;
	public JButton acesButton, twosButton, threesButton, foursButton,
			fivesButton, sixesButton, threeOfAKindButton, fourOfAKindButton,
			fullHouseButton, smallStraightButton, largeStraightButton,
			yahtzeeButton, chanceButton;
	public JButton[] scoreButtons = new JButton[13];
	public JLabel[] faceLabels = new JLabel[5];
	public JLabel[] animatedDiLabels = new JLabel[5];
	public JCheckBox diceOne, diceTwo, diceThree, diceFour, diceFive;
	public JCheckBox[] diceCheckBoxes = { diceOne, diceTwo, diceThree,
			diceFour, diceFive };
	public ImageIcon[] faceImages = new ImageIcon[6];
	public ImageIcon[] animatedDiImages = new ImageIcon[5];
	public ImageIcon yahtzeeTitleImage, yahtzeeMiniTitleImage,
			rotatingRedBlueDiceImage, howToPlayYahtzeeImage;
	public JLabel yahtzeeTitleLabel, yahtzeeMiniTitleLabel,
			rotatingRedBlueDiceLabel, playerOneGamerTagPrompt,
			playerTwoGamerTagPrompt, playerThreeGamerTagPrompt,
			playerFourGamerTagPrompt, welcomePrompt, numberOfPlayersPrompt;
	public JTextField playerOneGamerTagField, playerTwoGamerTagField,
			playerThreeGamerTagField, playerFourGamerTagField;
	public JTextArea howToPlayYahtzeeTextArea;
	public JComboBox numberOFPlayersComboBox;
	String[] numberOfPlayers = { "Two", "Three", "Four" };
	JLabel[] playerGamerTagPrompts = { playerOneGamerTagPrompt,
			playerTwoGamerTagPrompt, playerThreeGamerTagPrompt,
			playerFourGamerTagPrompt };
	JTextField[] playerGamerTagFields = { playerOneGamerTagField,
			playerTwoGamerTagField, playerThreeGamerTagField,
			playerFourGamerTagField };
	public int[] gamertagFrameHeights = { 255, 290, 340 };
	public int[] numberOfRows = { 8, 10, 12 };
	public String[] gamerTags;
	public ArrayList<ScoreSheet> scoreSheets;
	public ArrayList<Scores> playerScores;
	int rollCounter = 0;
	int faceNumber = 0;
	public Scores scores = null;
	ScoreSheet scoreSheet = null;
	int scoreSheetColumnPosition = 2;
	int scoreSheetRowPosition = 0;
	int singlePlayerRound = 12;
	public int multiplayerRound = 12;
	int gameNumber = 1;
	public boolean isButtonClicked = false;
	public boolean isSinglePlayer;
	public int currentPlayerTurn = 0;
	public int previousPlayerTurn = 0;
	public String firstPlace = "";
	public String secondPlace = "";
	public String thirdPlace = "";
	public String fourthPlace = "";
	public int firstPlacePoints, secondPlacePoints, thirdPlacePoints,
			fourthPlacePoints;
	public String howToRules = "";

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

	public Yahtzee() {
		try {
			// Open the file that is the first
			// command line parameter
			FileInputStream fstream = new FileInputStream(
					"C:\\Users\\tbeardjr\\Documents\\SchoolWork\\src\\resources\\RulesOfYahtzee.txt");
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				// Print the content on the console

				howToRules = howToRules + strLine + "\n";

			}
			// Close the input stream
			in.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}

		try {
			Thread.sleep(0000);
		} catch (InterruptedException e) {
		}

		// Yahtzee Title Screen
		yahtzeeTitleScreenFrame = new JFrame("Title Screen");
		yahtzeeTitleScreenFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		yahtzeeTitleScreenFrame.setSize(805, 625);
		Font yahtzeeTitleLabelFont = new Font("serif", Font.HANGING_BASELINE,
				72);
		yahtzeeTitleScreenFrame.setVisible(true);
		yahtzeeTitleScreenPanel = new JPanel();
		yahtzeeTitleScreenPanel.setLayout(new GridLayout(4, 1, 0, 0));
		yahtzeeTitleScreenPanel.setBackground(Color.yellow);
		yahtzeeTitleScreenFrame.add(yahtzeeTitleScreenPanel);
		yahtzeeTitleImage = new ImageIcon("Yahtzee_Title.jpg");
		yahtzeeTitleLabel = new JLabel("YAHTZEE!", JLabel.CENTER);
		yahtzeeTitleLabel.setFont(yahtzeeTitleLabelFont);
		yahtzeeTitleLabel.setBackground(Color.black);
		yahtzeeTitleLabel.setForeground(Color.yellow);
		yahtzeeTitleLabel.setOpaque(true);

		yahtzeeTitleScreenPanel.add(yahtzeeTitleLabel);
		singlePlayerButton = new JButton("Single Player");
		multiplayerButton = new JButton("Multiplayer Player");
		howToPlayButton = new JButton("How To Play");
		Font font = new Font("serif", Font.PLAIN, 32);
		singlePlayerButton.setFont(font);
		singlePlayerButton.setBackground(Color.black);
		singlePlayerButton.setForeground(Color.yellow);
		multiplayerButton.setFont(font);
		multiplayerButton.setBackground(Color.black);
		multiplayerButton.setForeground(Color.yellow);
		howToPlayButton.setFont(font);
		howToPlayButton.setBackground(Color.black);
		howToPlayButton.setForeground(Color.yellow);
		yahtzeeTitleScreenPanel.add(singlePlayerButton);
		yahtzeeTitleScreenPanel.add(multiplayerButton);
		yahtzeeTitleScreenPanel.add(howToPlayButton);
		yahtzeeTitleScreenPanel.revalidate();
		yahtzeeTitleScreenPanel.repaint();

		// Yahtzee frame
		yahtzeeFrame = new JFrame("Yahtzee");
		yahtzeeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		yahtzeeFrame.setSize(350, 400);
		yahtzeeFrame.setVisible(false);
		yahtzeePanel = new JPanel();
		yahtzeePanel.setBackground(Color.black);
		yahtzeePanel.setLayout(new FlowLayout());
		checkBoxPanel = new JPanel();
		checkBoxPanel.setBackground(Color.black);
		checkBoxPanel.setLayout(new FlowLayout());
		yahtzeeFrame.add(checkBoxPanel, BorderLayout.SOUTH);
		yahtzeeFrame.add(yahtzeePanel, BorderLayout.CENTER);
		rollAllDiceButton = new JButton("Roll All Dice");
		reRollDiceButton = new JButton("Re-Roll Dice");
		scoreButton = new JButton("Score");
		rollAllDiceButton.setBackground(Color.black);
		rollAllDiceButton.setForeground(Color.yellow);
		reRollDiceButton.setBackground(Color.black);
		reRollDiceButton.setForeground(Color.yellow);
		scoreButton.setBackground(Color.black);
		scoreButton.setForeground(Color.yellow);
		yahtzeeMiniTitleImage = new ImageIcon("yahtzee mini title.gif");
		rotatingRedBlueDiceImage = new ImageIcon("RotatingRedBlueDice.gif");
		yahtzeeMiniTitleLabel = new JLabel(yahtzeeMiniTitleImage, JLabel.CENTER);
		rotatingRedBlueDiceLabel = new JLabel(rotatingRedBlueDiceImage,
				JLabel.LEFT);
		// yahtzeePanel.add(rotatingRedBlueDiceLabel);
		yahtzeePanel.add(yahtzeeMiniTitleLabel);
		yahtzeePanel.add(rollAllDiceButton);
		yahtzeePanel.add(Box.createRigidArea(new Dimension(10, 5)));
		yahtzeePanel.add(reRollDiceButton);
		yahtzeePanel.add(Box.createRigidArea(new Dimension(10, 5)));
		yahtzeePanel.add(scoreButton);
		// create animated dice
		for (int x = 0; x < animatedDiImages.length; x++) {
			animatedDiImages[x] = new ImageIcon("animatedDice.gif");
			animatedDiLabels[x] = new JLabel(animatedDiImages[x], JLabel.CENTER);
			yahtzeePanel.add(animatedDiLabels[x]);
		}
		reRollDiceButton.setEnabled(false);
		scoreButton.setEnabled(false);
		// create dice images
		for (int x = 0; x < faceImages.length; x++) {
			faceImages[x] = new ImageIcon("face_" + (x + 1) + ".jpg");
			faceImages[x].setDescription(String.valueOf(x));
		}

		// Main Menu ActionListeners
		singlePlayerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isSinglePlayer = true;
				gamerTagFrame = new JFrame("Enter GamerTag");
				gamerTagFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				gamerTagFrame.setSize(290, 270);
				gamerTagFrame.setLocation(900, 400);
				gamerTagFrame.setVisible(true);
				gamerTagPanel = new JPanel();
				gamerTagFrame.add(gamerTagPanel);
				gamerTagFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				welcomePrompt = new JLabel("Welcome to Yahtzee!");
				playerOneGamerTagPrompt = new JLabel("Enter your GamerTag ");
				playerOneGamerTagField = new JTextField("", 15);
				playerOneGamerTagField.addKeyListener(new KeyAdapter() {
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
							okButton.setEnabled(false);
							JOptionPane
									.showMessageDialog(
											null,
											"Gamertag must be between 1 and 15 characters.",
											"Validation Error",
											JOptionPane.ERROR_MESSAGE);
						} else {
							okButton.setEnabled(true);
						}

						// Need to make sure only letters and numbers are
						// allowed
						if (keyCode == ENTER_KEY) {
							okButton.doClick();
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

				playerOneGamerTagField.setText("Player 1");
				gamerTagPanel.setLayout(new GridLayout(7, 5, 0, 0));
				gamerTagPanel.add(welcomePrompt);
				welcomePrompt.setLocation(150, 0);
				gamerTagPanel.add(new JLabel());
				gamerTagPanel.add(playerOneGamerTagPrompt);
				gamerTagPanel.add(playerOneGamerTagField);
				gamerTagPanel.add(new JLabel());
				okButton = new JButton("Ok");
				// localGamerTagPanel.add(new JLabel());
				gamerTagPanel.add(okButton);
				okButton.setActionCommand("Ok");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						gamerTagFrame.setVisible(false);
						yahtzeeTitleScreenFrame.setVisible(false);
						ScoreSheet.finishButton.setEnabled(false);
						scoreSheet.scoreSheetFrame.setAlwaysOnTop(false);
						scoreSheet.buttonPanel
								.remove(scoreSheet.nextPlayersTurnButton);
						yahtzeeFrame.setVisible(true);

					}
				});

				cancelButton = new JButton("Cancel");
				gamerTagPanel.add(cancelButton);
				cancelButton.setActionCommand("cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						gamerTagFrame.setVisible(false);
						yahtzeeTitleScreenFrame.setVisible(true);
					}
				});

				playerOneGamerTagField.addActionListener(this);

			}
		});
		multiplayerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isSinglePlayer = false;
				numberOfPlayersPrompt = new JLabel("Choose number of players.");
				numberOFPlayersComboBox = new JComboBox(numberOfPlayers);
				gamerTagFrame = new JFrame("Enter GamerTag");
				gamerTagFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				gamerTagFrame.setSize(290, 340);
				gamerTagFrame.setLocation(900, 400);
				gamerTagFrame.setVisible(true);
				gamerTagPanel = new JPanel();
				gamerTagFrame.add(gamerTagPanel);
				gamerTagPanel.add(numberOfPlayersPrompt);
				gamerTagPanel.add(numberOFPlayersComboBox);
				gamerTagFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				numberOFPlayersComboBox.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (isButtonClicked == true) {
							gamerTagPanel.removeAll();
							gamerTagPanel.add(numberOfPlayersPrompt);
							gamerTagPanel.add(numberOFPlayersComboBox);
						}
						isButtonClicked = true;

						for (int x = 0; x < numberOFPlayersComboBox
								.getSelectedIndex() + 2; x++) {

							playerGamerTagPrompts[x] = new JLabel("Player "
									+ (x + 1) + " enter your GamerTag ");
							playerGamerTagFields[x] = new JTextField("", 15);
							playerGamerTagFields[x]
									.addKeyListener(new KeyAdapter() {
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
											JTextField textField = (JTextField) e
													.getSource();

											// Capture text in the text field
											String text = textField.getText();

											// Capture each key pressed.
											int keyCode = e.getKeyCode();

											// Make sure the gamer tag is no
											// longer than 15
											// characters
											if ((text.length() > 15 || text
													.length() == 0)) {
												// Gamertag too long. Stop them
												// from
												// clicking the OK
												okButton.setEnabled(false);
												JOptionPane
														.showMessageDialog(
																null,
																"Gamertag must be between 1 and 15 characters.",
																"Validation Error",
																JOptionPane.ERROR_MESSAGE);
											} else {
												okButton.setEnabled(true);
											}

											// Need to make sure only letters
											// and numbers
											// are allowed
											if (keyCode == ENTER_KEY) {
												okButton.doClick();
											} else if (Character
													.isLetterOrDigit(keyCode) == false
													&& Character
															.isIdentifierIgnorable(keyCode) == false
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

							playerGamerTagFields[x]
									.setText("Player " + (x + 1));
							gamerTagPanel.setLayout(new GridLayout(
									numberOfRows[numberOFPlayersComboBox
											.getSelectedIndex()], 5, 0, 0));
							gamerTagPanel
									.setSize(
											290,
											gamertagFrameHeights[numberOFPlayersComboBox
													.getSelectedIndex()]);
							gamerTagPanel.add(playerGamerTagPrompts[x]);
							gamerTagPanel.add(playerGamerTagFields[x]);
							gamerTagPanel.revalidate();
							gamerTagPanel.repaint();
							playerGamerTagFields[x].addActionListener(this);
						}
						okButton = new JButton("Ok");
						gamerTagPanel.add(okButton);
						okButton.setActionCommand("Ok");
						okButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								yahtzeeTitleScreenFrame.setVisible(false);
								gamerTags = new String[numberOFPlayersComboBox
										.getSelectedIndex() + 2];
								scoreSheets = new ArrayList<ScoreSheet>();
								for (int x = 0; x < numberOFPlayersComboBox
										.getSelectedIndex() + 2; x++) {
									scoreSheets.add(new ScoreSheet());
									scoreSheets.get(x).scoreSheetFrame
											.setAlwaysOnTop(false);
									gamerTags[x] = playerGamerTagFields[x]
											.getText();
								}
								scoreSheets.get(0).nextPlayersTurnButton
										.setText(gamerTags[1] + "'s Turn");
								scoreSheets.get(0).scoreSheetFrame
										.setTitle(gamerTags[0]
												+ "'s Score Sheet");
								for (ScoreSheet scoreSheet : scoreSheets) {
									scoreSheet.nextPlayersTurnButton
											.addActionListener(new ActionListener() {
												public void actionPerformed(
														ActionEvent e) {
													if (currentPlayerTurn < numberOFPlayersComboBox
															.getSelectedIndex() + 1) {
														scoreSheets
																.get(currentPlayerTurn).nextPlayersTurnButton
																.setText(gamerTags[currentPlayerTurn + 1]
																		+ "'s Turn");
														scoreSheets
																.get(currentPlayerTurn).scoreSheetFrame
																.setTitle(gamerTags[currentPlayerTurn]
																		+ "'s Score Sheet");
													} else {
														scoreSheets
																.get(currentPlayerTurn).nextPlayersTurnButton.setText(gamerTags[currentPlayerTurn
																- (numberOFPlayersComboBox
																		.getSelectedIndex() + 1)]
																+ "'s Turn");
														scoreSheets
																.get(currentPlayerTurn).scoreSheetFrame
																.setTitle(gamerTags[currentPlayerTurn]
																		+ "'s Score Sheet");
													}
													scoreSheets
															.get(previousPlayerTurn).scoreSheetFrame
															.setVisible(false);
												}
											});
								}
								gamerTagFrame.setVisible(false);
								ScoreSheet.finishButton.setEnabled(false);
								yahtzeeFrame.setVisible(true);
							}
						});

						cancelButton = new JButton("Cancel");
						gamerTagPanel.add(cancelButton);
						cancelButton.setActionCommand("cancel");
						cancelButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								gamerTagFrame.setVisible(false);
							}
						});

					}
				});

			}
		});

		howToPlayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				howToPlayFrame = new JFrame("How To Play");
				howToPlayFrame.setSize(1000, 1000);
				howToPlayPanel = new JPanel();
				howToPlayFrame.add(howToPlayPanel);
				howToPlayFrame.setAlwaysOnTop(true);
				howToPlayYahtzeeImage = new ImageIcon("HowToPlayYahtzee1.gif");
				howToPlayYahtzeeTextArea = new JTextArea(howToRules);
				howToPlayYahtzeeTextArea.setEditable(false);
				howToPlayYahtzeeTextArea.setWrapStyleWord(true);
				JScrollPane scrollingArea = new JScrollPane(
						howToPlayYahtzeeTextArea);
				howToPlayPanel.setLayout(new BorderLayout());
				howToPlayPanel.add(scrollingArea, BorderLayout.CENTER);
				// howToPlayPanel.add(howToPlayYahtzeeTextArea);
				doneButton = new JButton("Done");
				// howToPlayPanel.add(doneButton);
				howToPlayFrame.setVisible(true);
				doneButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						howToPlayFrame.setVisible(false);
					}
				});
				howToPlayPanel.repaint();
				howToPlayPanel.revalidate();

			}
		});
		addCheckBoxes();
		scoreChoicePanel = new JPanel();
		scoreChoicePanel.setBackground(Color.black);
		scoreChoicePanel.setLayout(new GridLayout(6, 5, 0, 0));
		checkBoxPanel.add(scoreChoicePanel, BorderLayout.CENTER);
		// Add upper score buttons

		ButtonGroup scoreButtonGroup = new ButtonGroup();
		acesButton = new JButton("Aces");
		scoreButtonGroup.add(acesButton);
		scoreButtons[0] = acesButton;
		scoreChoicePanel.add(acesButton);
		acesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isSinglePlayer == true) {
					scores.scoreAces();
					scoreSheet.updateUpperScoreSheet(scoreSheetRowPosition,
							scores.acesScore, scoreSheetColumnPosition);
					scoreSheet.scoreSheetFrame.setVisible(true);
				} else {
					playerScores.get(currentPlayerTurn).scoreAces();
					scoreSheets.get(currentPlayerTurn).updateUpperScoreSheet(
							scoreSheetRowPosition,
							playerScores.get(currentPlayerTurn).acesScore,
							scoreSheetColumnPosition);
					scoreSheets.get(currentPlayerTurn).scoreSheetFrame
							.setVisible(true);
					previousPlayerTurn = currentPlayerTurn;
					currentPlayerTurn++;
				}
				acesButton.setEnabled(false);
				getFinalScore();
			}
		});
		twosButton = new JButton("Twos");
		scoreButtonGroup.add(twosButton);
		scoreButtons[1] = twosButton;
		scoreChoicePanel.add(twosButton);
		twosButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isSinglePlayer == true) {
					scores.scoreTwos();
					scoreSheet.updateUpperScoreSheet(scoreSheetRowPosition + 1,
							scores.twosScore, scoreSheetColumnPosition);
					scoreSheet.scoreSheetFrame.setVisible(true);
				} else {
					playerScores.get(currentPlayerTurn).scoreTwos();
					scoreSheets.get(currentPlayerTurn).updateUpperScoreSheet(
							scoreSheetRowPosition + 1,
							playerScores.get(currentPlayerTurn).twosScore,
							scoreSheetColumnPosition);
					scoreSheets.get(currentPlayerTurn).scoreSheetFrame
							.setVisible(true);
					previousPlayerTurn = currentPlayerTurn;
					currentPlayerTurn++;
				}

				twosButton.setEnabled(false);
				getFinalScore();

			}
		});
		threesButton = new JButton("Threes");
		scoreButtonGroup.add(threesButton);
		scoreButtons[2] = threesButton;
		scoreChoicePanel.add(threesButton);
		threesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isSinglePlayer == true) {
					scores.scoreThrees();
					scoreSheet.updateUpperScoreSheet(scoreSheetRowPosition + 2,
							scores.threesScore, scoreSheetColumnPosition);
					scoreSheet.scoreSheetFrame.setVisible(true);
				} else {
					playerScores.get(currentPlayerTurn).scoreThrees();
					scoreSheets.get(currentPlayerTurn).updateUpperScoreSheet(
							scoreSheetRowPosition + 2,
							playerScores.get(currentPlayerTurn).threesScore,
							scoreSheetColumnPosition);
					scoreSheets.get(currentPlayerTurn).scoreSheetFrame
							.setVisible(true);
					previousPlayerTurn = currentPlayerTurn;
					currentPlayerTurn++;
				}
				threesButton.setEnabled(false);
				getFinalScore();
			}
		});
		foursButton = new JButton("Fours");
		scoreButtonGroup.add(foursButton);
		scoreButtons[3] = foursButton;
		scoreChoicePanel.add(foursButton);
		foursButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isSinglePlayer == true) {
					scores.scoreFours();
					scoreSheet.updateUpperScoreSheet(scoreSheetRowPosition + 3,
							scores.foursScore, scoreSheetColumnPosition);
					scoreSheet.scoreSheetFrame.setVisible(true);
				} else {
					playerScores.get(currentPlayerTurn).scoreFours();
					scoreSheets.get(currentPlayerTurn).updateUpperScoreSheet(
							scoreSheetRowPosition + 3,
							playerScores.get(currentPlayerTurn).foursScore,
							scoreSheetColumnPosition);
					scoreSheets.get(currentPlayerTurn).scoreSheetFrame
							.setVisible(true);
					previousPlayerTurn = currentPlayerTurn;
					currentPlayerTurn++;
				}
				foursButton.setEnabled(false);
				getFinalScore();
			}
		});
		fivesButton = new JButton("Fives");
		scoreButtonGroup.add(fivesButton);
		scoreButtons[4] = fivesButton;
		scoreChoicePanel.add(fivesButton);
		fivesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isSinglePlayer == true) {
					scores.scoreFives();
					scoreSheet.updateUpperScoreSheet(scoreSheetRowPosition + 4,
							scores.fivesScore, scoreSheetColumnPosition);
					scoreSheet.scoreSheetFrame.setVisible(true);
				} else {
					playerScores.get(currentPlayerTurn).scoreFives();
					scoreSheets.get(currentPlayerTurn).updateUpperScoreSheet(
							scoreSheetRowPosition + 4,
							playerScores.get(currentPlayerTurn).fivesScore,
							scoreSheetColumnPosition);
					scoreSheets.get(currentPlayerTurn).scoreSheetFrame
							.setVisible(true);
					previousPlayerTurn = currentPlayerTurn;
					currentPlayerTurn++;
				}
				fivesButton.setEnabled(false);
				getFinalScore();
			}
		});
		sixesButton = new JButton("Sixes");
		scoreButtonGroup.add(sixesButton);
		scoreButtons[5] = sixesButton;
		scoreChoicePanel.add(sixesButton);
		sixesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isSinglePlayer == true) {
					scores.scoreSixes();
					scoreSheet.updateUpperScoreSheet(scoreSheetRowPosition + 5,
							scores.sixesScore, scoreSheetColumnPosition);
					scoreSheet.scoreSheetFrame.setVisible(true);
				} else {
					playerScores.get(currentPlayerTurn).scoreSixes();
					scoreSheets.get(currentPlayerTurn).updateUpperScoreSheet(
							scoreSheetRowPosition + 5,
							playerScores.get(currentPlayerTurn).sixesScore,
							scoreSheetColumnPosition);
					scoreSheets.get(currentPlayerTurn).scoreSheetFrame
							.setVisible(true);
					previousPlayerTurn = currentPlayerTurn;
					currentPlayerTurn++;
				}
				sixesButton.setEnabled(false);
				getFinalScore();

			}
		});

		// add Lower Score radio buttons
		threeOfAKindButton = new JButton("Three Of A Kind");
		scoreButtonGroup.add(threeOfAKindButton);
		scoreButtons[6] = threeOfAKindButton;
		scoreChoicePanel.add(threeOfAKindButton);
		threeOfAKindButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isSinglePlayer == true) {
					scores.scoreThreeOfAKind();
					scoreSheet.updateLowerScoreSheet(scoreSheetRowPosition,
							scores.threeOfAKindScore, scoreSheetColumnPosition);
					scoreSheet.scoreSheetFrame.setVisible(true);
				} else {
					playerScores.get(currentPlayerTurn).scoreThreeOfAKind();
					scoreSheets
							.get(currentPlayerTurn)
							.updateLowerScoreSheet(
									scoreSheetRowPosition,
									playerScores.get(currentPlayerTurn).threeOfAKindScore,
									scoreSheetColumnPosition);
					scoreSheets.get(currentPlayerTurn).scoreSheetFrame
							.setVisible(true);
					previousPlayerTurn = currentPlayerTurn;
					currentPlayerTurn++;
				}

				threeOfAKindButton.setEnabled(false);
				getFinalScore();

			}
		});

		fourOfAKindButton = new JButton("Four Of A Kind");
		scoreButtonGroup.add(fourOfAKindButton);
		scoreButtons[7] = fourOfAKindButton;
		scoreChoicePanel.add(fourOfAKindButton);
		fourOfAKindButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isSinglePlayer == true) {
					scores.scoreFourOfAKind();
					scoreSheet.updateLowerScoreSheet(scoreSheetRowPosition + 1,
							scores.fourOfAKindScore, scoreSheetColumnPosition);
					scoreSheet.scoreSheetFrame.setVisible(true);
				} else {
					playerScores.get(currentPlayerTurn).scoreFourOfAKind();
					scoreSheets
							.get(currentPlayerTurn)
							.updateLowerScoreSheet(
									scoreSheetRowPosition + 1,
									playerScores.get(currentPlayerTurn).fourOfAKindScore,
									scoreSheetColumnPosition);
					scoreSheets.get(currentPlayerTurn).scoreSheetFrame
							.setVisible(true);
					previousPlayerTurn = currentPlayerTurn;
					currentPlayerTurn++;
				}
				fourOfAKindButton.setEnabled(false);
				getFinalScore();

			}
		});

		fullHouseButton = new JButton("Full House");
		scoreButtonGroup.add(fullHouseButton);
		scoreButtons[8] = fullHouseButton;
		scoreChoicePanel.add(fullHouseButton);
		fullHouseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isSinglePlayer == true) {
					scores.scoreFullHouse();
					scoreSheet.updateLowerScoreSheet(scoreSheetRowPosition + 2,
							scores.fullHouseScore, scoreSheetColumnPosition);
					scoreSheet.scoreSheetFrame.setVisible(true);
				} else {
					playerScores.get(currentPlayerTurn).scoreFullHouse();
					scoreSheets.get(currentPlayerTurn).updateLowerScoreSheet(
							scoreSheetRowPosition + 2,
							playerScores.get(currentPlayerTurn).fullHouseScore,
							scoreSheetColumnPosition);
					scoreSheets.get(currentPlayerTurn).scoreSheetFrame
							.setVisible(true);
					previousPlayerTurn = currentPlayerTurn;
					currentPlayerTurn++;
				}

				fullHouseButton.setEnabled(false);
				getFinalScore();

			}
		});

		smallStraightButton = new JButton("Small Straight");
		scoreButtonGroup.add(smallStraightButton);
		scoreButtons[9] = smallStraightButton;
		scoreChoicePanel.add(smallStraightButton);
		smallStraightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isSinglePlayer == true) {
					scores.scoreSmallStraight();
					scoreSheet
							.updateLowerScoreSheet(scoreSheetRowPosition + 3,
									scores.smallStraightScore,
									scoreSheetColumnPosition);
					scoreSheet.scoreSheetFrame.setVisible(true);
				} else {
					playerScores.get(currentPlayerTurn).scoreSmallStraight();
					scoreSheets
							.get(currentPlayerTurn)
							.updateLowerScoreSheet(
									scoreSheetRowPosition + 3,
									playerScores.get(currentPlayerTurn).smallStraightScore,
									scoreSheetColumnPosition);
					scoreSheets.get(currentPlayerTurn).scoreSheetFrame
							.setVisible(true);
					previousPlayerTurn = currentPlayerTurn;
					currentPlayerTurn++;
				}

				smallStraightButton.setEnabled(false);
				getFinalScore();

			}
		});

		largeStraightButton = new JButton("Large Straight");
		scoreButtonGroup.add(largeStraightButton);
		scoreButtons[10] = largeStraightButton;
		scoreChoicePanel.add(largeStraightButton);
		largeStraightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isSinglePlayer == true) {
					scores.scoreLargeStraight();
					scoreSheet
							.updateLowerScoreSheet(scoreSheetRowPosition + 4,
									scores.largeStraightScore,
									scoreSheetColumnPosition);
					scoreSheet.scoreSheetFrame.setVisible(true);
				} else {
					playerScores.get(currentPlayerTurn).scoreLargeStraight();
					scoreSheets
							.get(currentPlayerTurn)
							.updateLowerScoreSheet(
									scoreSheetRowPosition + 4,
									playerScores.get(currentPlayerTurn).largeStraightScore,
									scoreSheetColumnPosition);
					scoreSheets.get(currentPlayerTurn).scoreSheetFrame
							.setVisible(true);
					previousPlayerTurn = currentPlayerTurn;
					currentPlayerTurn++;
				}
				largeStraightButton.setEnabled(false);
				getFinalScore();

			}
		});

		chanceButton = new JButton("Chance");
		scoreButtonGroup.add(chanceButton);
		scoreButtons[12] = chanceButton;
		scoreChoicePanel.add(chanceButton);
		chanceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isSinglePlayer == true) {
					scores.scoreChance();
					scoreSheet.updateLowerScoreSheet(scoreSheetRowPosition + 6,
							scores.chanceScore, scoreSheetColumnPosition);
					scoreSheet.scoreSheetFrame.setVisible(true);
				} else {
					playerScores.get(currentPlayerTurn).scoreChance();
					scoreSheets.get(currentPlayerTurn).updateLowerScoreSheet(
							scoreSheetRowPosition + 6,
							playerScores.get(currentPlayerTurn).chanceScore,
							scoreSheetColumnPosition);
					scoreSheets.get(currentPlayerTurn).scoreSheetFrame
							.setVisible(true);
					previousPlayerTurn = currentPlayerTurn;
					currentPlayerTurn++;
				}
				chanceButton.setEnabled(false);
				getFinalScore();
			}
		});

		yahtzeeButton = new JButton("Yahtzee!");
		scoreButtonGroup.add(yahtzeeButton);
		scoreButtons[11] = yahtzeeButton;
		scoreChoicePanel.add(new JLabel());
		scoreChoicePanel.add(yahtzeeButton);
		yahtzeeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isSinglePlayer == true) {
					scores.scoreYahtzee();
					scoreSheet.updateLowerScoreSheet(scoreSheetRowPosition + 5,
							scores.yahtzeeScore, scoreSheetColumnPosition);
					scoreSheet.scoreSheetFrame.setVisible(true);
				} else {
					playerScores.get(currentPlayerTurn).scoreYahtzee();
					scoreSheets.get(currentPlayerTurn).updateLowerScoreSheet(
							scoreSheetRowPosition + 5,
							playerScores.get(currentPlayerTurn).yahtzeeScore,
							scoreSheetColumnPosition);
					scoreSheets.get(currentPlayerTurn).scoreSheetFrame
							.setVisible(true);
					previousPlayerTurn = currentPlayerTurn;
					currentPlayerTurn++;
				}
				yahtzeeButton.setEnabled(false);
				getFinalScore();

			}
		});
		for (int x = 0; x < scoreButtons.length; x++) {
			scoreButtons[x].setBackground(Color.black);
			scoreButtons[x].setForeground(Color.yellow);
		}
		disableScoreButtons();

		rollAllDiceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeAnimatedDice();
				rollDice();
				rollCounter++;
				rollAllDiceButton.setEnabled(false);
				reRollDiceButton.setEnabled(true);
				scoreButton.setEnabled(true);
				enableCheckBoxes();
				yahtzeePanel.revalidate();
				yahtzeePanel.repaint();

			}

		});

		reRollDiceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeDice();
				addAnimatedDice();
				removeAnimatedDice();
				reRollSelectedDice();
				rollCounter++;
				if (rollCounter == 3) {
					reRollDiceButton.setEnabled(false);
					disableCheckBoxes();
				}

				yahtzeePanel.revalidate();
				yahtzeePanel.repaint();

			}

		});
		scoreButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rollCounter = 0;
				rollAllDiceButton.setEnabled(true);
				disableButtons();
				disableCheckBoxes();
				enableUnusedScoreButtons();
				yahtzeePanel.revalidate();
				yahtzeePanel.repaint();
				// TODO Start next game
			}

		});
		playerScores = new ArrayList<Scores>();
		for (int x = 0; x < 4; x++) {
			playerScores.add(new Scores(this));

		}
		scores = new Scores(this);
		scoreSheet = new ScoreSheet();
		scoreSheet.scoreSheetFrame.setAlwaysOnTop(false);

	}

	public void rollDice() {
		for (int x = 0; x < 5; x++) {
			Random diceRoll = new Random();
			faceNumber = (diceRoll.nextInt(6));
			faceLabels[x] = new JLabel(faceImages[faceNumber], JLabel.CENTER);
			faceLabels[x].setName(faceImages[faceNumber].getDescription());
			yahtzeePanel.add(faceLabels[x]);
			System.out.println(faceNumber);

		}
	}

	public void removeAnimatedDice() {
		for (int x = 0; x < animatedDiImages.length; x++) {
			yahtzeePanel.remove(animatedDiLabels[x]);
		}
	}

	public void addAnimatedDice() {
		for (int x = 0; x < animatedDiImages.length; x++) {
			yahtzeePanel.add(animatedDiLabels[x]);
		}
	}

	public void reRollSelectedDice() {
		for (int x = 0; x < faceLabels.length; x++) {
			Random diceRoll = new Random();
			if (diceCheckBoxes[x].isSelected() == true) {
				faceNumber = (diceRoll.nextInt(6));
				faceLabels[x] = new JLabel(faceImages[faceNumber],
						JLabel.CENTER);
				faceLabels[x].setName(faceImages[faceNumber].getDescription());
				yahtzeePanel.add(faceLabels[x]);
				System.out.println("Dice " + (x + 1) + ": " + faceNumber);
			} else {
				yahtzeePanel.add(faceLabels[x]);
			}

		}
	}

	public void removeDice() {
		for (int x = 0; x < faceLabels.length; x++) {
			yahtzeePanel.remove(faceLabels[x]);
		}
	}

	public void addCheckBoxes() {
		for (int x = 0; x < diceCheckBoxes.length; x++) {
			diceCheckBoxes[x] = new JCheckBox("Dice " + (x + 1));
			diceCheckBoxes[x].setForeground(Color.yellow);
			checkBoxPanel.add(diceCheckBoxes[x]);
			diceCheckBoxes[x].setEnabled(false);
		}
	}

	public void enableCheckBoxes() {
		for (int x = 0; x < diceCheckBoxes.length; x++) {
			diceCheckBoxes[x].setEnabled(true);
			diceCheckBoxes[x].setSelected(false);
		}
	}

	public void disableCheckBoxes() {
		for (int x = 0; x < diceCheckBoxes.length; x++) {
			diceCheckBoxes[x].setEnabled(false);
		}
	}

	public void disableButtons() {
		rollAllDiceButton.setEnabled(false);
		reRollDiceButton.setEnabled(false);
		scoreButton.setEnabled(false);
	}

	public void enableAllScoreButtons() {
		for (int x = 0; x < scoreButtons.length; x++) {
			scoreButtons[x].setEnabled(true);
		}
	}

	public void enableUnusedScoreButtons() {
		if (isSinglePlayer == true) {
			for (int x = 0; x < scoreButtons.length; x++) {
				if (x < 6) {
					if (scoreSheet.upperSectionGrid[scoreSheetRowPosition + x][scoreSheetColumnPosition]
							.equals("")) {
						scoreButtons[x].setEnabled(true);

					}
				} else if (x > 5) {
					if (scoreSheet.lowerSectionGrid[scoreSheetRowPosition
							+ (x - 6)][scoreSheetColumnPosition].equals("")) {
						scoreButtons[x].setEnabled(true);
					}
				}
			}
		} else {
			for (int x = 0; x < scoreButtons.length; x++) {
				if (x < 6) {
					if (scoreSheets.get(currentPlayerTurn).upperSectionGrid[scoreSheetRowPosition
							+ x][scoreSheetColumnPosition].equals("")) {
						scoreButtons[x].setEnabled(true);

					}
				} else if (x > 5) {
					if (scoreSheets.get(currentPlayerTurn).lowerSectionGrid[scoreSheetRowPosition
							+ (x - 6)][scoreSheetColumnPosition].equals("")) {
						scoreButtons[x].setEnabled(true);
					}
				}
			}
		}
	}

	public void disableScoreButtons() {
		for (int x = 0; x < scoreButtons.length; x++) {
			scoreButtons[x].setEnabled(false);
		}
	}

	public void resetScore() {
		if (isSinglePlayer == true) {
			scores.upperScore = 0;
			scores.acesScore = 0;
			scores.twosScore = 0;
			scores.threesScore = 0;
			scores.foursScore = 0;
			scores.fivesScore = 0;
			scores.sixesScore = 0;
			scores.lowerScore = 0;
			scores.threeOfAKindScore = 0;
			scores.fourOfAKindScore = 0;
			scores.fullHouseScore = 0;
			scores.smallStraightScore = 0;
			scores.largeStraightScore = 0;
			scores.yahtzeeScore = 0;
			scores.chanceScore = 0;
			scores.totalScore = 0;
		} else {
			for (Scores s : playerScores) {
				s.upperScore = 0;
				s.acesScore = 0;
				s.twosScore = 0;
				s.threesScore = 0;
				s.foursScore = 0;
				s.fivesScore = 0;
				s.sixesScore = 0;
				s.lowerScore = 0;
				s.threeOfAKindScore = 0;
				s.fourOfAKindScore = 0;
				s.fullHouseScore = 0;
				s.smallStraightScore = 0;
				s.largeStraightScore = 0;
				s.yahtzeeScore = 0;
				s.chanceScore = 0;
				s.totalScore = 0;
			}
		}
	}

	public void getFinalScore() {
		if (isSinglePlayer == true) {
			singlePlayerRound++;
		} else if (multiplayerRound != 13
				&& currentPlayerTurn == numberOFPlayersComboBox
						.getSelectedIndex() + 1) {
			multiplayerRound++;			
		}
		addAnimatedDice();
		if (singlePlayerRound == 13) {
			scoreSheet.updateUpperScoreSheet(scoreSheetRowPosition + 6,
					scores.upperScore, scoreSheetColumnPosition);
			scoreSheet.nextRoundButton.setEnabled(false);
			ScoreSheet.finishButton.setEnabled(true);
			startNewGame();
			if (scores.upperScore >= 63) {
				scores.upperScore += 35;
				scoreSheet.updateUpperScoreSheet(scoreSheetRowPosition + 7, 35,
						scoreSheetColumnPosition);

			}
			scoreSheet.updateUpperScoreSheet(scoreSheetRowPosition + 8,
					scores.getUpperScore(), scoreSheetColumnPosition);
			scoreSheet.updateLowerScoreSheet(scoreSheetRowPosition + 8,
					scores.getLowerScore(), scoreSheetColumnPosition);
			scoreSheet.updateLowerScoreSheet(scoreSheetRowPosition + 9,
					scores.getTotalScore(), scoreSheetColumnPosition);
		} else if (isSinglePlayer == false && multiplayerRound == 13) {
			scoreSheets.get(previousPlayerTurn).updateUpperScoreSheet(
					scoreSheetRowPosition + 6,
					playerScores.get(previousPlayerTurn).upperScore,
					scoreSheetColumnPosition);
			scoreSheets.get(previousPlayerTurn).nextRoundButton
					.setEnabled(false);
			if (currentPlayerTurn == (numberOFPlayersComboBox
					.getSelectedIndex() + 2)) {
				scoreSheets.get(previousPlayerTurn).nextPlayersTurnButton
						.setEnabled(false);
			}
			ScoreSheet.finishButton.setEnabled(true);

			if (currentPlayerTurn == (numberOFPlayersComboBox
					.getSelectedIndex() + 2)) {
				startNewGame();
			}
			if (playerScores.get(previousPlayerTurn).upperScore >= 63) {
				playerScores.get(previousPlayerTurn).upperScore += 35;
				scoreSheets.get(previousPlayerTurn)
						.updateUpperScoreSheet(scoreSheetRowPosition + 7, 35,
								scoreSheetColumnPosition);

			}
			scoreSheets.get(previousPlayerTurn).updateUpperScoreSheet(
					scoreSheetRowPosition + 8,
					playerScores.get(previousPlayerTurn).getUpperScore(),
					scoreSheetColumnPosition);
			scoreSheets.get(previousPlayerTurn).updateLowerScoreSheet(
					scoreSheetRowPosition + 8,
					playerScores.get(previousPlayerTurn).getLowerScore(),
					scoreSheetColumnPosition);
			scoreSheets.get(previousPlayerTurn).updateLowerScoreSheet(
					scoreSheetRowPosition + 9,
					playerScores.get(previousPlayerTurn).getTotalScore(),
					scoreSheetColumnPosition);
		}

		if (isSinglePlayer == false
				&& currentPlayerTurn < (numberOFPlayersComboBox
						.getSelectedIndex() + 2)) {
			scoreSheets.get(previousPlayerTurn).nextRoundButton
					.setEnabled(false);
			ScoreSheet.finishButton.setEnabled(false);
		} else if (isSinglePlayer == false) {
			scoreSheets.get(previousPlayerTurn).nextPlayersTurnButton
					.setEnabled(false);
			if (currentPlayerTurn != (numberOFPlayersComboBox
					.getSelectedIndex() + 2)) {
				scoreSheets.get(previousPlayerTurn).nextRoundButton
						.setEnabled(true);
			}
			determineWinner();
			if (multiplayerRound != 1
					&& (currentPlayerTurn != (numberOFPlayersComboBox
							.getSelectedIndex() + 2)))
				previousPlayerTurn = 0;
			currentPlayerTurn = 0;

		}

		rollAllDiceButton.setEnabled(true);
		removeDice();
		disableScoreButtons();
		yahtzeePanel.revalidate();
		yahtzeePanel.repaint();
	}

	public void startNewGame() {
		ScoreSheet.finishButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gameNumber < 4
						&& (singlePlayerRound == 13 || multiplayerRound == 13)) {
					scoreSheet.promptPlayerForNewGame();
					if (isSinglePlayer == true
							&& scoreSheet.optionResponse == ScoreSheet.OPTION_YES) {
						gameNumber++;
						singlePlayerRound = 12;
						scoreSheetColumnPosition++;
						scoreSheet.optionResponse = ScoreSheet.OPTION_NO;
						scoreSheet.scoreSheetFrame.setVisible(false);
						ScoreSheet.finishButton.setEnabled(false);
						scoreSheet.nextRoundButton.setEnabled(true);
					} else if (scoreSheets != null
							&& scoreSheet.optionResponse == ScoreSheet.OPTION_YES) {
						gameNumber++;
						multiplayerRound = 12;
						scoreSheetColumnPosition++;
						scoreSheet.optionResponse = ScoreSheet.OPTION_NO;
						scoreSheets.get(previousPlayerTurn).scoreSheetFrame
								.setVisible(false);
						scoreSheets.get(previousPlayerTurn).nextRoundButton
								.setEnabled(false);
						ScoreSheet.finishButton.setEnabled(false);

					} else {

						JOptionPane
								.showMessageDialog(
										scoreSheet.scoreSheetFrame,
										"Thank you for playing Yahtzee! Returning to Main Menu",
										"Exit Yahtzee",
										JOptionPane.INFORMATION_MESSAGE);
						if (isSinglePlayer == true) {
							scoreSheet.scoreSheetFrame.setVisible(false);
							scoreSheet = new ScoreSheet();

						} else {
							scoreSheets.get(previousPlayerTurn).scoreSheetFrame
									.setVisible(false);
							scoreSheet.scoreSheetFrame.setVisible(false);
						}
						yahtzeeFrame.setVisible(false);
						gameNumber = 1;
						singlePlayerRound = 0;
						multiplayerRound = 0;
						disableScoreButtons();
						resetScore();
						scoreSheetColumnPosition = 2;
						yahtzeeTitleScreenFrame.setVisible(true);

					}
					disableScoreButtons();
					resetScore();

				} else if (singlePlayerRound == 13 || multiplayerRound == 13) {
					JOptionPane
							.showMessageDialog(
									scoreSheet.scoreSheetFrame,
									"Thank you for playing Yahtzee! Returning to Main Menu.",
									"Match Over",
									JOptionPane.INFORMATION_MESSAGE);
					scoreSheet.scoreSheetFrame.setVisible(false);
					yahtzeeFrame.setVisible(false);
					gameNumber = 1;
					singlePlayerRound = 0;
					multiplayerRound = 0;
					disableScoreButtons();
					resetScore();
					scoreSheetColumnPosition = 2;
					scoreSheet = new ScoreSheet();
					yahtzeeTitleScreenFrame.setVisible(true);
				}
			}
		});
	}

	public void determineWinner() {		

		for (int x = 0; x < gamerTags.length; x++) {
			int place = gamerTags.length;			
			for (int y = 0; y < gamerTags.length; y++) {
				if (playerScores.get(x).getTotalScore() >= playerScores.get(y)
						.getTotalScore() && x != y) {					
					place--;
				}
				if((playerScores.get(x).getTotalScore() == playerScores.get(y)
						.getTotalScore()) && x > y)
						place++;

			}			
			if (place == 1) {
				firstPlacePoints = playerScores.get(x).getTotalScore();
				firstPlace = gamerTags[x];				

			} else if (place == 2) {
				secondPlacePoints = playerScores.get(x).getTotalScore();
				secondPlace = gamerTags[x];				

			} else if (place == 3) {
				thirdPlacePoints = playerScores.get(x).getTotalScore();
				thirdPlace = gamerTags[x];				

			} else {
				fourthPlacePoints = playerScores.get(x).getTotalScore();
				fourthPlace = gamerTags[x];				
			}

		}
		JOptionPane.showMessageDialog(scoreSheet.scoreSheetFrame,
				"First Place: " + firstPlace + " [" + firstPlacePoints + "]\n"
						+ "Second Place: " + secondPlace + " ["
						+ secondPlacePoints + "]\n" + "Third Place: "
						+ (thirdPlace == null ? "" : thirdPlace) + " ["
						+ thirdPlacePoints + "]\n" + "Fourth Place: "
						+ (fourthPlace == null ? "" : fourthPlace) + " ["
						+ fourthPlacePoints + "]", "Places",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public static void main(String[] args) {
		new Yahtzee();
	}

}

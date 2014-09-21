package schoolWork;

import java.awt.event.*;
import java.awt.datatransfer.*;
import java.awt.*;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;

import javax.swing.*;

public class CheckersBoard extends TransferHandler {
	public JFrame checkersFrame;
	public JPanel checkersPanel;
	public JButton sourceButton,targetButton;
	public JButton[][] checkersSpaces = new JButton[8][8];	
	public ImageIcon redPiece, goldPiece;
	public ArrayList<ImageIcon> redPieces;
	public ArrayList<ImageIcon> goldPieces;
	public MouseEvent event;
	public boolean canDeliver = true;
	public int x = 0;
	public int y = 0;

	public CheckersBoard() {

		checkersFrame = new JFrame("Checkers");
		checkersPanel = new JPanel();
		checkersFrame.add(checkersPanel);
		checkersPanel.setLayout(new GridLayout(8, 8, 0, 0));
		checkersFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		checkersFrame.setLocation(350, 30);
		checkersFrame.setSize(700, 700);
		checkersFrame.setVisible(true);
		redPiece = new ImageIcon("C:\\Users\\tbeardjr\\workspace\\Java\\src\\schoolWork\\red star.png");
		redPieces = new ArrayList<>();
		goldPiece = new ImageIcon("C:\\Users\\tbeardjr\\workspace\\Java\\src\\schoolWork\\gold star.png");
		goldPieces = new ArrayList<>();
		setCheckerBoard();		
		//MouseListener listener = new DragMouseAdapter();
		
		for (x = 0; x < checkersSpaces.length; x++) {
			for (y = 0; y < checkersSpaces.length; y++) {
				
								
				checkersSpaces[x][y].addMouseListener(new MouseAdapter(){ 
					 
					 TransferHandler handle;
					 public void mousePressed(MouseEvent e){						 
						 sourceButton = (JButton)e.getSource(); 						
						 handle = sourceButton.getTransferHandler(); 
						 handle.exportAsDrag(sourceButton, e, TransferHandler.MOVE); 						 
						 
					 }
				 });
				checkersSpaces[x][y].setTransferHandler(new TransferHandler("icon") {
					@Override
				    public int getSourceActions(JComponent c) {
				        return MOVE;
				    }
					
				    @Override
				    protected void exportDone(JComponent c, Transferable data, int action) {				    	
				        if (action == MOVE){				        	
				        	((JButton) c).setIcon(null);
				        	sourceButton = (JButton) c;
				            return;
				        }
				    }
				    public boolean canImport(TransferHandler.TransferSupport support) 
				    {	
				    	System.out.println(support.getDropLocation());
				    	System.out.println(sourceButton.getLocation());
				    	if(support.getDropLocation().getDropPoint().getLocation().equals(sourceButton.getLocation())){
				    		canDeliver = true;
				    	}
				    	
				    	
						return canDeliver;
				    }

				    
				});
				//checkersSpaces[x][y].setTransferHandler(new TransferHandler("icon"));
				
				
			}
		}       
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
							checkersSpaces[x][y].setIcon(redPiece);
							//checkersSpaces[x][y].setIcon(null);
							redPieces.add(redPiece);
						}
						else if(x > 4)
						{
							checkersSpaces[x][y].setIcon(goldPiece);
							goldPieces.add(goldPiece);
						}
					}
				} else {
					if (y % 2 == 0) {
						checkersSpaces[x][y].setBackground(Color.black);
						if(x < 3)
						{
							checkersSpaces[x][y].setIcon(redPiece);
							redPieces.add(redPiece);
						}
						else if(x > 4)
						{
							checkersSpaces[x][y].setIcon(goldPiece);
							goldPieces.add(goldPiece);
						}
					} else {
						checkersSpaces[x][y].setBackground(Color.white);
						checkersSpaces[x][y].setEnabled(false);
					}
				}
				checkersPanel.add(checkersSpaces[x][y]);
			}
		}
	}	
	
	


	public static void main(String[] args) {		
		new CheckersBoard();
	}

}

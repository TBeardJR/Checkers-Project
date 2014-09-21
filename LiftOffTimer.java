package schoolWork;

import javax.swing.*;

public class LiftOffTimer {
	

	public static void main(String[] args) throws Exception{	
		JFrame liftOffFrame;
		JPanel liftOffPanel;
		JLabel liftOffLabel;
		ImageIcon liftOffImage;
		int y = 9;
		while(y > 0)
		{
		for(int x = 10; x > 0; x--)
		{			
			System.out.println("T-" + x + " seconds to launch.");
			Thread.sleep(1000);
		}
		System.out.println("Lift Off!");
		liftOffFrame = new JFrame("Lift Off!");
		liftOffFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		liftOffFrame.setSize(320, 230);
		liftOffFrame.setLocation(550, 200);		
		liftOffPanel = new JPanel();
		liftOffFrame.add(liftOffPanel);
		liftOffImage  = new ImageIcon("C:\\Users\\tbeardjr\\workspace\\Java\\src\\schoolWork\\rocket-launch.gif");
		liftOffLabel = new JLabel(liftOffImage);
		liftOffPanel.add(liftOffLabel);
		liftOffPanel.revalidate();
		liftOffPanel.repaint();
		liftOffFrame.setVisible(true);
		Thread.sleep(3700);
		liftOffFrame.setVisible(false);
		}
		System.exit(0);

	}

}

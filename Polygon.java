package schoolWork;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import javax.swing.*;
public class Polygon extends JComponent{

	static ArrayList<Point2D.Double> endPoints;
	
	public void add(Point2D.Double aPoint)
	{
		endPoints.add(new Point2D.Double(aPoint.x, aPoint.y));		
	}
	public void drawSquare(Graphics2D g2)
	{		
		//Top left Point
		Point2D.Double point1 = new Point2D.Double(200, 200);
		add(point1);
		//Top Right Point
		Point2D.Double point2 = new Point2D.Double(350, 200);
		add(point2);
		//Bottom right point
		Point2D.Double point3 = new Point2D.Double(350, 350);
		add(point3);
		//Bottom left point
		Point2D.Double point4 = new Point2D.Double(200, 350);
		add(point4);		
		
		//Top Line
		Line2D.Double topLine = new Line2D.Double(point1, point2);	
		g2.draw(topLine);
		//Right Line
		Line2D.Double rightLine = new Line2D.Double(point2, point3);	
		g2.draw(rightLine);
		//Bottom Line
		Line2D.Double bottomLine = new Line2D.Double(point3, point4);	
		g2.draw(bottomLine);
		//Left Line
		Line2D.Double leftLine = new Line2D.Double(point4, point1);	
		g2.draw(leftLine);
		
	}
	public void drawPentagon(Graphics2D g2)
	{						
				//Top left Point
				Point2D.Double point1 = new Point2D.Double(600, 200);
				add(point1);
				//Top Right Point
				Point2D.Double point2 = new Point2D.Double(750, 200);
				add(point2);
				//Bottom right point
				Point2D.Double point3 = new Point2D.Double(750, 350);
				add(point3);
				//Bottom left point
				Point2D.Double point4 = new Point2D.Double(600, 350);
				add(point4);
				//Top Point
				Point2D.Double point5 = new Point2D.Double(675, 150);
				add(point5);
				
				//Diagonal line on left Line
				Line2D.Double diagonalLeftLine = new Line2D.Double(point1, point5);	
				g2.draw(diagonalLeftLine);
				//Diagonal line on right Line
				Line2D.Double diagonalRightLine = new Line2D.Double(point2, point5);	
				g2.draw(diagonalRightLine);
				//Right Line
				Line2D.Double rightLine = new Line2D.Double(point2, point3);	
				g2.draw(rightLine);
				//Bottom Line
				Line2D.Double bottomLine = new Line2D.Double(point3, point4);	
				g2.draw(bottomLine);
				//Left Line
				Line2D.Double leftLine = new Line2D.Double(point4, point1);	
				g2.draw(leftLine);
	}
	public void paintComponent(Graphics g)
	{
		Polygon square = new Polygon();		
		Polygon pentagon = new Polygon();		
		Graphics2D g2 = (Graphics2D) g;
		square.drawSquare(g2);
		pentagon.drawPentagon(g2);
	}
	public static void main(String[] args) {
		Polygon polygon = new Polygon();
		endPoints = new ArrayList<Point2D.Double>();
		JFrame frame = new JFrame("Polygon");
		frame.setSize(800,800);
		frame.add(polygon);
		frame.setVisible(true);

	}

}

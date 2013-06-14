package com.demo.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import org.math.plot.*;
import static java.lang.Math.*;
import javax.swing.JTabbedPane;

public class MainFrame extends JFrame {
	
	private static final long serialVersionUID = 7953904993127525010L;

	public MainFrame() {
		super();

		this.setTitle("JMathPlot Demo");
		
		Plot2DPanel plot2dPanel = new Plot2DPanel();
		Plot3DPanel plot3dPanel = new Plot3DPanel();
		
		JTabbedPane tabPane = new JTabbedPane(JTabbedPane.TOP);
		this.setLayout(new GridLayout(1,1));
		this.setContentPane(tabPane);
		tabPane.addTab("3D", null, plot3dPanel);
		tabPane.addTab("2D", null, plot2dPanel);
		tabPane.setSelectedIndex(0);
		
		plot2dPanel.plotToolBar.setVisible(false);
		plot2dPanel.setPreferredSize(new Dimension(600, 600));
		plot2dPanel.setNotable(false);
		plot2dPanel.setEditable(false);
		plot2dPanel.setActionMode(1);
		plot2dPanel.setEnabled(false);		
		plot2dPanel.plotCanvas.removeMouseListener(plot2dPanel.plotCanvas);
		plot2dPanel.plotCanvas.removeMouseMotionListener(plot2dPanel.plotCanvas);		
		plot2dPanel.plotCanvas.removeMouseWheelListener(plot2dPanel.plotCanvas);	
		
		double[] x = {30.0, 50.0};
		double[] y = {790, 600};
		plot2dPanel.addLinePlot("A", Color.BLUE, x, y);	
		
		double[] pointsX = new double[200];
		double[] pointsY = new double[200];
		for (int j = 0; j < pointsX.length; j++) {
			double pointX = random() * 1000;
			double pointY = random() * 1000;
			pointsX[j] = pointX;
			pointsY[j] = pointY;
			plot2dPanel.addPlot("SCATTER", "Point", pointsX, pointsY);
		}	

		plot3dPanel.plotToolBar.setVisible(false);
		plot3dPanel.setAxisLabels("X", "Y", "Z");

		double[][] XYZ;
		XYZ = new double[200][3];
		for (int i = 0; i < 200; i++) {
			double X = random() * 1000;
			double Y = random() * 1000;
			double Z = random() * 1000;
			XYZ[i][0] = X;
			XYZ[i][1] = Y;
			XYZ[i][2] = Z;
		}

		plot3dPanel.addScatterPlot("Demo", Color.BLUE, XYZ);
		plot3dPanel.setPreferredSize(new Dimension(600, 600));
		
		this.setSize(new Dimension(600, 600));
		this.repaint();
		this.validate();
	}
	
	public static void main(String[] args) {
		MainFrame mainFrame = new MainFrame();
		mainFrame.setPreferredSize(new Dimension(600, 600));
		mainFrame.setVisible(true);
	}
}

/**
 * 
 */

package org.fda.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Rectangle;
import javax.swing.JSplitPane;

public class ChemViewer extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2886643849534802176L;
	private static ChemViewer chemViewer;
	
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				chemViewer = new ChemViewer();
				chemViewer.setVisible(true);
			}
		});
	}

	public ChemViewer() {
		this.setSize(1024, 768);
		
		/**
		 * Nest JSplitPane
		 */
		JSplitPane hSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true);
		JPanel leftContainerPanel = new JPanel();
		hSplitPane.setLeftComponent(leftContainerPanel);
		this.getContentPane().add(hSplitPane);
		hSplitPane.setPreferredSize(new Dimension(1024, 768));
		hSplitPane.setBounds(this.getBounds());
		hSplitPane.setDividerLocation(330);

		JSplitPane vSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true);
		vSplitPane.setPreferredSize(new Dimension(500, 768));
		vSplitPane.setDividerLocation(180);

		JPanel topRightPanel = new JPanel();
		topRightPanel.setBounds(new Rectangle(0, 0, 300, 100));
		topRightPanel.setBackground(Color.LIGHT_GRAY);
		vSplitPane.setTopComponent(topRightPanel);

		ChemViewerPanel chemViewerPanel = new ChemViewerPanel();
		leftContainerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		leftContainerPanel.add(chemViewerPanel);
		
		CompoundListPanel bottomRightPanel = new CompoundListPanel(new GridLayout(0, 1), chemViewerPanel);
		vSplitPane.setBottomComponent(bottomRightPanel);
		hSplitPane.setRightComponent(vSplitPane);
		 
//		chemViewerPanel.showStructureWithSmiles("Oc1ccc(cc1)C2=COc3cc(O)cc(O)c3C2=O");
		this.repaint();
	}
}

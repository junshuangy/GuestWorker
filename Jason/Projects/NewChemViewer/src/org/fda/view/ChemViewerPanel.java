// New version

/**
 *  LeftTop ViewerPanel
 */

package org.fda.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.fda.model.ObservableTableData;
import org.openscience.cdk.interfaces.IChemModel;
import org.openscience.jchempaint.JChemPaintPanel;

public class ChemViewerPanel extends JPanel implements Observer{
	private static final long serialVersionUID = 4008220638004976058L;
	
	private JCPViewPanel jcpViewerPanel;
	private JPanel btnsPanel;
	private JButton btnEdit;
	private JButton btnClear;
	
	public ChemViewerPanel() {
		super();
		createAndInitGUI();
	}
	
	private void createAndInitGUI() {
		this.setBorder(BorderFactory.createTitledBorder("Compound Structure Search"));
		this.setBounds(new Rectangle(0, 0, 300, 400));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		jcpViewerPanel = new JCPViewPanel(null);
		jcpViewerPanel.setBounds(new Rectangle(0, 0, 300, 300));
		jcpViewerPanel.setPreferredSize(new Dimension(300, 300));
		this.add(jcpViewerPanel);
		jcpViewerPanel.setBackground(Color.WHITE);
		
		btnsPanel = new JPanel();
		this.add(btnsPanel);
		btnsPanel.setLayout(new FlowLayout());
		btnsPanel.setBounds(0, 300, 300, 100);
		
		btnEdit = new JButton();
		btnsPanel.add(btnEdit);
		btnEdit.setBounds(0, 0, 100, 50);
		btnEdit.setFocusPainted(false);
		btnEdit.setText("Edit");
		btnEdit.addActionListener(new EditButtonHandler());

		btnClear = new JButton();
		btnsPanel.add(btnClear);
		btnClear.setBounds(200, 0, 100, 50);
		btnClear.setText("Clear");
		btnClear.setFocusPainted(false);
		btnClear.addActionListener(new ClearButtonHandler());

		this.repaint();
		this.revalidate();

	}

	
	/**
	 * when table selection change, update this viewer panel
	 */
	@Override
	public void update(Observable o, Object arg) {
		System.out.println("Update chemviewer......");
		this.jcpViewerPanel.setSmiles((String)((ObservableTableData)o).getData());
		this.repaint();
	}
	
	// event handler class
	private class EditButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Edit button pressed!");
			showEditor(jcpViewerPanel.getChemModel(), "Editor", false);
		}
	}

	private class ClearButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Clear button pressed!");
			jcpViewerPanel.setSmiles(null);
			jcpViewerPanel.repaint();
		}	
	}
	
	private void showEditor(IChemModel chemModel, String title, boolean debug) {
		JFrame frame = new JFrame(title);
	    chemModel.setID(title);
	    frame.addWindowListener(new ChemViewerPanel.AppCloser());
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    JChemPaintPanel p = new JChemPaintPanel(chemModel, "application", debug, null, new ArrayList());
	    p.setShowMenuBar(false);
	    p.updateStatusBar();
	    frame.setPreferredSize(new Dimension(800, 600));
	    frame.add(p);
	    frame.pack();
	    Point point = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();

	    int w2 = frame.getWidth() / 2;
	    int h2 = frame.getHeight() / 2;
	    frame.setLocation(point.x - w2, point.y - h2);
	    frame.setVisible(true);
	}
	
    public class AppCloser extends WindowAdapter
    {
    	public void windowClosing(WindowEvent e)
    	{
    		JChemPaintPanel editPanel = (JChemPaintPanel)((JFrame)e.getSource()).getContentPane().getComponents()[0];
    		System.out.println("Editor window close!!!!" + editPanel.getChemModel().toString());
    		jcpViewerPanel.updateToChemModel(editPanel.getChemModel());
            ((JFrame)e.getSource()).setVisible(false);
            ((JFrame)e.getSource()).dispose();
    	}
    }

}



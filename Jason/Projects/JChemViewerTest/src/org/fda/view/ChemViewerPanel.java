/**
 *  LeftTop ViewerPanel
 */

package org.fda.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import org.fda.model.ObservableTableData;
import org.openscience.cdk.geometry.GeometryTools;
import org.openscience.cdk.interfaces.AtomContainer;
import org.openscience.cdk.Molecule;
import org.openscience.cdk.ChemModel;
import org.openscience.cdk.SetOfMolecules;
import org.openscience.cdk.applications.jchempaint.JChemPaintEditorPanel;
import org.openscience.cdk.applications.jchempaint.JChemPaintModel;
import org.openscience.cdk.applications.jchempaint.JChemPaintViewerOnlyPanel;
import org.openscience.cdk.layout.StructureDiagramGenerator;
import org.openscience.cdk.smiles.SmilesGenerator;
import org.openscience.cdk.smiles.SmilesParser;
import org.openscience.cdk.tools.manipulator.ChemModelManipulator;

public class ChemViewerPanel extends JPanel implements Observer {
	private static final long serialVersionUID = 4008220638004976058L;
	private JChemPaintEditorPanel jcpEditorPanel;
	private JChemPaintViewerOnlyPanel jcpViewerPanel;
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

		jcpViewerPanel = new JChemPaintViewerOnlyPanel(new Dimension(300, 300), "");
		jcpViewerPanel.setBounds(0, 0, 300, 300);
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
	 * Test Smiles:("Oc1ccc(cc1)C2=COc3cc(O)cc(O)c3C2=O");
	 * @param smiles
	 */
	public void showStructureWithSmiles(String smiles) {
		JChemPaintModel jcpModel = null;
		
		try {
			Molecule molecule = new SmilesParser().parseSmiles(smiles);
			
			StructureDiagramGenerator sdg = new StructureDiagramGenerator();
			sdg.setMolecule(molecule);
			sdg.generateCoordinates();
			
			molecule = (Molecule) sdg.getMolecule();
			SetOfMolecules moleculeSet = new SetOfMolecules();
			moleculeSet.addMolecule(molecule);
			
			ChemModel model = new ChemModel();
			model.setSetOfMolecules(moleculeSet);
			jcpModel = new JChemPaintModel(model);	
		} 
		catch (Exception e) {
			jcpModel = null;
			e.printStackTrace();
		}
		
		this.showModelInViewerPanel(jcpModel);
	}
	
	private void showModelInViewerPanel(JChemPaintModel jcpModel) {
		jcpViewerPanel.registerModel(jcpModel);
		jcpViewerPanel.setJChemPaintModel(jcpModel);
		jcpViewerPanel.setBounds(0, 0, 300, 300);
		
		// TODO: Calculate the scale factor		
		AtomContainer ac = ChemModelManipulator.getAllInOneContainer(jcpViewerPanel.getChemModel());
		GeometryTools.scaleMolecule(ac, jcpViewerPanel.getViewerDimension(), 0.9);
		GeometryTools.center(ac, jcpViewerPanel.getViewerDimension());
//		jcpViewerPanel.scaleAndCenterMolecule(jcpViewerPanel.getChemModel());
		jcpViewerPanel.repaint();
		jcpViewerPanel.revalidate();
	}

	private class EditButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Edit button pressed!");

			// pass the model from jcpViewerPanel to jcpEditorPanel
			JChemPaintModel model = new JChemPaintModel(jcpViewerPanel.getChemModel());
			jcpEditorPanel = new JChemPaintEditorPanel(1, new Dimension(900, 450));
			jcpEditorPanel.setShowMenuBar(false);
			jcpEditorPanel.registerModel(model);
			jcpEditorPanel.setJChemPaintModel(model);
			
			
			JFrame frame = new JFrame("Editor");
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setPreferredSize(new Dimension(900, 450));
			frame.setLocationByPlatform(true);
			frame.setVisible(true);
			frame.getContentPane().add(jcpEditorPanel);
			frame.pack();
			frame.repaint();

			// scroll to show
			AtomContainer ac = ChemModelManipulator.getAllInOneContainer(model.getChemModel());
			JScrollBar vScrollBar = jcpEditorPanel.getScrollPane().getVerticalScrollBar();
			vScrollBar.setValue((int) GeometryTools.get2DCenter(ac).y - jcpEditorPanel.getScrollPane().getHeight() / 2);
			

			frame.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e) {
					// repaint the jcpViewerPanel
					JChemPaintModel model = new JChemPaintModel(jcpEditorPanel.getChemModel());
					AtomContainer atomContainer = ChemModelManipulator.getAllInOneContainer(model.getChemModel());
					SmilesGenerator generator = new SmilesGenerator(model.getChemModel().getBuilder());
					String newSmileString = generator.createSMILES(new Molecule(atomContainer));
					System.out.println("NewSmiles:" + newSmileString);
					showStructureWithSmiles(newSmileString);
				}
			});
		}
	}

	private class ClearButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Clear button pressed!");
			JChemPaintModel emptyModel = new JChemPaintModel();
			jcpViewerPanel.setJChemPaintModel(emptyModel);
			jcpViewerPanel.repaint();
			jcpViewerPanel.revalidate();
		}	
	}

	/**
	 * when table selection change, update this viewer panel
	 */
	@Override
	public void update(Observable o, Object arg) {
		System.out.println("Update chemviewer......");
		this.showStructureWithSmiles((String)((ObservableTableData)o).getData());
		this.repaint();
	}
}



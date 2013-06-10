/**
 * 
 */
package org.fda.view;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;
import javax.vecmath.Vector2d;

import org.openscience.cdk.DefaultChemObjectBuilder;
import org.openscience.cdk.exception.CDKException;
import org.openscience.cdk.interfaces.IAtomContainer;
import org.openscience.cdk.interfaces.IChemModel;
import org.openscience.cdk.interfaces.IMolecule;
import org.openscience.cdk.interfaces.IMoleculeSet;
import org.openscience.cdk.layout.StructureDiagramGenerator;
import org.openscience.cdk.smiles.FixBondOrdersTool;
import org.openscience.cdk.smiles.SmilesParser;
import org.openscience.cdk.tools.CDKHydrogenAdder;
import org.openscience.cdk.tools.manipulator.ChemModelManipulator;
import org.openscience.jchempaint.AbstractJChemPaintPanel;
import org.openscience.jchempaint.JChemPaintPanel;
import org.openscience.jchempaint.RenderPanel;
import org.openscience.jchempaint.controller.IControllerModel;
import org.openscience.jchempaint.renderer.RendererModel;

public class JCPViewPanel extends AbstractJChemPaintPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1444082851631963990L;

	public JCPViewPanel(String smiles) {

		try {
			renderPanel = new RenderPanel(loadModelFromSmiles(smiles), 300, 300, true, false, true, null);
			this.add(renderPanel,BorderLayout.CENTER);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		
		RendererModel rendererModel = 
			this.get2DHub().getRenderer().getRenderer2DModel();
		IChemModel chemModel = this.getChemModel();
		IControllerModel controllerModel = 
			this.get2DHub().getController2DModel();

		controllerModel.setAutoUpdateImplicitHydrogens(true);
		rendererModel.setShowImplicitHydrogens(true);
		rendererModel.setShowEndCarbons(false);
		
//		rendererModel.setShowAromaticity(false);
//		rendererModel.setShowAromaticityCDKStyle(false);		
//		rendererModel.setShowExplicitHydrogens(false);
//		rendererModel.setShowAtomTypeNames(false);
//		rendererModel.setShowAtomAtomMapping(false);
//		rendererModel.setDrawNumbers(false);

		if (chemModel != null) {
			List<IAtomContainer> atomContainers = 
				ChemModelManipulator.getAllAtomContainers(chemModel);
			for (int i = 0; i < atomContainers.size(); i++) {
				try {
					CDKHydrogenAdder.getInstance(atomContainers.get(i).getBuilder())
							.addImplicitHydrogens(atomContainers.get(i));
				} catch (CDKException e) {
					// do nothing
				}
			}
		}
	}

	public IChemModel loadModelFromSmiles(String smiles) {
		IChemModel chemModel = DefaultChemObjectBuilder.getInstance().newInstance(IChemModel.class, new Object[0]);
        if (smiles != null) {
            try {
                SmilesParser sp = new SmilesParser(DefaultChemObjectBuilder.getInstance());
                IMolecule mol = sp.parseSmiles(smiles);
                mol = new FixBondOrdersTool().kekuliseAromaticRings(mol);
                
                //for some reason, smilesparser sets valencies, which we don't want in jcp
                for(int i=0;i<mol.getAtomCount();i++){
                	mol.getAtom(i).setValency(null);
                }
                
                StructureDiagramGenerator sdg = new StructureDiagramGenerator();
                sdg.setMolecule(mol);
                sdg.generateCoordinates(new Vector2d(0, 1));
                mol = sdg.getMolecule();
                chemModel.setMoleculeSet(DefaultChemObjectBuilder.getInstance().newInstance(IMoleculeSet.class, new Object[0]));
                chemModel.getMoleculeSet().addAtomContainer(mol);
            } catch (Exception exception) {
            	exception.printStackTrace();
                this.announceError(exception);
            }
        }
        
		return chemModel ;
    }

    public void setSmiles(String smiles) {
        this.setChemModel(loadModelFromSmiles(smiles));
        this.get2DHub().updateView();
        repaint();
    }
    
    public void updateToChemModel(IChemModel model) {
        this.setChemModel(model);
        this.get2DHub().updateView();
        repaint();
    }
}


/**
 * @author yang
 */
package org.fda.view;

import java.awt.LayoutManager;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.fda.db.DataFetcher;
import org.fda.model.ObservableTableData;

/**
 *
 */
public class CompoundListPanel extends JPanel {
	private static final long serialVersionUID = 6426641143595805466L;
	private final int columnIndexOfSmiles = 3;
	
	/**
	 * 
	 * @param layout
	 * @param observer : which observes the change of selected row
	 */
	public CompoundListPanel(LayoutManager layout, Observer observer) {
		super(layout);
		createAndInitTable(observer);
	}
	
	/**
	 * 
	 * @param observer
	 */
	private void createAndInitTable(Observer observer) {
		DataFetcher fetcher = new DataFetcher();
		final Object[] names = fetcher.getColumnNames();
		final Object[][] records = fetcher.getDataRecords();		
		final JTable table = new JTable(records, names);
		table.setRowHeight(20);
		
		// Add observable object
		final ObservableTableData selectData = new ObservableTableData();
		selectData.addObserver(observer);
		
		// add table and scroll panel
	  	JScrollPane scrollPane = new JScrollPane (table);
	  	this.add (scrollPane);
	  	
	  	// Table selection listener
	  	table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	  	table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
	  			int row = table.getSelectedRow();
	  			String smileString = (String)table.getValueAt(row, columnIndexOfSmiles);
	  			selectData.setData(smileString);
	  			selectData.notifyObservers();
			}
		});
	}
}

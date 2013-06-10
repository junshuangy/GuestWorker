package org.fda.model;

import java.util.Observable;

public class ObservableTableData extends Observable {
	  private Object data;

	  public void setData(Object newData)
	  {
	    data = newData;
	    setChanged();
	    System.out.println("Data changed!");
	  }

	  public Object getData()
	  {
	    return data;
	  }
}

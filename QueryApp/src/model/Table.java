package model;

import java.util.*;
import javafx.collections.*;
import javafx.beans.property.*;

public class Table {
	
	private String[] headers;
	private ObservableList <RowItem> rowItems;
	
	public Table (String... headers) {
		this.headers = headers;
		rowItems = new SimpleListProperty <> ();
	}
	
	public String[] getHeaders () {
		return headers;
	}
	
	public void addRowItem (RowItem r) {
		rowItems.add (r);
	}
	
	public void removeAllRowItems () {
		rowItems.removeAll ();
	}
	
	public ObservableList <RowItem> getRowItems () {
		return rowItems;
	}
	
}
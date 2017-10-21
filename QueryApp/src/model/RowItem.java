package model;

import javafx.beans.property.*;

public class RowItem {
	
	private final StringProperty col0;
	private final StringProperty col1;
	private final StringProperty col2;
	private final StringProperty col3;
	private final StringProperty col4;
	private final StringProperty col5;
	private final StringProperty col6;
	private final StringProperty col7;
	private final StringProperty col8;
	private final StringProperty col9;
	
	public RowItem (String... values) {
			col0 = new SimpleStringProperty ();
			col1 = new SimpleStringProperty ();
			col2 = new SimpleStringProperty ();
			col3 = new SimpleStringProperty ();
			col4 = new SimpleStringProperty ();
			col5 = new SimpleStringProperty ();
			col6 = new SimpleStringProperty ();
			col7 = new SimpleStringProperty ();
			col8 = new SimpleStringProperty ();
			col9 = new SimpleStringProperty ();
			
		try {
			col0.set(values[0]);
			col1.set(values[1]);
			col2.set(values[2]);
			col3.set(values[3]);
			col4.set(values[4]);
			col5.set(values[5]);
			col6.set(values[6]);
			col7.set(values[7]);
			col8.set(values[8]);
			col9.set(values[9]);
		} catch (Exception e) {}
	}
	
	public void setCol0 (String str) { col0.set (str); }
	public String getCol0 () { return col0.get (); }
	public StringProperty col0Property () { return col0; }
	
	public void setCol1 (String str) { col1.set (str); }
	public String getCol1 () { return col1.get (); }
	public StringProperty col1Property () { return col1; }
	
	public void setCol2 (String str) { col2.set (str); }
	public String getCol2 () { return col2.get (); }
	public StringProperty col2Property () { return col2; }
	
	public void setCol3 (String str) { col3.set (str); }
	public String getCol3 () { return col3.get (); }
	public StringProperty col3Property () { return col3; }
	
	public void setCol4 (String str) { col4.set (str); }
	public String getCol4 () { return col4.get (); }
	public StringProperty col4Property () { return col4; }
	
	public void setCol5 (String str) { col5.set (str); }
	public String getCol5 () { return col5.get (); }
	public StringProperty col5Property () { return col5; }
	
	public void setCol6 (String str) { col6.set (str); }
	public String getCol6 () { return col6.get (); }
	public StringProperty col6Property () { return col6; }
	
	public void setCol7 (String str) { col7.set (str); }
	public String getCol7 () { return col7.get (); }
	public StringProperty col7Property () { return col7; }
	
	public void setCol8 (String str) { col8.set (str); }
	public String getCol8 () { return col8.get (); }
	public StringProperty col8Property () { return col8; }
	
	public void setCol9 (String str) { col9.set (str); }
	public String getCol9 () { return col9.get (); }
	public StringProperty col9Property () { return col9; }
}
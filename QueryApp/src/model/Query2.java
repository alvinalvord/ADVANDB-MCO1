package model;

import java.sql.*;

public class Query2 extends QueryObject {
	
	public Query2 () {
		super ();
		initVariants ();
		table = new Table ("BookID", "DateOut", "DateReturned");
		setViewing (0);
	}
	
	private void initVariants () {
		variants.add("select b.BookID, b.DateOut, b.DateReturned from (select BookID, DateOut, DateReturned"
				+ " from book_loans" 
				+ " where DateOut >= '2011-07-07' AND"
                + " DateReturned <= '2012-07-10' AND "
                + " DateOut <= DateReturned) as b;");
	}
	
	public void prepareUpdates () throws Exception {
		table.removeAllRowItems ();
		
		setQuery(variants.get(viewing));
		
		long startTime, endTime;
		startTime = System.nanoTime ();
		ResultSet rs = dbc.executeQuery (getQuery ());
		endTime = System.nanoTime ();
		
		setDuration ((endTime - startTime));
		
		while (rs.next ()) {
			table.addRowItem (new RowItem (rs.getString (1), rs.getString (2), rs.getString(3)));
		}
		
		notifyViews ();
	}
	
}
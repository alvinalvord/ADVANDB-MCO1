package model;

import java.sql.*;

public class Query1 extends QueryObject {
	
	public Query1 () {
		super ();
		initVariants ();
		table = new Table ("Title", "PublisherName");
		setViewing (0);
	}
	
	private void initVariants () {
		variants.add
			("select title, publishername " +
			"from book " +
			"where publishername like '%book%'");
	}
	
	public void prepareUpdates () throws Exception {
		setQuery(variants.get(viewing));
		
		long startTime, endTime;
		startTime = System.nanoTime ();
		ResultSet rs = dbc.executeQuery (getQuery ());
		endTime = System.nanoTime ();
		
		setDuration ((endTime - startTime) / 1000000);
		
		while (rs.next ()) {
			table.addRowItem (new RowItem (rs.getString (1), rs.getString (2)));
		}
		
		notifyViews ();
	}
	
}
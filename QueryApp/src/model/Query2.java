package model;

import java.sql.*;

public class Query2 extends QueryObject {
	
	public Query2 () {
		super ();
		initVariants ();
		table = new Table ("Title", "PublisherName");
		setViewing (0);
	}
	
	private void initVariants () {
		variants.add
			("select title, publishername " +
			"from book " +
			"where publishername like '%press%'");
			variants.add
			("select b.title, b.publishername " +
			"from (select title, publishername from book where publishername like '%book%') as b");
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
			table.addRowItem (new RowItem (rs.getString (1), rs.getString (2)));
		}
		
		notifyViews ();
	}
	
}
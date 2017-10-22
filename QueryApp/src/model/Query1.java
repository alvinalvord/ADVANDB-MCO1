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
			"where publishername like '%bbc%'");
			variants.add
			("select b.title, b.publishername " +
			"from (select title, publishername from book where publishername like '%bbc%') as b");
	}
	
	public void prepareUpdates () throws Exception {
		table.removeAllRowItems ();
		
		setQuery(variants.get(viewing));
		System.out.println (getQuery ());
		
		long startTime, endTime;
		startTime = System.currentTimeMillis ();
		ResultSet rs = dbc.executeQuery (getQuery ());
		endTime = System.currentTimeMillis ();
		
		setDuration ((endTime - startTime));
		
		while (rs.next ()) {
			table.addRowItem (new RowItem (rs.getString (1), rs.getString (2)));
		}
		
		notifyViews ();
	}
	
}
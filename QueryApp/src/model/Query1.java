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
			"where publishername = 'BBC Books'");
		variants.add("select b.title, b.publishername " +
				"from(" + 
				"select title, publishername " + 
				"from book " + 
				"where publishername = 'BBC Books') as b");
		variants.add(
				"select title, publishername " +
				"from book " +
				"where publishername = 'BBC Books'");
		variants.add("select title, publishername " +
				"from book " +
				"where publishername = 'BBC Books'");
		variants.add("select title, publishername " +
				"from book " +
				"where publishername = 'BBC Books'");
		variants.add("select title, publishername " +
				"from book " +
				"where publishername = 'BBC Books'");
		variants.add("select b.title, b.publishername " +
				"from (select title, publishername from book where publishername = 'BBC Books') as b");
		variants.add("select b.title, b.publishername " +
				"from (select title, publishername from book where publishername = 'BBC Books') as b");
		variants.add("select b.title, b.publishername " +
				"from (select title, publishername from book where publishername = 'BBC Books') as b");
		variants.add("select b.title, b.publishername " +
				"from (select title, publishername from book where publishername = 'BBC Books') as b");
	}
	
	public void prepareUpdates () throws Exception {
		table.removeAllRowItems ();
		
		try{dbc.executeUpdate("drop index a on book");} catch(Exception e){}
		try{dbc.executeUpdate("drop index b on book");}catch(Exception e){}
		try{dbc.executeUpdate("drop index c on book");}catch(Exception e){}
		
		setQuery(variants.get(viewing));
		
		switch(viewing){
		case 2:
			dbc.executeUpdate("create index a on book(title)");
			setQuery("create index a on book(title);\n" + getQuery());
			break;
		case 3:
			dbc.executeUpdate("create index b on book(publishername)");
			setQuery("create index on b book(publishername);\n" + getQuery());
			break;
		case 4:
			dbc.executeUpdate("create index a on book(publishername)");
			dbc.executeUpdate("create index b on book(title)");
			setQuery("create index a on book(publishername);\ncreate index b on book(title);\n" + getQuery());
			break;
		case 5:
			dbc.executeUpdate("create index a on book(title, publishername)");
			setQuery("create index on a book(title, publishername);\n" + getQuery());
			break;
		case 6:
			dbc.executeUpdate("create index a on book(title)");
			setQuery("create index a on book(title);\n" + getQuery());
			break;
		case 7:
			dbc.executeUpdate("create index a on book(publishername)");
			setQuery("create index a on book(publishername);\n" + getQuery());
			break;
		case 8:
			dbc.executeUpdate("create index a on book(title)");
			dbc.executeUpdate("create index c on book(publishername)");
			setQuery("create index a on book(title);\ncreate index c on book(publishername);\n" + getQuery());
			break;
		case 9:
			dbc.executeUpdate("create index a on book(title, publishername)");
			setQuery("create index a on book(title, publishername);\n" + getQuery());
			break;
		}
		
//		System.out.println (getQuery ());
		
		long startTime, endTime;
		startTime = System.nanoTime ();
		ResultSet rs = dbc.executeQuery (variants.get(viewing));
		endTime = System.nanoTime ();
		
		setDuration ((endTime - startTime));
		
		while (rs.next ()) {
			table.addRowItem (new RowItem (rs.getString (1), rs.getString (2)));
		}
		
		notifyViews ();
		
	}
	
}
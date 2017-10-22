package model;

import java.sql.*;

public class Query2 extends QueryObject {
	String[] inputs;
	
	public Query2 () {
		super ();
		input = "2011-07-07, 2012-07-10";
		inputs = input.split(", ");
		initVariants ();
		table = new Table ("BookID", "DateOut", "DateReturned");
		setViewing (0);
	}
	
	private void initVariants () {
		variants.clear();
		
		variants.add("select BookID, DateOut, DateReturned"
				+ " \nfrom book_loans"
				+ " \nwhere DateOut >= '" + inputs[0] + "' AND" 
                + " \n\tDateReturned <= '" + inputs[1] + "' AND"
                + " \n\tDateOut <= DateReturned");
		variants.add("select b.BookID, b.DateOut, b.DateReturned \nfrom (select BookID, DateOut, DateReturned"
				+ "\n\t from book_loans" 
				+ "\n\t where DateOut >= '" + inputs[0] + "' AND"
                + "\n\t\t DateReturned <= '" + inputs[1] + "' AND "
                + "\n\t\t DateOut <= DateReturned) as b");
		variants.add("select * \nfrom"
				+ " (select BookID, DateOut, DateReturned"
				+ "\n\t from book_loans" 
				+ "\n\t where DateOut >= '" + inputs[0] + "' AND"
                + "\n\t\t DateReturned <= '" + inputs[1] + "' AND "
                + "\n\t\t DateOut <= DateReturned) as b");
		variants.add("select b.BookID, b.DateOut, b.DateReturned \nfrom"
				+ " (select *"
				+ "\n\t from book_loans" 
				+ "\n\t where DateOut >= '" + inputs[0] + "' AND"
                + "\n\t\t DateReturned <= '" + inputs[1] + "' AND "
                + "\n\t\t DateOut <= DateReturned) as b");
		variants.add("select b.BookID, b.DateOut, b.DateReturned \nfrom"
				+ " (select *"
				+ "\n\t from book_loans" 
				+ "\n\t where DateOut >= '" + inputs[0] + "' AND"
                + "\n\t\t DateReturned <= '" + inputs[1] + "' AND "
                + "\n\t\t DateOut <= DateReturned) as b");
		variants.add("select b.BookID, b.DateOut, b.DateReturned \nfrom"
				+ " (select *"
				+ "\n\t from book_loans" 
				+ "\n\t where DateOut >= '" + inputs[0] + "' AND"
                + "\n\t\t DateReturned <= '" + inputs[1] + "' AND "
                + "\n\t\t DateOut <= DateReturned) as b");
		variants.add("select b.BookID, b.DateOut, b.DateReturned \nfrom"
				+ " (select *"
				+ "\n\t from book_loans" 
				+ "\n\t where DateOut >= '" + inputs[0] + "' AND"
                + "\n\t\t DateReturned <= '" + inputs[1] + "' AND "
                + "\n\t\t DateOut <= DateReturned) as b");
		variants.add("select b.BookID, b.DateOut, b.DateReturned \nfrom"
				+ " (select *"
				+ "\n\t from book_loans" 
				+ "\n\t where DateOut >= '" + inputs[0] + "' AND"
                + "\n\t\t DateReturned <= '" + inputs[1] + "' AND "
                + "\n\t\t DateOut <= DateReturned) as b");
		variants.add("select b.BookID, b.DateOut, b.DateReturned \nfrom"
				+ " (select *"
				+ "\n\t from book_loans" 
				+ "\n\t where DateOut >= '" + inputs[0] + "' AND"
                + "\n\t\t DateReturned <= '" + inputs[1] + "' AND "
                + "\n\t\t DateOut <= DateReturned) as b");
		variants.add("select b.BookID, b.DateOut, b.DateReturned \nfrom"
				+ " (select *"
				+ "\n\t from book_loans" 
				+ "\n\t where DateOut >= '" + inputs[0] + "' AND"
                + "\n\t\t DateReturned <= '" + inputs[1] + "' AND "
                + "\n\t\t DateOut <= DateReturned) as b");
		variants.add("select b.BookID, b.DateOut, b.DateReturned \nfrom"
				+ " (select *"
				+ "\n\t from book_loans" 
				+ "\n\t where DateOut >= '" + inputs[0] + "' AND"
                + "\n\t\t DateReturned <= '" + inputs[1] + "' AND "
                + "\n\t\t DateOut <= DateReturned) as b");
	}
	
	public void prepareUpdates () throws Exception {
		table.removeAllRowItems ();
		
		initVariants();
		
		try{dbc.executeUpdate("drop index a on book_loans");} catch(Exception e){}
		try{dbc.executeUpdate("drop index b on book_loans");}catch(Exception e){}
		try{dbc.executeUpdate("drop index c on book_loans");}catch(Exception e){}
		
		setQuery(variants.get(viewing));
		
		switch(viewing){
		case 4: 
			dbc.executeUpdate("create index a on book_loans(BookID)");
			setQuery("create index a on book_loans(BookID);\n" + getQuery());
			break;
		case 5:
			dbc.executeUpdate("create index a on book_loans(DateOut)");
			setQuery("create index a on book_loans(DateOut);\n" + getQuery());
			break;
		case 6:
			dbc.executeUpdate("create index a on book_loans(DateReturned)");
			setQuery("create index a on book_loans(DateReturned);\n" + getQuery());
			break;
		case 7:
			dbc.executeUpdate("create index b on book_loans(DateOut)");
			dbc.executeUpdate("create index c on book_loans(DateReturned)");
			setQuery("create index b on book_loans(DateOut);\ncreate index c on book_loans(DateReturned);\n" + getQuery());
			break;
		case 8:
			dbc.executeUpdate("create index a on book_loans(BookID)");
			dbc.executeUpdate("create index b on book_loans(DateOut)");
			dbc.executeUpdate("create index c on book_loans(DateReturned)");
			setQuery("create index a on book_loans(BookID);\ncreate index b on book_loans(DateOut);\ncreate index c on book_loans(DateReturned);\n" + getQuery());
			break;
		case 9:
			dbc.executeUpdate("create index a on book_loans(BookId, DateOut, DateReturned)");
			setQuery("create index a on book_loans(BookID, DateOut, DateReturned);\n" + getQuery());
			break;
		case 10:
			dbc.executeUpdate("create index a on book_loans(DateOut, DateReturned)");
			setQuery("create index a on book_loans(DateOut, DateReturned);\n" + getQuery());
			break;
		}
		
		long startTime, endTime;
		startTime = System.nanoTime ();
		ResultSet rs = dbc.executeQuery (variants.get(viewing));
		endTime = System.nanoTime ();
		
		setDuration ((endTime - startTime));
		
		while (rs.next ()) {
			table.addRowItem (new RowItem (rs.getString (1), rs.getString (2), rs.getString(3)));
		}
		
		notifyViews ();
	}
	
}
package model;

import java.sql.*;

public class Query3 extends QueryObject {
	
	public Query3 () {
		super ();
		input = "Twodream";
		initVariants ();
		table = new Table ("BookID", "Title", "AuthorLastName");
		setViewing (0);
	}
	
	private void initVariants () {
		variants.clear();
		
		variants.add
			("SELECT b.BookID, b.Title, ba.AuthorLastName "
			+ "\nFROM book b, book_authors ba "
			+ "\nWHERE b.BookID = ba.BookID AND " 
			+ "\n\tba.AuthorLastName LIKE '%" + input + "%'");
		variants.add
		("SELECT b.BookID, b.Title, ba.AuthorLastName "
		+ "\nFROM book b, \n\t(SELECT BookID, AuthorLastName \n\t\tFROM book_authors \n\t\tWHERE AuthorLastName  "
		+ "LIKE '%" + input + "%') as ba " 
		+ "\nWHERE b.BookID = ba.BookID");
		variants.add
		("SELECT b.BookID, b.Title, ba.AuthorLastName "
		+ "\nFROM book b, book_authors ba "
		+ "\nWHERE b.BookID = ba.BookID AND " 
		+ "\n\tba.AuthorLastName LIKE '%" + input + "%'");
		variants.add
		("SELECT b.BookID, b.Title, ba.AuthorLastName "
		+ "\nFROM book b, book_authors ba "
		+ "\nWHERE b.BookID = ba.BookID AND " 
		+ "\n\tba.AuthorLastName LIKE '%" + input + "%'");
		variants.add
		("SELECT b.BookID, b.Title, ba.AuthorLastName "
		+ "\nFROM book b, book_authors ba "
		+ "\nWHERE b.BookID = ba.BookID AND " 
		+ "\n\tba.AuthorLastName LIKE '%" + input + "%'");
		variants.add
		("SELECT b.BookID, b.Title, ba.AuthorLastName "
		+ "\nFROM book b, \n\t(SELECT BookID, AuthorLastName \n\t\tFROM book_authors \n\t\tWHERE AuthorLastName  "
		+ "LIKE '%" + input + "%') as ba " 
		+ "\nWHERE b.BookID = ba.BookID");
		variants.add
		("SELECT b.BookID, b.Title, ba.AuthorLastName "
		+ "\nFROM book b, \n\t(SELECT BookID, AuthorLastName \n\t\tFROM book_authors \n\t\tWHERE AuthorLastName  "
		+ "LIKE '%" + input + "%') as ba " 
		+ "\nWHERE b.BookID = ba.BookID");
		variants.add
		("SELECT b.BookID, b.Title, ba.AuthorLastName "
		+ "\nFROM book b, \n\t(SELECT BookID, AuthorLastName \n\t\tFROM book_authors \n\t\tWHERE AuthorLastName  "
		+ "LIKE '%" + input + "%') as ba " 
		+ "\nWHERE b.BookID = ba.BookID");
	}
	
	public void prepareUpdates () throws Exception {
		table.removeAllRowItems ();
		
		initVariants();
		
		setQuery(variants.get(viewing));
		
		try{dbc.executeUpdate("drop index a on book");}catch(Exception e){}
		try{dbc.executeUpdate("drop index b on book_authors");}catch(Exception e){}
		
		switch(viewing){
		case 2:
			dbc.executeUpdate("create index a on book(title)");
			setQuery("create index a on book(title);\n" + getQuery());
			break;
		case 3:
			dbc.executeUpdate("create index b on book_authors(AuthorLastName)");
			setQuery("create index a on book(title);\n" + getQuery());
			break;
		case 4:
			dbc.executeUpdate("create index a on book(title)");
			dbc.executeUpdate("create index b on book_authors(AuthorLastName)");
			setQuery("create index a on book(title);\n" + getQuery());
			break;
		case 5:
			dbc.executeUpdate("create index a on book(title)");
			setQuery("create index a on book(title);\n" + getQuery());
			break;
		case 6:
			dbc.executeUpdate("create index b on book_authors(AuthorLastName)");
			setQuery("create index a on book(title);\n" + getQuery());
			break;
		case 7:
			dbc.executeUpdate("create index a on book(title)");
			dbc.executeUpdate("create index b on book_authors(AuthorLastName)");
			setQuery("create index a on book(title);\n" + getQuery());
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
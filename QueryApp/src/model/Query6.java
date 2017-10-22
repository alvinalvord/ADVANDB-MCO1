package model;

import java.sql.*;

public class Query6 extends QueryObject {
	
	public Query6 () {
		super ();
		initVariants ();
		table = new Table ("Borrower Last Name", "Card No", "Title");
		setViewing (0);
	}
	
	private void initVariants () {
		variants.add
		("SELECT BorrowerLName, b.CardNo, bo.Title\n"
				+ "FROM book_loans bl, book bo, \n\t(SELECT CardNo, BorrowerLName \n\tFROM borrower \n\tWHERE BorrowerLName LIKE '%" + input + "%') as b\n" 
				+ "WHERE bo.BookID = bl.BookID AND bl.CardNo = b.CardNo\n"
				+ "Group BY 3");
		variants.add
		("SELECT BorrowerLName, b.CardNo, bo.Title\n"
				+ "FROM book_loans bl, book bo, table1 b\n" 
				+ "WHERE bo.BookID = bl.BookID AND bl.CardNo = b.CardNo\n"
				+ "Group BY 3");
		variants.add
		("SELECT BorrowerLName, b.CardNo, bo.Title\n"
				+ "FROM book_loans bl, book bo, \n\t(SELECT CardNo, BorrowerLName \n\tFROM borrower \n\tWHERE BorrowerLName LIKE '%" + input + "%') as b\n" 
				+ "WHERE bo.BookID = bl.BookID AND bl.CardNo = b.CardNo\n"
				+ "Group BY 3");
		variants.add
		("SELECT BorrowerLName, b.CardNo, bo.Title\n"
				+ "FROM book_loans bl, book bo, table1 b\n" 
				+ "WHERE bo.BookID = bl.BookID AND bl.CardNo = b.CardNo\n"
				+ "Group BY 3");
		variants.add
		("SELECT BorrowerLName, b.CardNo, bo.Title\n"
				+ "FROM book_loans bl natural join book bo natural "
				+ "join((SELECT CardNo, BorrowerLName FROM borrower\n"
				+ "WHERE BorrowerLName LIKE '%" + input + "%') as b)\n"
				+ "Group BY 3");
		variants.add
		("SELECT BorrowerLName, b.CardNo, bo.Title\n"
				+ "FROM book_loans bl natural join book bo natural join table1 b\n"
				+ "GROUP BY 3");
		variants.add
		("SELECT BorrowerLName, b.CardNo, bo.Title\n"
				+ "FROM book_loans bl natural join book bo natural join table1 b\n"
				+ "GROUP BY 3");
	}
	
	public void prepareUpdates () throws Exception {
		table.removeAllRowItems ();
		
		setQuery(variants.get(viewing));
		
		try{dbc.executeUpdate("drop index a on borrower");}catch(Exception e){}
		try{dbc.executeUpdate("drop index c on book_loans");}catch(Exception e){}
		
		switch(viewing){
		case 1:
			dbc.executeUpdate("create temporary table if not exists table1 as " +
					"(SELECT CardNo, BorrowerLName FROM borrower WHERE BorrowerLName LIKE '%" + input + "%')");
			setQuery("CREATE temporary table if not exists table1 as\n\t(SELECT CardNo, BorrowerLName \n\tFROM borrower \n\tWHERE BorrowerLName LIKE '%" + input + "%');\n" + getQuery());
			break;
		case 2:
			dbc.executeUpdate("create index a on borrower(cardNo)");
			dbc.executeUpdate("create index c on book_loans(BookID)");
			setQuery("CREATE index a on borrower(cardNo);\nCREATE index b on book_loans(BookID);\n" + getQuery());
			break;
		case 3:
			dbc.executeUpdate("create temporary table if not exists table1 as " +
					"(SELECT CardNo, BorrowerLName FROM borrower WHERE BorrowerLName LIKE '%" + input + "%')");
			dbc.executeUpdate("create index a on borrower(cardNo)");
			dbc.executeUpdate("create index c on book_loans(BookID)");
			setQuery("CREATE index a on borrower(cardNo);\nCREATE index b on book_loans(BookID);\nCREATE temporary table if not exists table1 as\n\t(SELECT CardNo, BorrowerLName \n\tFROM borrower \n\tWHERE BorrowerLName LIKE '%" + input + "%');\n" + getQuery());
			break;
		case 5:
			dbc.executeUpdate("create temporary table if not exists table1 as " +
					"(SELECT CardNo, BorrowerLName FROM borrower WHERE BorrowerLName LIKE '%" + input + "%')");
			setQuery("CREATE temporary table if not exists table1 as\n\t(SELECT CardNo, BorrowerLName \n\tFROM borrower \n\tWHERE BorrowerLName = 'Hellgrove');\n" + getQuery());
			break;
		case 6:
			dbc.executeUpdate("create index a on borrower(borrowerLName)");
			dbc.executeUpdate("create temporary table if not exists table1 as " +
					"(SELECT CardNo, BorrowerLName FROM borrower WHERE BorrowerLName LIKE '%" + input + "%')");
			setQuery("CREATE index a on borrower(borrowerLName);\nCREATE temporary table if not exists table1 as\n\t(SELECT CardNo, BorrowerLName \n\tFROM borrower \n\tWHERE BorrowerLName LIKE '%" + input + "%');\n" + getQuery());
			break;
		}
		
		long startTime, endTime;
		startTime = System.nanoTime ();
		ResultSet rs = dbc.executeQuery (variants.get(viewing));
		endTime = System.nanoTime ();
		
		setDuration ((endTime - startTime));
		
		while (rs.next ()) {
			table.addRowItem (new RowItem (rs.getString (1), rs.getString (2), rs.getString (3)));
		}
		
		notifyViews ();
	}
	
}
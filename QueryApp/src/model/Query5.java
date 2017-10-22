package model;

import java.sql.*;

public class Query5 extends QueryObject {
	
	public Query5 () {
		super ();
		initVariants ();
		table = new Table ("Title", "PublisherName");
		setViewing (0);
	}
	
	private void initVariants () {
		variants.add("SELECT BorrowerLName, b.CardNo, BranchName, BranchAddress "
				+ "FROM book_loans bl, library_branch lb, (SELECT  "
				+ "CardNo, BorrowerLName FROM borrower WHERE  "
				+ "BorrowerLName = 'Hellgrove') as b  "
				+ "WHERE lb.BranchID = bl.BranchID AND bl.CardNo = b.CardNo");
		variants.add("SELECT BorrowerLName, b.CardNo, BranchName, BranchAddress "
				+ "FROM book_loans bl, library_branch lb, (SELECT  "
				+ "CardNo, BorrowerLName FROM borrower WHERE  "
				+ "BorrowerLName = 'Hellgrove') as b  "
				+ "WHERE lb.BranchID = bl.BranchID AND bl.CardNo = b.CardNo");
		variants.add("SELECT BorrowerLName, b.CardNo, BranchName, BranchAddress "
				+ "FROM book_loans bl, library_branch lb, (SELECT  "
				+ "CardNo, BorrowerLName FROM borrower WHERE  "
				+ "BorrowerLName = 'Hellgrove') as b  "
				+ "WHERE lb.BranchID = bl.BranchID AND bl.CardNo = b.CardNo");
		variants.add("SELECT BorrowerLName, b.CardNo, BranchName, BranchAddress "
				+ "FROM book_loans bl, library_branch lb, table1 as b "
				+ "WHERE lb.BranchID = bl.BranchID AND bl.CardNo = b.CardNo ");
	}
	
	public void prepareUpdates () throws Exception {
		table.removeAllRowItems ();
		
		setQuery(variants.get(viewing));
		
		try{dbc.executeUpdate("drop index a on book_loans");}catch(Exception e){};
		
		switch(viewing){
		case 1:
			dbc.executeUpdate("create index a on book_loans(CardNo)");
			setQuery("create index a on book_loans(CardNo);\n" + getQuery());
			break;
		case 2:
			dbc.executeUpdate("create index a on book_loans(BranchID)");
			setQuery("create index a on book_loans(CardNo);\n" + getQuery());
			break;
		}
		
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
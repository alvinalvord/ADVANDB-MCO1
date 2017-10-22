package model;

import java.sql.*;

public class Query5 extends QueryObject {
	
	public Query5 () {
		super ();
		input = "Hellgrove";
		initVariants ();
		table = new Table ("Borrower Last Name", "Card Number", "Branch Name", "Branch Address");
		setViewing (0);
	}
	
	private void initVariants () {
		variants.clear();
		variants.add("SELECT BorrowerLName, b.CardNo, BranchName, BranchAddress \nFROM book_loans bl, library_branch lb, \n\t(SELECT CardNo, BorrowerLName FROM borrower WHERE BorrowerLName LIKE '%" + input + "%') as b \nWHERE lb.BranchID = bl.BranchID AND bl.CardNo = b.CardNo \nGROUP BY 3");
		variants.add ("SELECT BorrowerLName, b.CardNo, BranchName, BranchAddress \nFROM book_loans bl, library_branch lb, \n\t(SELECT CardNo, BorrowerLName FROM borrower WHERE BorrowerLName LIKE '%" + input + "%') as b \nWHERE lb.BranchID = bl.BranchID AND bl.CardNo = b.CardNo \nGROUP BY 3");
		variants.add ("SELECT BorrowerLName, b.CardNo, BranchName, BranchAddress \nFROM book_loans bl, library_branch lb, table1 as b \nWHERE lb.BranchID = bl.BranchID AND bl.CardNo = b.CardNo \nGROUP BY 3");
		variants.add ("SELECT BorrowerLName, b.CardNo, BranchName, BranchAddress \nFROM book_loans bl, library_branch lb, table1 as b \nWHERE lb.BranchID = bl.BranchID AND bl.CardNo = b.CardNo \nGROUP BY 3");
		variants.add ("SELECT BorrowerLName, b.CardNo, BranchName, BranchAddress \nFROM (book_loans bl natural join library_branch lb) natural join \n\t((SELECT CardNo, BorrowerLName FROM borrower WHERE BorrowerLName LIKE '%" + input + "%') as b) \nGROUP BY 3");
		variants.add ("SELECT BorrowerLName, b.CardNo, BranchName, BranchAddress\nFROM (book_loans bl natural join library_branch lb) natural join \n\t((SELECT CardNo, BorrowerLName FROM borrower WHERE BorrowerLName LIKE '%" + input + "%') as b) \nGROUP BY 3");
		variants.add ("SELECT BorrowerLName, table1.CardNo, BranchName, BranchAddress \nFROM (book_loans bl natural join library_branch lb) natural join table1 \nGROUP BY 3");
	}
	
	public void prepareUpdates () throws Exception {
		table.removeAllRowItems ();
		
		initVariants();
		
		setQuery(variants.get(viewing));
		
		try{dbc.executeUpdate("drop index a on book_loans");}catch(Exception e){};
		try{dbc.executeUpdate("drop index b on book_loans");}catch(Exception e){};
		
		switch(viewing){
		case 1:
			dbc.executeUpdate("create index a on book_loans(CardNo)");
			dbc.executeUpdate("CREATE index b on book_loans(BranchID)");
			setQuery("create index a on book_loans(CardNo);\nCREATE index b on book_loans(BranchID);\n" + getQuery());
			break;
		case 2:
			dbc.executeUpdate ("create temporary table if not exists table1 as (SELECT CardNo, BorrowerLName FROM borrower WHERE BorrowerLName LIKE '%" + input + "%')");
			setQuery ("create temporary table if not exists table1 as (SELECT CardNo, BorrowerLName FROM borrower WHERE BorrowerLName LIKE '%" + input + "%');\n" + getQuery ());
			break;
		case 3:
			dbc.executeUpdate ("create temporary table if not exists table1 as (SELECT CardNo, BorrowerLName FROM borrower WHERE BorrowerLName LIKE '%" + input + "%')");
			dbc.executeUpdate ("create index a on book_loans(BranchID)");
			dbc.executeUpdate ("create index b on book_loans(CardNo)");
			setQuery ("create temporary table if not exists table1 as (SELECT CardNo, BorrowerLName FROM borrower WHERE BorrowerLName LIKE '%" + input + "%');\ncreate index a on book_loans(BranchID);\ncreate index b on book_loans(CardNo);\n" + getQuery ());
			break;
		case 5:
			dbc.executeUpdate ("create index a on book_loans(BranchID)");
			dbc.executeUpdate ("create index b on book_loans(CardNo)");
			setQuery ("create index a on book_loans(BranchID);\ncreate index b on book_loans(CardNo);\n"+getQuery ());
			break;
		case 6:
			dbc.executeUpdate ("create temporary table if not exists table1 as (SELECT CardNo, BorrowerLName FROM borrower WHERE BorrowerLName LIKE '%" + input + "%')");
			dbc.executeUpdate ("create index a on book_loans(BranchID)");
			dbc.executeUpdate ("create index b on book_loans(CardNo)");
			setQuery ("create temporary table if not exists table1 as (SELECT CardNo, BorrowerLName FROM borrower WHERE BorrowerLName = 'Hellgrove');\ncreate index a on book_loans(BranchID);\ncreate index b on book_loans(CardNo);\n"+getQuery ());
			break;
		}
		
		long startTime, endTime;
		startTime = System.nanoTime ();
		ResultSet rs = dbc.executeQuery (variants.get(viewing));
		endTime = System.nanoTime ();
		
		setDuration ((endTime - startTime));
		
		while (rs.next ()) {
			table.addRowItem (new RowItem (rs.getString (1), rs.getString (2), rs.getString (3), rs.getString (4)));
		}
		
		notifyViews ();
	}
	
}
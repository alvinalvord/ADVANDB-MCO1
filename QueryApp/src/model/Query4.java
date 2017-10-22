package model;

import java.sql.*;

public class Query4 extends QueryObject {
	
	public Query4 () {
		super ();
		input = "Bluekiller";
		initVariants ();
		table = new Table ("BorrowerLName", "CardNo", "numBor");
		setViewing (0);
	}
	
	private void initVariants () {
		variants.clear();
		variants.add("select b.BorrowerLName, b.CardNo, COUNT(*) as numBor " 
			+ "\nfrom borrower b, book_loans bl "
			+ "\nwhere b.CardNo = bl.CardNo AND "
			+ "\n\tb.BorrowerLName LIKE '%" + input + "%'");
		variants.add("select b.BorrowerLName, b.CardNo, COUNT(*) as numBor " 
				+ "\nfrom borrower b natural join book_loans bl "
				+ "\nwhere b.BorrowerLName LIKE '%" + input + "%'");
		variants.add("select BorrowerLName, CardNo, \n\t(select COUNT(*) " 
				+ "\n\t\tfrom borrower b natural join book_loans bl "
				+ "\n\t\twhere b.BorrowerLName LIKE '%" + input + "%')"
				+ "as numBor "
				+ "\nfrom borrower "
				+ "\nwhere BorrowerLName LIKE '%" + input + "%'");
		variants.add("select b.BorrowerLName, b.CardNo, COUNT(*) as numBor " 
				+ "\nfrom borrower b inner join book_loans bl on b.CardNo = bl.CardNo "
				+ "\nwhere b.BorrowerLName LIKE '%" + input + "%'");
		variants.add("select BorrowerLName, CardNo, COUNT(*) as NumBor "
				+ "\nfrom table1 "
				+ "\nwhere BorrowerLName LIKE '%" + input + "%'");
		variants.add("select b.BorrowerLName, b.CardNo, COUNT(*) as numBor " 
				+ "\nfrom borrower b, book_loans bl "
				+ "\nwhere b.CardNo = bl.CardNo AND "
				+ "\n\tb.BorrowerLName LIKE '%" + input + "%'");
		variants.add("select b.BorrowerLName, b.CardNo, COUNT(*) as numBor " 
				+ "\nfrom borrower b, book_loans bl "
				+ "\nwhere b.CardNo = bl.CardNo AND "
				+ "\n\tb.BorrowerLName LIKE '%" + input + "%'");
		variants.add("select b.BorrowerLName, b.CardNo, COUNT(*) as numBor " 
				+ "\nfrom borrower b, book_loans bl "
				+ "\nwhere b.CardNo = bl.CardNo AND "
				+ "\n\tb.BorrowerLName LIKE '%" + input + "%'");
		variants.add("select b.BorrowerLName, b.CardNo, COUNT(*) as numBor " 
				+ "\nfrom borrower b, book_loans bl "
				+ "\nwhere b.CardNo = bl.CardNo AND "
				+ "\n\tb.BorrowerLName LIKE '%" + input + "%'");
	}
	
	public void prepareUpdates () throws Exception {
		table.removeAllRowItems ();
		
		initVariants();
		
		setQuery(variants.get(viewing));
		
		try{dbc.executeUpdate("drop index a on borrower");}catch(Exception e){}
		try{dbc.executeUpdate("drop index b on borrower");}catch(Exception e){}
		
		switch(viewing){
		case 4:
			dbc.executeUpdate("create temporary table if not exists table1 as "
				+ "(select b.BorrowerLName, b.CardNo " 
				+ "from borrower b, book_loans bl "
				+ "where b.CardNo = bl.CardNo)");
			setQuery("create temporary table if not exists table1 as \n"
				+ "(select b.BorrowerLName, b.CardNo \n" 
				+ "from borrower b, book_loans bl \n"
				+ "where b.CardNo = bl.CardNo);\n" + getQuery());
			break;
		case 5: 
			dbc.executeUpdate("create index a on borrower(borrowerLName)");
			setQuery("create index a on borrower(borrowerLName);\n" + getQuery());
			break;
		case 6: 
			dbc.executeUpdate("create index a on borrower(cardNo)");
			setQuery("create index a on borrower(cardNo);\n" + getQuery());
			break;
		case 7: 
			dbc.executeUpdate("create index a on borrower(borrowerLName)");
			dbc.executeUpdate("create index b on borrower(cardNo)");
			setQuery("create index a on borrower(borrowerLName);\ncreate index b on borrower(cardNo);\n" + getQuery());
			break;
		case 8: 
			dbc.executeUpdate("create index a on borrower(cardNo, borrowerLName)");
			setQuery("create index a on borrower(borrowerLName);\n" + getQuery());
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
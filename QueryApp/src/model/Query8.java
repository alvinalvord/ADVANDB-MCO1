package model;

import java.sql.*;

public class Query8 extends QueryObject {
	
	public Query8 () {
		super ();
		initVariants ();
		table = new Table ("Borrower Last Name", "Book ID", "Author Last Name");
		setViewing (0);
	}
	
	private void initVariants () {
		variants.add
			("SELECT bo.BorrowerLName, b.BookID, ba.AuthorLastName \n" + 
			"FROM book b, book_authors ba, book_loans bl, " + 
				"library_branch lb, borrower bo, publisher p \n" + 
			"WHERE b.BookID = ba.BookID and " +
				"b.BookID = bl.BookID and \n\t" +
				"bl.BranchID = lb.BranchID and " +
				"bl.CardNo = bo.CardNo and \n\t" +
				"b.PublisherName = p.PublisherName and " +
				"lb.BranchAddress like '%New York%' and \n\t" +
				"p.Address like '%New York%' and " + 
				"bo.Address like '%New York%' \n" + 
				"GROUP BY 1");
		variants.add
			("SELECT bo.BorrowerLName, b.BookID, ba.AuthorLastName \n" + 
			"FROM (select bookid, PublisherName from book) as b, \n\t" +
			"(select bookid, authorlastname from book_authors) as ba, \n\t" +
			"(select bookid, branchid, cardno from book_loans) as bl, \n\t" +
			"(select branchid from library_branch where BranchAddress like '%New York%') as lb, \n\t" +
			"(select cardno, borrowerlname from borrower where address like '%New York%') as bo, \n\t" +
			"(select publishername, address from publisher where address like '%New York%') as p \n" +
			"WHERE b.BookID = ba.BookID and b.BookID = bl.BookID and \n\t" +
			"bl.BranchID = lb.BranchID and bl.CardNo = bo.CardNo and \n\t" + 
			"b.PublisherName = p.PublisherName \nGROUP BY 1");
		variants.add
			("SELECT bo.BorrowerLName, b.BookID, ba.AuthorLastName\nFROM \n\t(((((select bookid, PublisherName from book) as b natural join \n\t\t(select bookid, authorlastname from book_authors) as ba) natural join \n\t\t(select bookid, branchid, cardno from book_loans) as bl) natural join \n\t\t(select branchid from library_branch where BranchAddress like '%New York%') as lb) natural join \n\t\t(select cardno, borrowerlname from borrower where address like '%New York%') as bo) natural join \n\t\t(select publishername, address from publisher where address like '%New York%') as p\nGROUP BY 1");
			
		variants.add
			("SELECT bo.BorrowerLName, b.BookID, ba.AuthorLastName \n" + 
			"FROM book b, book_authors ba, book_loans bl, " + 
				"library_branch lb, borrower bo, publisher p \n" + 
			"WHERE b.BookID = ba.BookID and " +
				"b.BookID = bl.BookID and \n\t" +
				"bl.BranchID = lb.BranchID and " +
				"bl.CardNo = bo.CardNo and \n\t" +
				"b.PublisherName = p.PublisherName and " +
				"lb.BranchAddress like '%New York%' and \n\t" +
				"p.Address like '%New York%' and " + 
				"bo.Address like '%New York%' \n" + 
				"GROUP BY 1");
		variants.add
			("SELECT bo.BorrowerLName, b.BookID, ba.AuthorLastName \n" + 
			"FROM (select bookid, PublisherName from book) as b, \n\t" +
			"(select bookid, authorlastname from book_authors) as ba, \n\t" +
			"(select bookid, branchid, cardno from book_loans) as bl, \n\t" +
			"(select branchid from library_branch where BranchAddress like '%New York%') as lb, \n\t" +
			"(select cardno, borrowerlname from borrower where address like '%New York%') as bo, \n\t" +
			"(select publishername, address from publisher where address like '%New York%') as p \n" +
			"WHERE b.BookID = ba.BookID and b.BookID = bl.BookID and \n\t" +
			"bl.BranchID = lb.BranchID and bl.CardNo = bo.CardNo and \n\t" + 
			"b.PublisherName = p.PublisherName \nGROUP BY 1");
		variants.add
			("SELECT bo.BorrowerLName, b.BookID, ba.AuthorLastName\nFROM \n\t(((((select bookid, PublisherName from book) as b natural join \n\t\t(select bookid, authorlastname from book_authors) as ba) natural join \n\t\t(select bookid, branchid, cardno from book_loans) as bl) natural join \n\t\t(select branchid from library_branch where BranchAddress like '%New York%') as lb) natural join \n\t\t(select cardno, borrowerlname from borrower where address like '%New York%') as bo) natural join \n\t\t(select publishername, address from publisher where address like '%New York%') as p\nGROUP BY 1");
	}
	
	public void prepareUpdates () throws Exception {
		table.removeAllRowItems ();
		
		setQuery(variants.get(viewing));
		
		try{dbc.executeUpdate("drop index a on library_branch");}catch(Exception e){};
		try{dbc.executeUpdate("drop index b on borrower");}catch(Exception e){};
		try{dbc.executeUpdate("drop index c on publisher");}catch(Exception e){};
		
		switch (viewing) {
			case 3:
			case 4:
			case 5:
				dbc.executeUpdate("create index a on library_branch(branchaddress)");
				dbc.executeUpdate("create index b on borrower(address)");
				dbc.executeUpdate("create index c on publisher(address)");
				setQuery ("create index a on library_branch(branchaddress);\ncreate index b on borrower(address);\ncreate index c on publisher(address);\n" + getQuery ());
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
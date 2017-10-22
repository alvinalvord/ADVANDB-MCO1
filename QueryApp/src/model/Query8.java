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
			()
	}
	
	public void prepareUpdates () throws Exception {
		table.removeAllRowItems ();
		
		setQuery(variants.get(viewing));
		
		switch (viewing) {
			case 1:
				setQuery (getQuery ());
				break;
		}
		
		long startTime, endTime;
		startTime = System.nanoTime ();
		ResultSet rs = dbc.executeQuery (getQuery ());
		endTime = System.nanoTime ();
		
		setDuration ((endTime - startTime));
		
		while (rs.next ()) {
			table.addRowItem (new RowItem (rs.getString (1), rs.getString (2), rs.getString (3)));
		}
		
		notifyViews ();
	}
	
}
package model;

import java.sql.*;

public class Query7 extends QueryObject {
	
	public Query7 () {
		super ();
		initVariants ();
		table = new Table ("Book ID", "Title", "Branch Name", "Borrower Last Name");
		setViewing (0);
	}
	
	private void initVariants () {
		variants.add
			("select b.bookid, b.title, lb.branchname, bo.borrowerlname \n" + 
			"from book b, book_loans bl, library_branch lb, borrower bo \n" +
			"where b.bookid = bl.bookid and " + 
			"bl.BranchID = lb.BranchID and \n\t" +
			"bl.CardNo = bo.CardNo and " + 
			"b.PublisherName like '%Press%' and lb.branchname like '%Silver%'");
	}
	
	public void prepareUpdates () throws Exception {
		table.removeAllRowItems ();
		
		setQuery(variants.get(viewing));
		
		long startTime, endTime;
		startTime = System.nanoTime ();
		ResultSet rs = dbc.executeQuery (getQuery ());
		endTime = System.nanoTime ();
		
		setDuration ((endTime - startTime));
		
		while (rs.next ()) {
			table.addRowItem (new RowItem (rs.getString (1), rs.getString (2), rs.getString (3), rs.getString (4)));
		}
		
		notifyViews ();
	}
	
}
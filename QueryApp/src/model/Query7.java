package model;

import java.sql.*;

public class Query7 extends QueryObject {
	
	public Query7 () {
		super ();
		initVariants ();
		table = new Table ("Book ID", "Title", "Borrower Last Name");
		setViewing (0);
	}
	
	private void initVariants () {
		variants.add
			("select b.bookid, b.title, bo.borrowerlname \n" + 
			"from book b, book_loans bl, library_branch lb, borrower bo \n" +
			"where b.bookid = bl.bookid and " + 
			"bl.BranchID = lb.BranchID and \n\t" +
			"bl.CardNo = bo.CardNo and " + 
			"b.PublisherName like '%Press%' and lb.branchname like '%Silver%'");
		variants.add
			("SELECT b.bookid, b.title, bo.borrowerlname \n" + 
			"FROM (select BookID, title from book where PublisherName like '%Press%') as b, \n" +
			"book_loans bl, \n" + 
			"(select branchid, branchname from library_branch where branchname like '%Silver%') as lb, \n" + 
			"borrower bo \n" +
			"WHERE b.bookid = bl.bookid and bl.BranchID = lb.BranchID and \n" + 
			"bl.CardNo = bo.CardNo");
		variants.add ("SELECT b.bookid, b.title, bo.borrowerlname \nFROM ((book b natural join book_loans bl) natural join library_branch lb) natural join borrower bo \nWHERE b.PublisherName like '%Press%' and lb.branchname like '%Silver%'");
		variants.add ("SELECT b.bookid, b.title, bo.borrowerlname \nFROM \n\t(((select BookID, title from book where PublisherName like '%Press%') as b \n\t\tnatural join book_loans bl) natural join \n\t\t(select branchid, branchname from library_branch where branchname like '%Silver%') as lb) \n\t\tnatural join borrower bo");
		variants.add ("SELECT b.bookid, b.title, bo.borrowerlname \nFROM (select BookID, title from book where PublisherName like '%Press%') as b, book_loans bl, \n\t(select branchid, branchname from library_branch where branchname like '%Silver%') as lb, borrower bo \nWHERE b.bookid = bl.bookid and bl.BranchID = lb.BranchID and \n\tbl.CardNo = bo.CardNo");
		variants.add ("SELECT b.bookid, b.title, bo.borrowerlname \nFROM \n\t(((select BookID, title from book where PublisherName like '%Press%') as b \n\tnatural join book_loans bl) \n\tnatural join (select branchid, branchname from library_branch where branchname like '%Silver%') as lb) \n\tnatural join borrower bo");
		variants.add ("SELECT b.bookid, b.title, bo.borrowerlname \nFROM ((book b natural join book_loans bl) natural join library_branch lb) \n\tnatural join borrower bo \nWHERE b.PublisherName like '%Press%' and lb.branchname like '%Silver%'");
	}
	
	public void prepareUpdates () throws Exception {
		table.removeAllRowItems ();
		
		setQuery(variants.get(viewing));
		
		try{dbc.executeUpdate("drop index a on book");}catch(Exception e){};
		try{dbc.executeUpdate("drop index b on library_branch");}catch(Exception e){};
		
		switch (viewing) {
			case 6:
			case 4:
			case 5:
				dbc.executeUpdate("create index a on book(publishername)");
				dbc.executeUpdate("create index b on library_branch(branchname)");
				setQuery ("create index a on book(publishername);\ncreate index b on library_branch(branchname);\n" + getQuery ());
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
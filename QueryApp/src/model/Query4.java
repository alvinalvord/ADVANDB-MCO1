package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Query4 implements QueryFactory{

    String query;
    String from, select, where;

    public Query4(String selects, String where){
        //query = "SELECT * FROM book WHERE PublisherName = '" + where +"'";
        select = "SELECT " + selects + " ";
        from = "FROM borrower b, book_loans bl ";
        where =  "WHERE (b.CardNo  = bl.CardNo) AND b.BorrowerLName = '" + where +"'";
        query = select + from + where;
        System.out.println(query);

    }

    @Override
    public void generate() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {

        DBConnection db = new DBConnection();
        ResultSet rs = db.getConnection(query);

        while(rs.next()) {
            String id = rs.getString("numBor");
            System.out.println(id);
        }
        db.closeConnection();
    }

    @Override
    public void setQuery(String query){
        this.query = query;
    }

}

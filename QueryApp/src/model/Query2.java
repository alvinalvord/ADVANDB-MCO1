package model;

import java.sql.*;

public class Query2 implements QueryFactory{

    String query;
    String from, select, where;

    public Query2(String selects, String where1, String where2){
//        SELECT * FROM book_loans WHERE (REPLACE(DateOut, '-', '/') BETWEEN REPLACE('2011-07-07', '-', '/') AND REPLACE('2011-07-10', '-', '/'))AND (REPLACE(DateReturned, '-', '/') BETWEEN REPLACE('2011-07-07', '-', '/') AND REPLACE('2011-07-10', '-', '/'));

        select = "SELECT " + selects + " ";
        from = "FROM book_loans ";
        where = "WHERE DateOut >= '"+where1+"' AND DateReturned <= '"+where2+"' AND DateOut < DateReturned";
        /*where =  "WHERE (REPLACE(DateOut, '-', '/') BETWEEN REPLACE('"+ where1 +"', '-', '/') AND REPLACE('"+ where2 +"', '-', '/'))";
        where += "AND (REPLACE(DateReturned, '-', '/') BETWEEN REPLACE('"+ where1 +"', '-', '/') AND REPLACE('"+ where2 +"', '-', '/'));";*/
        query = select + from + where;
        System.out.println(query);
    }

    @Override
    public void generate() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {

        DBConnection db = new DBConnection();
        ResultSet rs = db.getConnection(query);

        System.out.println("Shit");
        while(rs.next()) {
            String id = rs.getString("CardNo");
            System.out.println(id);
        }
        db.closeConnection();

    }

    @Override
    public void setQuery(String query){
        this.query = query;
    }
}

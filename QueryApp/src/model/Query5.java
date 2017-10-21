package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Query5 implements QueryFactory{

    String query;
    String from, select, where;

    public Query5(String selects, String where){
        //query = "SELECT * FROM book WHERE PublisherName = '" + where +"'";
        select = "SELECT " + selects + " ";
        from = "FROM book ";
        where =  "WHERE PublisherName = '" + where +"'";
        query = select + from + where;
//        System.out.println(query);

    }

    @Override
    public void generate() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {

        DBConnection db = new DBConnection();
        ResultSet rs = db.getConnection(query);

        while(rs.next()) {
            String id = rs.getString("Title");
            System.out.println(id);
        }
        db.closeConnection();
    }

    @Override
    public void setQuery(String query){
        this.query = query;
    }


}

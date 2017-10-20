package model;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.sql.*;

public class Query1 implements QueryFactory{

    String query;

    @Override
    public void generate() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {

        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/advandb_mco1_librarydb", "root", "jgana1997");
        Statement st = con.createStatement();
        String sql = (query);
        ResultSet rs = st.executeQuery(sql);

        while(rs.next()) {
            String id = rs.getString("Title");
            System.out.println(id);
        }
        con.close();
    }

    @Override
    public void setQuery(String query){
        this.query = query;
    }

}

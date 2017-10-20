package model;

import java.sql.*;
import java.util.ArrayList;

public class DBConnection {

	 static Connection con=null;

	    public static Connection getConnection() throws SQLException {
	        if (con != null) return con;
	        // get db, user, pass from settings file
	        return getConnection("advandb_mco1_librarydb", "root", "jgana1997");

	    }

	    private static Connection getConnection(String db_name,String user_name,String password) throws SQLException {
	        try
	        {
	            Class.forName("com.mysql.jdbc.Driver");
	            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/"+db_name+"?user="+ user_name+"&password="+password);
	        }
	        catch(Exception e)
	        {
	            e.printStackTrace();
	        }

			DatabaseMetaData md = con.getMetaData();
			ResultSet rs = md.getTables(null, null, "%", null);
			while (rs.next()) {
				System.out.println(rs.getString(3));
			}

	        return con;
	    }

}

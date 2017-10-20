package model;

import java.sql.*;
import java.util.ArrayList;

public class DBConnection {

	private Connection con;

	public DBConnection(){
		con = null;
	}

	public ResultSet getConnection(String query) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		con = DriverManager.getConnection("jdbc:mysql://localhost/advandb_mco1_librarydb", "root", "jgana1997");

			Statement st = con.createStatement();
			String sql = (query);
			ResultSet rs = st.executeQuery(sql);
			return rs;

	}

	public void closeConnection() throws SQLException {
//		System.out.println("db closed");
		con.close();
	}
}

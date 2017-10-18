package model;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryModelView extends Model {


    public ArrayList<String> getTablesNames() throws SQLException {
        Connection dbCon = connectDB();
        ArrayList listTableNames = new ArrayList<String>();
        DatabaseMetaData md = dbCon.getMetaData();
        ResultSet rs = md.getTables(null, null, "%", null);
        while (rs.next()) {
            listTableNames.add(rs.getString(3));
        }
        return listTableNames;
    }

}

package model;

import java.sql.*;

public interface QueryFactory {

    String from = null;
    String select = null;
    String where = null;
    String having = null;
    String query = null;

    public void generate() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException;

    public void setQuery(String query);

}

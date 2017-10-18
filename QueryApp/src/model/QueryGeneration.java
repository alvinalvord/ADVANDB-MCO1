package model;

import java.sql.SQLException;
import java.util.ArrayList;

public class QueryGeneration extends Model {


    public void generateQuery(int type, String name){

        switch(type){
            case 1:
                break;
            case 2: break;
            case 3: break;
            case 4: break;
            case 5: break;
            case 6: break;
            case 7: break;
            case 8: break;
        }

    }

    public void oneTable(String from, ArrayList<String> select){
        
    }

    @Override
    public ArrayList<String> getTablesNames() throws SQLException {
        return null;
    }
}

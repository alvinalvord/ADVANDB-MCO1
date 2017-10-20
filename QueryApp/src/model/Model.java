package model;

import views.View;
import java.sql.*;
import java.sql.SQLException;
import java.util.*;

public abstract class Model {
	
	private ArrayList <View> views = new ArrayList <View> ();
	private Connection dbCon;
	
	public void attach (View v) {
		views.add (v);
	}

	public void detach (View v) {
		views.remove (v);
	}
	
	public void notifyViews () {
		for (View v: views)
			v.update ();
	}


	public abstract ArrayList<String> getTablesNames() throws SQLException;
}
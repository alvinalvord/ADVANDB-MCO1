

import controllers.ViewController;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.stage.Stage;
import model.*;

public class Driver extends Application {

	public void start (Stage mainStage) throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException {
	/*	Model model = new QueryModelView();
		model.getTablesNames();*/

		Query1 q1 = new Query1("*", "BBC Books");
		//q1.generate();

		Query2 q2 = new Query2("*", "2011-07-07", "2011-07-10");
		//q2.generate();

		Query3 q3 = new Query3("*", "Stormtail");
		//q3.generate();

		Query4 q4 = new Query4("*, COUNT(*) as numBor", "Bluekiller");
		q4.generate();


		new ViewController (mainStage);
	}

	public static void main (String[] args) throws SQLException {
		launch (args);
	}

}

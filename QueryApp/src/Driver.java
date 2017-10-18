

import controllers.ViewController;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.stage.Stage;
import model.DBConnection;
import model.Model;
import model.QueryGeneration;
import model.QueryModelView;

public class Driver extends Application {

	public void start (Stage mainStage) throws SQLException {
		Model model = new QueryModelView();
		model.getTablesNames();
		new ViewController (mainStage);
	}

	public static void main (String[] args) throws SQLException {
		launch (args);
	}

}

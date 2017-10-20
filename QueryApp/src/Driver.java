

import controllers.ViewController;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.stage.Stage;
import model.*;

public class Driver extends Application {

	public void start (Stage mainStage) throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException {
		Model model = new QueryModelView();
		model.getTablesNames();

		Query1 asdf = new Query1();
		asdf.setQuery("SELECT * FROM book;");
		asdf.generate();
		new ViewController (mainStage);
	}

	public static void main (String[] args) throws SQLException {
		launch (args);
	}

}

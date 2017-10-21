import model.*;
import controllers.*;
import javafx.application.*;
import javafx.stage.*;

public class Driver extends Application {
	
	public void start (Stage mainStage) {
		new ViewController (mainStage);
	}
	
	public static void main (String[] args) {
		String user = "root";
		String pass = "root";
		
		DBConnection.getConnection ()
			.setConnection ("advandb_mco1_librarydb", user, pass);
		launch (args);
	}
	
}
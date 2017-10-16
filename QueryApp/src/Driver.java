import controllers.ViewController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.DBConnection;

public class Driver extends Application {

	public void start (Stage mainStage) {
		new ViewController (mainStage);
	}

	public static void main (String[] args) {
		DBConnection dbCon = new DBConnection();
		if(dbCon.getConnection() == null){
			System.out.println("NULL");
		}else{
			System.out.println("sdjaflkfjkldasf");
		}
		launch (args);
	}

}

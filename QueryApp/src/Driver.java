import controllers.ViewController;
import javafx.application.*;
import javafx.stage.*;

public class Driver extends Application {
	
	public void start (Stage mainStage) {
		new ViewController (mainStage);
	}
	
	public static void main (String[] args) {
		launch (args);
	}
	
}
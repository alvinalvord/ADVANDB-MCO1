package views;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

public class QueryBuildView extends BorderPane implements View {
	
	public QueryBuildView () {
		// di ko alam kung san manggagaling yung numOfQueries so i put a constant lol
		setCenter(new ResultsView("Query Preview", "Duration: 0.01234s", 10));
	}
	
	public void update () {
		
	}
}
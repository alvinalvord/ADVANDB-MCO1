package views;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

public class QueryBuildView extends BorderPane implements View {
	
	public QueryBuildView () {
		setCenter(new ResultsView("hello", "hi"));
	}
	
	public void update () {
		
	}
	
}
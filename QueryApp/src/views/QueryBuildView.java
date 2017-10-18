package views;
import javafx.scene.layout.*;

public class QueryBuildView extends BorderPane implements View {
	
	public QueryBuildView () {
		
		this.setRight(new QuerySelectView());
		
		// di ko alam kung san manggagaling yung numOfQueries so i put a constant lol
		this.setCenter(new ResultsView("Query Preview", "Duration: 0.01234s", 10));
	}
	
	public void update () {
		
	}
}
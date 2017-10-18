package controllers;
import javafx.stage.*;
import views.QueryBuildView;

public class ViewController extends MainController {
	
	public ViewController (Stage mainStage) {
		super (mainStage);
	}
	
	protected void initViews () {
		views.add (new QueryBuildView ());
		// you have to call QuerySelectView's setQueries to set the queries to be displayed upon expansion
	}
	
	public void setScene (int n) {
		switch (n) {
			case 0:
				currentView = n;
				
			default:
				currentView = 0;
		}
		changeView ();
	}
	
}
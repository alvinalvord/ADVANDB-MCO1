package controllers;
import javafx.stage.*;
import views.QueryBuildView;

public class ViewController extends MainController {
	
	
	public ViewController (Stage mainStage) {
		super (mainStage);
		aaAAA();
		
	}
	
	public void aaAAA(){
		QueryBuildView q = (QueryBuildView) views.get(0);
		q.getQsv().setQueries(new String[]{"\n\n\n\n\nhi", "\n\nhee", "yyeee"});
//		q.getRv().setQueryPrev("\n\n\n\n\n\n21312\n1\n\n\n1");
		q.getRv().initQuerySelection(8);
		q.update();
	}
	
	protected void initViews () {
		views.add (new QueryBuildView (this));
		// you have to call QuerySelectView's setQueries to set the queries to be displayed upon expansion
	}
	
	public void setScene (int n) {
		switch (n) {
			case 0:
				currentView = n;
				break;
				
			default:
				currentView = 0;
		}
		changeView ();
	}
	
}
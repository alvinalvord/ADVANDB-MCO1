import javafx.stage.*;

public class ViewController extends MainController {
	
	public ViewController (Stage mainStage) {
		super (mainStage);
	}
	
	protected void initViews () {
		views.add (new QueryBuildView ());
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
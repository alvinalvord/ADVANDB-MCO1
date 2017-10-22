package controllers;
import javafx.stage.*;
import model.FactoryProducer;
import model.QueryObject;
import views.QueryBuildView;

public class ViewController extends MainController {
	
	
	public ViewController (Stage mainStage) {
		super (mainStage);
		setQueries();
		runQuery();
	}
	
	public void setQueries(){
		QueryBuildView q = (QueryBuildView) views.get(0);
		
		String[] s = new String[8];
		
		for(int i = 0; i < s.length; i++)
			s[i] = FactoryProducer.getFactory(1).getQueryObject((i + 1)).getQuery();
		
		q.getQsv().setQueries(s);
		
		q.getRv().initQuerySelection(2);
		
		q.update();
	}
	
	public void runQuery(){
		QueryBuildView q = (QueryBuildView) views.get(0);
		
		for(int i = 0; i < q.getQsv().getButtons().length; i++){
			int j = i;
			q.getQsv().getButtons()[i].setOnMouseClicked(e -> {
				QueryObject qo = FactoryProducer.getFactory(1).getQueryObject((j + 1));
				q.getRv().setQueryPrev(qo.getQuery());
				q.getRv().initQuerySelection(qo.countVariants());
				q.update();
			});
		}
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
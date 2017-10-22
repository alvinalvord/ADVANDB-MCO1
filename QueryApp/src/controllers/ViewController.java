package controllers;
import javafx.stage.*;
import model.FactoryProducer;
import model.QueryObject;
import views.QueryBuildView;

public class ViewController extends MainController {
	
	private int factoryNumber;
	
	public ViewController (Stage mainStage) {
		super (mainStage);
		factoryNumber = 1;
		setQueries();
		runQuery();
	}
	
	public void changeFactory (int n) {
		if (n >= 1 && n <=  8)
			factoryNumber = n;
		else
			factoryNumber = 1;
	}
	
	public int getFactoryNumber () {
		return factoryNumber;
	}
	
	public void setQueries(){
		QueryBuildView q = (QueryBuildView) views.get(0);
		
		String[] s = new String[8];
		
		for(int i = 0; i < s.length; i++) {
			QueryObject qo = FactoryProducer.getFactory(1)
				.getQueryObject((i + 1));
			s[i] = qo.getDefaultQuery();
			qo.attach (q);
		}
		q.getQsv().setQueries(s);
		
		q.getRv().initQuerySelection(FactoryProducer.getFactory(1)
				.getQueryObject(1).countVariants());
		
		updateViews();
	}
	
	public void runQuery(){
		QueryBuildView q = (QueryBuildView) views.get(0);
		
		for(int i = 0; i < q.getQsv().getButtons().length; i++){
			int j = i;
			q.getQsv().getButtons()[i].setOnMouseClicked(e -> {
				QueryObject qo = FactoryProducer.getFactory(1).getQueryObject((j + 1));
				q.getRv().setQueryPrev(qo.getQuery());
				q.getRv().initQuerySelection(qo.countVariants());
				updateViews();
			});
		}
	}
	
	public void updateViews(){
		QueryBuildView q = (QueryBuildView) views.get(0);
		q.update();
	}
	
	protected void initViews () {
		views.add (new QueryBuildView (this));
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
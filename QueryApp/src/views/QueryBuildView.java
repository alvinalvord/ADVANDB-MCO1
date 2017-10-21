package views;
import controllers.ViewController;
import javafx.scene.layout.*;

public class QueryBuildView extends BorderPane implements View {
	
	private QuerySelectView qsv;
	private ResultsView rv;
	private ViewController vc;
	
	public QueryBuildView (ViewController vc) {
		qsv = new QuerySelectView();
		rv = new ResultsView();
		
		this.vc = vc;
		
		this.setRight(qsv);
		
		this.setCenter(rv);
	}
	
	public QuerySelectView getQsv() {
		return qsv;
	}

	public void setQsv(QuerySelectView qsv) {
		this.qsv = qsv;
	}

	public ResultsView getRv() {
		return rv;
	}

	public void setRv(ResultsView rv) {
		this.rv = rv;
	}

	public void setQueryPreview(String preview){
		rv.setQueryPrev(preview);
		update();
	}
	
	public void setDuration(double duration){
		rv.setDuration(duration);
		update();
	}
	
	public void setNumOfQueries(int num){
		rv.initQuerySelection(num);
		update();
	}
	
	public void update () {
		qsv.update();
		rv.update();
	}
}
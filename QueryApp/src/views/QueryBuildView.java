package views;
import controllers.ViewController;
import javafx.scene.layout.*;

public class QueryBuildView extends BorderPane implements View {
	
	private QuerySelectView qsv;
	private ResultsView rv;
	private ViewController vc;
	
	public QueryBuildView (ViewController vc) {
		qsv = new QuerySelectView(vc);
		rv = new ResultsView(vc);
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
	}
	
	public void setDuration(long duration){
		rv.setDuration(duration);
	}
	
	public void setNumOfQueries(int num){
		rv.initQuerySelection(num);
	}
	
	public void update () {
		qsv.update();
		rv.update();
	}
}
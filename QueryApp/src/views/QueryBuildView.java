package views;
import controllers.ViewController;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;

public class QueryBuildView extends BorderPane implements View {
	
	private QuerySelectView qsv;
	private ResultsView rv;
	private FilterView fv;
	private ViewController vc;
	
	public QueryBuildView (ViewController vc) {
		qsv = new QuerySelectView(vc);
		rv = new ResultsView(vc);
		fv = new FilterView(vc);
		
		this.vc = vc;
		
		this.setTop(fv);
		this.setRight(qsv);
		
		ScrollPane sp = new ScrollPane();
		sp.setContent(rv);
		sp.setFitToWidth(true);
		sp.setMaxWidth(ResultsView.width);
		sp.getStylesheets().add("style.css");
		sp.getStyleClass().add("QuerySelectView");
		sp.setPadding(new Insets(20));
		sp.setVbarPolicy (ScrollPane.ScrollBarPolicy.ALWAYS);
		rv.setOnScroll(e -> {
			double deltaY = e.getDeltaY()*6; // *6 to make the scrolling a bit faster
			double width = sp.getContent().getBoundsInLocal().getWidth();
			double vvalue = sp.getVvalue();
			sp.setVvalue(vvalue + -deltaY/width);
		});
		
		this.setCenter(sp);
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
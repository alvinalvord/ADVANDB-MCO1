package views;

import com.sun.javafx.tk.FontMetrics;
import com.sun.javafx.tk.Toolkit;

import java.text.*;
import javafx.application.Platform;
import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.util.Callback;
import model.*;
import controllers.*;
import javafx.scene.control.*;
import javafx.scene.control.TableView.ResizeFeatures;
import javafx.scene.control.cell.PropertyValueFactory;

public class ResultsView extends VBox implements View{
	private final static double width = 960;
	private ChoiceBox<String> querySelection;
	private Label queryPrev;
	private Label duration;
	private TableView<RowItem> resultsTable;
	private QueryObject qo;
	private ViewController vc;
	
	public ResultsView(ViewController vc){
		super(20);

		this.vc = vc;
		
		this.setPadding(new Insets(20));
		this.setAlignment(Pos.TOP_RIGHT);
		this.setMaxWidth(width);
		this.setMinWidth(width);
		this.setFillWidth(true);
		
		this.getStylesheets().add("style.css");
		this.setId("bg");
		
		initQuerySelection(1);
		
		// create Query Preview box
		this.queryPrev = new Label("Query Preview");

		// create Duration display box
		this.duration = new Label("Duration: ");
		
		this.resultsTable = new TableView<RowItem>();
		
		initQueryPreview();
		initDuration();
		addChildren();
	}
	
	public ChoiceBox<String> getQuerySelection() {
		return querySelection;
	}

	public void setQuerySelection(ChoiceBox<String> querySelection) {
		this.querySelection = querySelection;
	}

	public Label getQueryPrev() {
		return queryPrev;
	}

	public void setQueryPrev(String queryPrev) {
		this.queryPrev.setText(queryPrev);
	}

	public Label getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		long ms = duration / 1000000;
		long ns = duration % 1000000;
		DecimalFormat df = new DecimalFormat ("000000");
		
		this.duration.setText("Duration: " + ms + "." + df.format(ns) + "ms");
	}

	public TableView<RowItem> getResultsTable() {
		return resultsTable;
	}

	public void setResultsTable(TableView<RowItem> resultsTable) {
		this.resultsTable = resultsTable;
	}
	
	public void initQuerySelection(int numOfQueries){		
		// create drop down
		this.querySelection = new ChoiceBox<String>();
		
		// add values to dropdown list
		for(int i = 1; i <= numOfQueries; i++){
			this.querySelection.getItems().add("Variant " + i);
		}
		
		querySelection.getSelectionModel ().selectedItemProperty ().addListener ( (a,b,c) ->
		{
			if (qo != null)
				qo.setViewing (querySelection.getSelectionModel ().getSelectedIndex ());
		}
		);
		
		// a e s t h e t i c c
		querySelection.setValue(querySelection.getItems().get(0));
	}

	private void initQueryPreview(){
		// a e s t h e t i c c
		queryPrev.setMaxWidth(Double.MAX_VALUE);
		queryPrev.setAlignment(Pos.CENTER_LEFT);
		queryPrev.setWrapText(true);
		queryPrev.setId("lbl");
	}

	private void initDuration(){
		// a e s t h e t i c c
		duration.setMaxWidth(Double.MAX_VALUE);
		duration.setId("lbl");
	}

	public void addChildren(){
		getChildren().add(querySelection);
		getChildren().addAll(queryPrev, duration);
		getChildren().add(resultsTable);
	}
	
	public void setTableItems(){
		resultsTable.getColumns ().clear ();
		
		qo = FactoryProducer.getFactory(1).getQueryObject(vc.getFactoryNumber ());
		
		ObservableList<RowItem> ol = qo.getTable().getRowItems();
		FontMetrics fontMetrics = Toolkit.getToolkit().getFontLoader().getFontMetrics(new Font("Roboto", 14));
		
		for(int i = 0; i < qo.getTable().getHeaders().length; i++){
			TableColumn <RowItem, String> tc = new TableColumn <>(qo.getTable().getHeaders()[i]);
			tc.setMinWidth(75);
			tc.setMaxWidth(Double.MAX_VALUE);
			tc.setCellValueFactory(new PropertyValueFactory <RowItem, String>("col" + i));
			
			resultsTable.getColumns().add(tc);
	        double textwidth = fontMetrics.computeStringWidth(qo.getTable().getHeaders()[i]);
	        resultsTable.getColumns().get(i).setPrefWidth(textwidth + 50);
		}
		resultsTable.setItems(ol);
	}

	private void updateLabels () {
		queryPrev.setText (qo.getQuery ());
		setDuration (qo.getDuration ());
	}
	
	public void update(){
		setTableItems();
		updateLabels ();
		getChildren().clear();
		addChildren();
	}
}

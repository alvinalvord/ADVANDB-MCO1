package views;

import com.sun.javafx.tk.FontMetrics;
import com.sun.javafx.tk.Toolkit;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.util.Callback;
import model.AbstractFactory;
import model.FactoryProducer;
import model.QueryObject;
import model.RowItem;
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
	
	public ResultsView(){
		super(20);

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

	public ResultsView(String queryPrev, double d, int numOfQueries){
		this();

		// create Query Preview box
		setQueryPrev(queryPrev);

		// create Duration display box
		setDuration(d);
		
		initQuerySelection(numOfQueries);
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

	public void setDuration(double duration) {
		this.duration.setText(this.duration.getText() + duration + "s");
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
		
		querySelection.getSelectionModel ().selectedItemProperty ().addListener ( (a,b,c) ->
		{
			if (qo != null)
				qo.setViewing (querySelection.getSelectionModel ().getSelectedIndex ());
		}
		);
		
		// add values to dropdown list
		for(int i = 1; i <= numOfQueries; i++){
			this.querySelection.getItems().add("Variant " + i);
		}

		// a e s t h e t i c c
		querySelection.setValue(querySelection.getItems().get(0));

	}

	private void initQueryPreview(){
		// a e s t h e t i c c
		queryPrev.setMaxWidth(Double.MAX_VALUE);
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
		
		if (qo != null)
			qo.detach (this);
		
		qo = FactoryProducer.getFactory(1).getQueryObject(1);
		qo.attach (this);
		
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
		duration.setText (qo.getDuration () + " ms");
	}
	
	private void checkSelected(){
		
	}
	
	public void update(){
		setTableItems();
		updateLabels ();
		getChildren().clear();
		addChildren();
	}
}

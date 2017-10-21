package views;

import javafx.geometry.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.control.*;

public class ResultsView extends VBox implements View{
	private final static double width = 960;
	private ChoiceBox<String> querySelection;
	private Label queryPrev;
	private Label duration;
	private TableView<String> resultsTable;
	
	public ResultsView(){
		super(20);

		this.setPadding(new Insets(20));
		this.setAlignment(Pos.TOP_RIGHT);
		this.setMaxWidth(width);
		this.setMinWidth(width);
		this.setFillWidth(true);
		
		this.getStylesheets().add("style.css");
		this.setId("bg");
				
		// create drop down
		this.querySelection = new ChoiceBox<String>();

		// create Query Preview box
		this.queryPrev = new Label("Query Preview");

		// create Duration display box
		this.duration = new Label("Duration: ");
		
		this.resultsTable = new TableView<String>();
		
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

	public TableView<String> getResultsTable() {
		return resultsTable;
	}

	public void setResultsTable(TableView<String> resultsTable) {
		this.resultsTable = resultsTable;
	}
	
	public void initQuerySelection(int numOfQueries){		
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
//		queryPrev.setPadding(new Insets(10, 20, 10, 20));
//		queryPrev.setBorder(new Border(new BorderStroke(Color.rgb(200, 200, 200), BorderStrokeStyle.SOLID, new CornerRadii(3), BorderWidths.DEFAULT)));
//		queryPrev.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));;
	}

	private void initDuration(){
		// a e s t h e t i c c
		duration.setMaxWidth(Double.MAX_VALUE);
		duration.setPadding(new Insets(10, 20, 10, 20));
		duration.setBorder(new Border(new BorderStroke(Color.rgb(200, 200, 200), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		duration.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));;
	}

	
	/*public void initTable(ObservableList<String> results, ArrayList<String> columnNames){
=======
	
	/*
	public void initTable(ObservableList<String> results, ArrayList<String> columnNames){
>>>>>>> 56e62f0311f9af6e3cdec5cfa6ecd23ff1b11136
		// populate table with data
		resultsTable.setItems(results);

		// create columns
		ArrayList<TableColumn> temp = new ArrayList<TableColumn>();

		for(String column : columnNames)
			temp.add(new TableColumn<String, String>(column).setCellValueFactory(column));

		resultsTable.getColumns().setAll(temp);
	}*/
	

	public void addChildren(){
		getChildren().add(querySelection);
		getChildren().addAll(queryPrev, duration);
		getChildren().add(resultsTable);
	}

	public void update(){
		getChildren().clear();
		addChildren();
	}
}

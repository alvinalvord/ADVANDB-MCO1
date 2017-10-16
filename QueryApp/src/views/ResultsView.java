package views;

import java.awt.Paint;

//import java.util.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.control.*;

public class ResultsView extends VBox implements View{
	private final static double width = 960;

	private ChoiceBox<String> querySelection;
	private Label queryPrev;
	private Label duration;
	private TableView<String> resultsTable;

	public ResultsView(String queryPrev, String duration, int numOfQueries){
		super(20);

		this.setPadding(new Insets(20));
		this.setAlignment(Pos.CENTER_LEFT);
		this.setBackground(new Background(new BackgroundFill(Color.rgb(233, 196, 255), CornerRadii.EMPTY, Insets.EMPTY)));
		this.setMaxWidth(width);
		this.setMinWidth(width);

		// create drop down
		this.querySelection = new ChoiceBox<String>();

		// create Query Preview box
		this.queryPrev = new Label(queryPrev);

		// create Duration display box
		this.duration = new Label(duration);

		// create Results Table
		resultsTable = new TableView<String>();

		initQuerySelection(numOfQueries);
		initQueryPreview();
		initDuration();
		addChildren();
	}

	private void initQuerySelection(int numOfQueries){
		// add values to dropdown list
		for(int i = 1; i <= numOfQueries; i++)
			this.querySelection.getItems().add("Query " + i);

		// a e s t h e t i c c
		querySelection.setValue(querySelection.getItems().get(0));

	}

	private void initQueryPreview(){
		// a e s t h e t i c c
		queryPrev.setPrefWidth(width);
		queryPrev.setPadding(new Insets(10, 20, 10, 20));
		queryPrev.setBorder(new Border(new BorderStroke(Color.rgb(200, 200, 200), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		queryPrev.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));;
	}

	private void initDuration(){
		// a e s t h e t i c c
		duration.setPrefWidth(width);
		duration.setPadding(new Insets(10, 20, 10, 20));
		duration.setBorder(new Border(new BorderStroke(Color.rgb(200, 200, 200), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		duration.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));;
	}

	
	/*public void initTable(ObservableList<String> results, ArrayList<String> columnNames){
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

	}
}

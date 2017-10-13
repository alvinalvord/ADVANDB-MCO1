package views;

import java.util.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

public class ResultsView extends VBox implements View{
	private ChoiceBox<String> querySelection;
	private Label queryPrev;
	private Label duration;
	private TableView<String> resultsTable;
	
	public ResultsView(String queryPrev, String duration){
		super(20);
		
		setPadding(new Insets(20));
		setAlignment(Pos.CENTER_LEFT);
		
		// create drop down
		this.querySelection = new ChoiceBox<String>();
		
		// create Query Preview box
		this.queryPrev = new Label(queryPrev);
		
		// create Duration display box
		this.duration = new Label(duration);
		
		// create Results Table 
		resultsTable = new TableView<String>();
		addChildren();
	}
	
	/*
	public void initTable(ObservableList<String> results, ArrayList<String> columnNames){
		// populate table with data
		resultsTable.setItems(results);
		
		// create columns
		ArrayList<TableColumn> temp = new ArrayList<TableColumn>();
		
		for(String column : columnNames)
			temp.add(new TableColumn<String, String>(column).setCellValueFactory(column));
		
		resultsTable.getColumns().setAll(temp);
	}
	*/
	
	public void addChildren(){
		getChildren().addAll(queryPrev, duration);
		getChildren().add(resultsTable);
	}
	
	public void update(){
		
	}
}
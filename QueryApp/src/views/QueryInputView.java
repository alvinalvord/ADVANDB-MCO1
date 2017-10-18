package views;

import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class QueryInputView extends GridPane implements View {
	
	public Label[] labels;
	public TextArea[] conditionInputs;
	
	public ArrayList<ComboBox<String>> dropdowns;
	
	public Button[] buttons;
	
	public QueryInputView(){
		super();
		
		initElements();
		
		addToLayout();
		
		this.setMinWidth(400);
		this.setMaxWidth(400);
		this.setPadding(new Insets(20));
		this.setHgap(15);
		this.setVgap(10);
	}
	
	private void initElements(){
		initLabels();
		initTextAreas();
		initButtons();
		initComboBoxes();
	}
	
	private void initLabels(){
		String[] text = new String[]{"FROM", "SELECT", "WHERE", "GROUP BY", "HAVING", "ORDER BY"};
		labels = new Label[6];
		
		for(int i = 0; i < labels.length; i++){
			labels[i] = new Label(text[i]);
		}
	}
	
	private void initTextAreas(){
		conditionInputs = new TextArea[2];
		
		for(int i = 0; i < conditionInputs.length; i++){
			conditionInputs[i] = new TextArea();
			conditionInputs[i].setMaxHeight(100);
		}
	}
	
	private void initButtons(){
		String[] text = new String[]{"+", "SQ", "+", "SQ", "P", "+", "+"};
		buttons = new Button[7];
		
		for(int i = 0; i < buttons.length; i++){
			buttons[i] = new Button(text[i]);
			buttons[i].setBackground(new Background(new BackgroundFill(Color.AZURE, CornerRadii.EMPTY, new Insets(15))));
		}
	}
	
	private void initComboBoxes(){
		dropdowns = new ArrayList<ComboBox<String>>();
		
		for(int i = 0; i < 4; i++){
			dropdowns.add(new ComboBox<String>());
			dropdowns.get(i).setMinWidth(75);
		}
	}
	
	public void addValues(ArrayList<String> values, int index){
		dropdowns.get(index).getItems().addAll(values);
	}
	
	public void addToLayout(){
		for(int i = 0; i < labels.length; i++)
			this.add(labels[i], 0, i*2);
		
		this.addRow(0, buttons[0]);
		this.addRow(0, buttons[1]);
		this.addRow(2, buttons[2]);
		this.addRow(2, buttons[3]);
		this.addRow(2, buttons[4]);
		this.addRow(6, buttons[5]);
		this.addRow(10, buttons[6]);
		
		this.add(conditionInputs[0], 0, 5, 5, 1);
		this.add(conditionInputs[1], 0, 9, 5, 1);

		this.addRow(1, dropdowns.get(0));
		this.addRow(3, dropdowns.get(1));
		this.addRow(7, dropdowns.get(2));
		this.addRow(11, dropdowns.get(3));
	}
	
	@Override
	public void update() {
		
	}
	
}

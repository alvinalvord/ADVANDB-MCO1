package views;

import controllers.ViewController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import model.FactoryProducer;

public class FilterView extends HBox implements View {
	
	private Label filterLbl;
	private TextField inputTxtFld;
	private Button runBtn;
	
	private ViewController vc;
	
	public FilterView(ViewController vc){
		super(20);
		this.vc = vc;
		initChildren();
		this.getStylesheets().add("style.css");
		this.setId("topbar");
		
		this.setOnKeyPressed(e -> {
			if(e.getCode() == KeyCode.ENTER){
				runBtn.fire();
			}
		});
	}
	
	private void initChildren(){
		initLabel();
		initTextField();
		initButton();
		
		this.getChildren().addAll(filterLbl, inputTxtFld, runBtn);
	}
	
	private void initLabel(){
		filterLbl = new Label("Filter");
		filterLbl.setTextFill(Color.PEACHPUFF);
	}
	
	private void initTextField(){
		inputTxtFld = new TextField();
		inputTxtFld.setMinWidth(125);
	}
	
	private void initButton(){
		runBtn = new Button("Run");
		
		runBtn.setOnAction(e -> {
			if(inputTxtFld.getText() != ""){
				FactoryProducer.getFactory(1).getQueryObject(vc.getFactoryNumber()).setInput(inputTxtFld.getText());
				vc.updateViews();
			}
		});
	}

	@Override
	public void update() {
		this.getChildren().clear();
		this.getChildren().addAll(filterLbl, inputTxtFld, runBtn);
	}
	
}

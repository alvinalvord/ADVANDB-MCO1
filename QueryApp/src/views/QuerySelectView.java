package views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class QuerySelectView extends ScrollPane implements View{
	
	private VBox vb;
	private ToggleButton[] buttons;
	private String[] queries;
	
	public QuerySelectView(){
		
		super();
		
		this.setMinWidth(400);
		this.setMaxWidth(400);
		
		this.setFitToWidth(true);
		
		initLayout();
		
		this.setContent(vb);
		
		this.getStylesheets().add("style.css");
		
		this.getStyleClass().add("QuerySelectView");
		
		this.setPadding(new Insets(0));
	}
	
	public void setQueries(String[] queries){
		this.queries = queries;
	}
	
	private void initLayout(){
		vb = new VBox(20);
		
		vb.setPadding(new Insets(20));
		vb.setAlignment(Pos.TOP_CENTER);
		
		vb.setOnScroll(e -> {
			double deltaY = e.getDeltaY()*6; // *6 to make the scrolling a bit faster
			double width = this.getContent().getBoundsInLocal().getWidth();
			double vvalue = this.getVvalue();
			this.setVvalue(vvalue + -deltaY/width);
		});
		vb.setId("selection");
		
		initButtons();
		
		vb.getChildren().addAll(buttons);
	}
	
	private void initButtons(){
		buttons = new ToggleButton[8];
		ToggleGroup tg = new ToggleGroup();
		
		for(int i = 0; i < buttons.length; i++){
			int j = i;
			buttons[i] = new ToggleButton("Query " + (i+1));
			buttons[i].setMinSize(320, 75);
			buttons[i].setMaxHeight(Double.MAX_VALUE);
			buttons[i].getStylesheets().add("style.css");
			
			buttons[i].setOnMouseEntered(e -> {
				buttons[j].setText(buttons[j].getText() + "\n\n" + queries[j]);
			});
			
			buttons[i].setOnMouseExited(e -> {
				buttons[j].setText("Query " + (j + 1));
			});

			buttons[i].setOnMouseClicked(e -> update());
		}
		
		tg.getToggles().addAll(buttons);
	}

	// pag may kailangan ichange sa view
	@Override
	public void update() {
		for(int j = 0; j < buttons.length; j++){
			if(buttons[j].isSelected()){
				buttons[j].setText(buttons[j].getText() + "\n\n" + queries[j]);
			}
			else{
				buttons[j].setText("Query " + (j + 1));
			}
		}
		
		vb.getChildren().clear();
		vb.getChildren().addAll(buttons);
	}
}

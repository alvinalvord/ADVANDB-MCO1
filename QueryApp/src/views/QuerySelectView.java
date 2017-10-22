package views;

import controllers.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class QuerySelectView extends ScrollPane implements View {
	
	private ViewController vc;
	private VBox vb;
	private ToggleButton[] buttons;
	private String[] queries;
	
	public QuerySelectView(ViewController vc){
		super();
		
		this.vc = vc;
		this.setMinWidth(400);
		this.setMaxWidth(400);
		this.setFitToWidth(true);
		
		initLayout();
		this.setContent(vb);
		this.getStylesheets().add("style.css");
		this.getStyleClass().add("QuerySelectView");
		this.setPadding(new Insets(0));
		setVbarPolicy (ScrollPane.ScrollBarPolicy.ALWAYS);
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
			final int j = i;
			buttons[i] = new ToggleButton("Query " + (i+1));
			buttons[i].setMinSize(320, 75);
			buttons[i].setMaxHeight(Double.MAX_VALUE);
			buttons[i].setMaxWidth (320);
			buttons[i].getStylesheets().add("style.css");
			buttons[i].setWrapText(true);
			
			buttons[i].setOnMouseEntered(e -> {
				if(!buttons[j].isSelected())
					buttons[j].setText(buttons[j].getText() + "\n\n" + queries[j]);
			});
			
			buttons[i].setOnMouseExited(e -> {
				if(!buttons[j].isSelected())
					buttons[j].setText("Query " + (j + 1));
			});
			
			buttons[i].setOnAction (e -> {
				buttons[j].setSelected (true);
				vc.changeFactory (j + 1);
			});
		}
		
		tg.getToggles().addAll(buttons);
		
	}

	public VBox getVb() {
		return vb;
	}

	public void setVb(VBox vb) {
		this.vb = vb;
	}

	public ToggleButton[] getButtons() {
		return buttons;
	}

	public void setButtons(ToggleButton[] buttons) {
		this.buttons = buttons;
	}

	public String[] getQueries() {
		return queries;
	}

	// pag may kailangan ichange sa view
	@Override
	public void update() {
		for(int j = 0; j < buttons.length; j++){
			if(!buttons[j].isSelected())
				buttons[j].setText("Query " + (j + 1));
		}
		
		vb.getChildren().clear();
		vb.getChildren().addAll(buttons);
	}
}

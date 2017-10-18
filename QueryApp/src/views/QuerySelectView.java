package views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class QuerySelectView extends ScrollPane implements View{
	
	private VBox vb;
	private Button[] buttons;
	
	public QuerySelectView(){
		
		super();
		
		this.setMinWidth(400);
		this.setMaxWidth(400);
		
		this.setFitToWidth(true);
		
		initLayout();
		
		this.setContent(vb);
	}
	
	private void initLayout(){
		vb = new VBox(20);
		
		vb.setPadding(new Insets(20));
		vb.setAlignment(Pos.TOP_CENTER);
		vb.setBackground(new Background(new BackgroundFill(Color.rgb(233, 196, 255), CornerRadii.EMPTY, Insets.EMPTY)));
		
		initButtons();
		
		vb.getChildren().addAll(buttons);
	}
	
	private void initButtons(){
		buttons = new Button[8];
		
		for(int i = 0; i < buttons.length; i++){
			buttons[i] = new Button("Query " + (i+1));
			buttons[i].setAlignment(Pos.CENTER);
			buttons[i].setMinSize(320, 75);
			buttons[i].setBackground(new Background(new BackgroundFill(Color.LIGHTCORAL, new CornerRadii(2.5), new Insets(10))));
			buttons[i].setTextFill(Color.WHITE);
			
			// hover stuff
			int j = i;
			buttons[i].setOnMouseEntered(e -> {
				buttons[j].setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(2.5), new Insets(10))));
				buttons[j].setTextFill(Color.BLACK);
			});
			
			buttons[i].setOnMouseExited(e -> {
				buttons[j].setBackground(new Background(new BackgroundFill(Color.LIGHTCORAL, new CornerRadii(2.5), new Insets(10))));
				buttons[j].setTextFill(Color.WHITE);
			});
		}
	}

	// pag may kailangan ichange sa view
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}

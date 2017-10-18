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
	private String[] queries;
	
	private int selected = -1;
	private boolean[] opened;
	
	public QuerySelectView(){
		
		super();
		
		this.setMinWidth(400);
		this.setMaxWidth(400);
		
		this.setFitToWidth(true);
		
		initLayout();
		initOpened();
		
		this.setContent(vb);
	}
	
	public void setQueries(String[] queries){
		this.queries = queries;
	}
	
	private void initOpened(){
		opened = new boolean[8];
		
		for(int i = 0; i < opened.length; i++){
			opened[i] = false;
		}
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
			buttons[i].setMaxHeight(Double.MAX_VALUE);
			buttons[i].setBackground(new Background(new BackgroundFill(Color.LIGHTCORAL, new CornerRadii(2.5), new Insets(10))));
			buttons[i].setTextFill(Color.WHITE);
			buttons[i].setStyle("-fx-padding: 20px");
			
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
			
			buttons[i].setOnMouseClicked(e -> {
				opened[j] = !opened[j];
				select(j);
				update();
			});
		}
	}

	// pag may kailangan ichange sa view
	@Override
	public void update() {
		for(int j = 0; j < opened.length; j++){
			if(j != selected && opened[j])
				opened[j] = false;
		}
		
		for(int j = 0; j < buttons.length; j++){
			if(opened[j]){
				buttons[j].setText(buttons[j].getText() + "\n\n" + queries[j]);
			}
			else{
				buttons[j].setText("Query " + (j + 1));
			}
		}
		
		vb.getChildren().clear();
		vb.getChildren().addAll(buttons);
	}
	
	public void select(int index){
		this.selected = index;
	}

}

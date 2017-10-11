import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import java.util.*;

public abstract class MainController {
	
	protected Stage mainStage;
	protected Scene scene;
	protected int currentView;
	protected List <View> views;
	
	public MainController (Stage stage) {
		this.mainStage = stage;
		mainStage.setMinWidth (1280);
		mainStage.setMinHeight (720);
		
		scene = new Scene (new Group (), mainStage.getWidth (), mainStage.getHeight ());
		
		views = new ArrayList<View> ();
		initViews ();
		
		setScene (0);
		
		stage.setScene (scene);
		stage.show ();
	}
	
	protected abstract void initViews ();
	
	public abstract void setScene (int n);
	
	protected void changeView () {
		scene.setRoot ((Parent)views.get(currentView));
	}
	
	public Stage getStage () {
		return mainStage;
	}
	
}
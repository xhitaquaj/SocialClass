package gui;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.Region;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import gui.Friend;
import gui.FriendsList;
import javafx.scene.paint.Color;

public class JavaFXClient extends Application {
    final Button bt = new Button("Post");
    final TextField txt = new TextField();
    final ScrollBar sc = new ScrollBar();
    final ScrollBar sc2 = new ScrollBar();
    final ScrollBar sc3 = new ScrollBar();
    final Image[] images = new Image[5];
    final ImageView[] pics = new ImageView[5];
    final VBox vb = new VBox();//champ
    final VBox vb2 = new VBox();
    final DropShadow shadow = new DropShadow();
 
    @Override
    public void start(final Stage stage) {
        Group root = new Group();
        final Scene scene = new Scene(root,640,480);//fenetre
        scene.setFill(Color.BLACK);
        stage.setScene(scene);
        stage.setTitle("Scrollbar");
     	FriendsList friendlist=new FriendsList(stage, root);
	Friend friend=new Friend(friendlist.stack());
	Friend f=new Friend(friendlist.stack());
	friendlist.addFriend(friend);
	friendlist.addFriend(f);
	Timeline t = new Timeline(friendlist,root);
	root.getChildren().addAll(friendlist.stack(), friendlist.scroll(),t.field());

	stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		public void handle(WindowEvent we) {
		    System.out.println("Stage is closing");
		    stage.close();
		}
	    });        
    
	stage.show();
	
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}

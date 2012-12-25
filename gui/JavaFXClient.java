package gui;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.Region;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import gui.Friend;
import gui.FriendsList;
import javafx.scene.paint.Color;

public class JavaFXClient extends Application {
 
    @Override
    public void start(final Stage stage) {
        VBox root = new VBox();

	//canevas principal
	HBox hbox = new HBox(20);
	hbox.setPadding(new Insets(20));
	//hbox.setPrefHeight(stage.getHeight());
	

        Scene scene = new Scene(root,640,480);//fenetre
        scene.setFill(Color.ORANGE);
        stage.setScene(scene);
        stage.setTitle("SocialClass");

	FriendsList friendlist=new FriendsList(stage, hbox);
	Friend friend = new Friend(friendlist.stack(),"Thomas","occupe");
	Friend fr = new Friend(friendlist.stack(),"Jean" ,"connecte");
	Friend f = new Friend(friendlist.stack(),"Jean","MrStickman.jpg" ,"connecte");
	friendlist.addFriend(f);
	friendlist.addFriend(friend);
	friendlist.addFriend(fr);
	Timeline t = new Timeline(hbox);
	Profile p = new Profile(fr, hbox);
	Profile pw = new Profile(f, hbox);
	root.getChildren().add(hbox);
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

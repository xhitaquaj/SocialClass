package gui;

import javafx.util.Duration;
import javafx.animation.ScaleTransition;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.paint.Color;
import javafx.scene.Group;

public class Timeline extends Parent {

    private VBox field;
    private Button post_button;
    private TextField post_entry;

    public Timeline(FriendsList l,Group root){
		
	initializePostEntry();
	initializePostButton();
	initializeField();
	loadContent(root,l);
	Bindings.adjust(l.stack(),field); 
    }
    public VBox field(){
	return field;
    }
    private void initializeField(){
	field = new VBox();
    }
    private void initializePostEntry(){
	post_entry = new TextField();
	
    }
    private void setTransition(Label l){
	ScaleTransition transition = new ScaleTransition(Duration.millis(2000),l);
	transition.setFromX(0.5);
	transition.setFromY(0.5);
	transition.setToX(1.5);
	transition.setToY(1.5);
	transition.setCycleCount(1);
	transition.setAutoReverse(true);
	transition.play();
    }
    private void initializePostButton(){
	post_button = new Button("Post");
	post_button.setStyle("-fx-background-color: cyan;");
	post_button.setOnAction(new EventHandler<ActionEvent>(){
		@Override public void handle(ActionEvent e){
		    Label l = new Label(post_entry.getCharacters().toString());
		    l.setMinWidth(100);
		    l.setTextFill(Color.GREEN);
		    //l.setStyle("-fx-border-color:white;");
		    field.getChildren().add(l);
		    setTransition(l);
		}
	    });
    }
    private void loadContent(Group root,FriendsList l){
	post_button.setLayoutY(20);
	post_entry.setLayoutY(0);
	post_button.setLayoutX(200);
	post_entry.setLayoutX(200);
	root.getChildren().add(post_entry);
	root.getChildren().add(post_button);
    }
}

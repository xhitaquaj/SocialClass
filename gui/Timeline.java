package gui;

import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.paint.Color;

public class Timeline extends Parent {

    private VBox field;
    private Button post_button;
    private TextField post_entry;

    public Timeline(FriendsList l){
	initializeField();	
	initializePostEntry();
	initializePostButton();
	loadContent();
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
    private void initializePostButton(){
	post_button = new Button("Post");
	post_button.setStyle("-fx-background-color: cyan;");
	post_button.setOnAction(new EventHandler<ActionEvent>(){
		@Override public void handle(ActionEvent e){
		    Label l = new Label(post_entry.getCharacters().toString());
		    l.setTextFill(Color.GREEN);
		    field.getChildren().add(l);
		}
	    });
    }
    private void loadContent(){
	field.getChildren().add(post_entry);
	field.getChildren().add(post_button);
    }
}

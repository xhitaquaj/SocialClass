package gui;

import javafx.scene.Group;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.Parent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import java.util.*;
import javafx.scene.effect.Reflection;
import javafx.scene.paint.Color;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class FriendsList extends Parent{
    
    private static VBox stack;
    private ScrollBar scroll;
    private static List<Friend> friends;
    private Button addButton;

    public FriendsList(Stage s, HBox hbox){
	friends = new ArrayList<Friend>();
	initializeStack();
	initializeScroll();
	initializeAddButton();
	loadContent(hbox);
	Bindings.adjustWidth(s, addButton, stack);
	//Bindings.adjustHeight(s, scroll, stack);
	//Bindings.bindScroll(scroll,stack);
    }

    
    private void initializeAddButton(){
	addButton = new Button("+ ajouter ami");
	addButton.setStyle("-fx-background-color: cyan;");
	addButton.setEffect(new Reflection());
	addButton.setOnAction(new EventHandler<ActionEvent>(){
		@Override public void handle(ActionEvent e){
		    AddFriendPopup.pop();
		}
	    });
    }

    private void initializeStack(){
	stack = new VBox(10);
	//stack.setLayoutY(50);
	//stack.setLayoutX(20);
    }

    private void initializeScroll(){
	scroll = new ScrollBar();
	//VBox.setVgrow(stack, Priority.ALWAYS);
        scroll.setOrientation(Orientation.VERTICAL);
        //scroll.setMaxHeight(1000);
    }

    public static VBox stack(){
	return stack;
    }

    private void adjustPosition(){
	for(Friend f : friends){
	    
	}
    }        

    public static void addFriend(Friend f){
	friends.add(f);
	stack.getChildren().add(f.stackpane());
    }

    private void loadContent(HBox hbox){
	//HBox.setHgrow(stack, Priority.ALWAYS);
	hbox.getChildren().add(scroll);
	hbox.getChildren().add(stack);
	stack.getChildren().add(addButton);

    }

}

package gui;

import javafx.scene.Group;
import javafx.geometry.Orientation;
import javafx.scene.control.ScrollBar;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
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

    public FriendsList(Stage sc, Group root){
	friends = new ArrayList<Friend>();
	initializeStack();
	initializeScroll();
	initializeAddButton(root);
	stack.setSpacing(5);
	Bindings.adjustHeight(sc,scroll,stack);
	Bindings.adjustWidth(sc, addButton, stack);
	Bindings.bindScroll(scroll,stack);

    }

    
    private void initializeAddButton(Group root){
	addButton = new Button("+ ajouter ami");
	addButton.setLayoutX(10);
	addButton.setLayoutY(0);
	addButton.setStyle("-fx-background-color: cyan;");
	addButton.setEffect(new Reflection());
	addButton.setOnAction(new EventHandler<ActionEvent>(){
		@Override public void handle(ActionEvent e){
		    AddFriendPopup.pop();
		}
	    });
	root.getChildren().add(addButton);
    }

    private void initializeStack(){
	stack = new VBox();
	stack.setLayoutY(50);
	stack.setLayoutX(20);
    }

    private void initializeScroll(){
	scroll = new ScrollBar();
	scroll.setLayoutX(10);
	scroll.setLayoutY(50);
	scroll.setMin(20);
        scroll.setOrientation(Orientation.VERTICAL);
        //scroll.setPrefHeight(720);//
        //scroll.setMax(1740);//contenu
    }

    public static VBox stack(){
	return stack;
    }

    public ScrollBar scroll(){
	return scroll;
    }

    

    

    private void adjustPosition(){
	for(Friend f : friends){
	    
	}
    }        

    public static void addFriend(Friend f){
	int s=friends.size();	
	friends.add(f);

	stack.getChildren().add(friends.get(s).pic());
    }
}

package gui;

import java.util.*;
import javafx.util.*;
import javafx.animation.ScaleTransition;
import javafx.scene.Parent;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.paint.Color;
import javafx.scene.Group;

public class Timeline extends Parent {

    private List<Label> posts;
    private VBox field;
    private Button postButton;
    private TextField postEntry;

    public Timeline(HBox hbox){		
	posts = new ArrayList<Label>();
	initializePostEntry();
	initializePostButton();
	initializeField();
	loadContent(hbox);
	//	Bindings.adjust(l.stack(),field); 
    }

    public VBox field(){
	return field;
    }

    private void initializeField(){
	field = new VBox(10);//espace entre les fils
    }

    private void initializePostEntry(){
	postEntry = new TextField();
	postEntry.setPromptText("Type here");
	postEntry.setPrefWidth(200);	
    }

    private void setTransition(Label l){
	ScaleTransition transition = new ScaleTransition(Duration.millis(2000),l);
	transition.setFromX(0.1);
	transition.setFromY(0.1);
	transition.setToX(1);
	transition.setToY(1);
	transition.setCycleCount(1);
	transition.setAutoReverse(true);
	transition.play();
    }

    private void initializePostButton(){
	postButton = new Button("Post");
	postButton.setPrefWidth(100);
	postButton.setStyle("-fx-background-color: cyan;");
	postButton.setOnAction(new EventHandler<ActionEvent>(){
		@Override public void handle(ActionEvent e){
		    addPost();
		}
	    });
    }

    private void addPost(){
	String p = postEntry.getCharacters().toString();
	if (!p.trim().isEmpty()){
	    int s = posts.size();
	    posts.add(new Label());
	    Label l = posts.get(0);
	    for(int i = s; i > 0; i--){
		posts.get(i).setText(posts.get(i-1).getText());
	    }
	    l.setText(p);
	    l.setMinWidth(100);
	    l.setTextFill(Color.GREEN);
	    //l.setStyle("-fx-border-color:white;");
	    field.getChildren().add(posts.get(s));
	    setTransition(l);
	}
	postEntry.clear();
    }

    //private void movePosts

    private void loadContent(HBox hbox){
	/*postButton.setLayoutY(20);
	postEntry.setLayoutY(10);
	postButton.setLayoutX(200);
	postEntry.setLayoutX(200);*/
	//	HBox.setHgrow(field, Priority.ALWAYS);
	//VBox.setVgrow(field, Priority.ALWAYS);
	field.getChildren().add(postEntry);
	field.getChildren().add(postButton);
	hbox.getChildren().add(field);	
    }
}

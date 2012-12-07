package gui;

import javafx.scene.control.TextField;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class AddFriendPopup {

    public static void pop(){
	final Stage popup = new Stage();
	Group gr = new Group();
	Scene scene = new Scene(gr,320,100);//fenetre
        scene.setFill(Color.GREY);
        popup.setScene(scene);
        popup.setTitle("Ajouter Nouvel Ami");
     	
	//HBox box = new HBox();
	//box.getChildren().add(new Label("In popup..."));
	//box.setPrefSize(100, 100);

	//box.setStyle("-fx-background-color: gray;");
	
	//popup.getContent().add(box);
	popup.setResizable(false);
	gr.getChildren().addAll(adressField(), adressEntry(), confirmButton(), closeButton(popup));
	popup.show();
    }

    private static Label adressField(){
	Label l = new Label("Ajouter Adresse");
	return l;
    }

    private static TextField adressEntry(){
	TextField txt = new TextField();
	return txt;
    }

    private static String getAdressEntry(TextField txt){
	return txt.getCharacters().toString();    
    }

    private static Friend newFriend(FriendsList l,String image){
	Friend f = new Friend(l.stack(),image);
	return f;
    }

    private static Button confirmButton(){
	Button bt = new Button("Ajouter");
	bt.setLayoutX(250);
	bt.setOnAction(new EventHandler<ActionEvent>(){
		@Override public void handle(ActionEvent e){
		    //FriendsList.addFriend(newFriend());
		}
	    });
	return bt;
    }

    public static Button closeButton(final Stage s){
	Button bt = new Button("Fermer");
	bt.setLayoutX(10);
	bt.setLayoutY(70);
	bt.setOnAction(new EventHandler<ActionEvent>(){
		@Override public void handle(ActionEvent e){
		    s.close();
		}
	    });
	return bt;
    }

}

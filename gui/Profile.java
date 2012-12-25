package gui;

import javafx.geometry.*;
import javafx.util.*;
import javafx.scene.Parent;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.animation.ScaleTransition;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Profile extends Parent{

    private TilePane tile;

    public Profile(Friend f, HBox hbox){
	initializeVbox(hbox);
	loadProfile(f);
    }

    public static void setTransition(StackPane l){
	ScaleTransition transition = new ScaleTransition(Duration.millis(2000),l);
	transition.setFromX(0.1);
	transition.setFromY(0.1);
	transition.setToX(1);
	transition.setToY(1);
	transition.setCycleCount(1);
	transition.setAutoReverse(true);
	transition.play();
    }

    private void initializeVbox(HBox hbox){
	tile = new TilePane(Orientation.VERTICAL);
	tile.setVgap(10);
	hbox.getChildren().add(tile);
    }

    private void loadProfile(Friend f){
	tile.getChildren().add(f.stackpane());
	//	setTransition(f.stackpane());
	//tile.getChildren().add(f.posts());
	HBox.setHgrow(tile, Priority.ALWAYS);
    }
}

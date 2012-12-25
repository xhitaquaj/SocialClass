package gui;

import javafx.geometry.*;
import javafx.scene.Parent;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import java.util.*;

public class Friend extends Parent{

    private Label name;
    private Image image;
    private ImageView pic;
    private Label status;
    private List<String> posts;
    private StackPane sp;

    public Friend(VBox stack, String name, String status){
	this(stack, name, "default_friend_pic.jpg",status);
    }
    
    public Friend(VBox stack, String name, String image_name, String status){
	loadName(name);
	loadStatus(status);
	loadImage(image_name);
	loadImageView();
	loadStack();
	Bindings.adjustWidth(stack,pic);
    }
    
    public StackPane stackpane(){
	return sp;
    }

    private void loadName(String name){
	this.name = new Label(name);
	this.name.setTextFill(Color.VIOLET);    
    }

    private void loadStack(){
	sp = new StackPane();
	sp.setAlignment(status, Pos.BOTTOM_RIGHT);
	sp.setAlignment(name, Pos.TOP_LEFT);
	sp.getChildren().addAll(pic, name, status);
    }

    private void loadStatus(String status){
	this.status = new Label(status);
	this.status.setTextFill(Color.BLUE);
    }

    private void loadImage(String image_name){
	image = new Image(getClass().getResourceAsStream(image_name));
    }
    
    private void loadImageView(){
	pic = new ImageView(image);	
	DropShadow shadow = new DropShadow();
	shadow.setColor(Color.GREY);
        shadow.setOffsetX(2);
        shadow.setOffsetY(2);
	pic.setEffect(shadow);
	pic.setPreserveRatio(true);
    }

    

}

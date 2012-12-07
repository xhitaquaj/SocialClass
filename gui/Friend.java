package gui;

import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class Friend extends Parent{

    private Image image;
    private ImageView pic;

    public Friend(VBox stack){
	this(stack,"default_friend_pic.jpg");
    }
    
    public Friend(VBox stack, String image_name){
	loadImage(image_name);
	loadImageView();
	Bindings.adjustWidth(stack,pic);
    }

    public ImageView pic(){
	return pic;
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

package gui;

import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.control.ScrollBar;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class Bindings {

    protected static void adjustHeight(final Stage s, final ScrollBar sb, final VBox vb){
	s.heightProperty().addListener(new ChangeListener<Number>(){
		public void changed(ObservableValue<? extends Number> obervable,
				    Number oldValue, Number newValue){
		    Double height = (Double)newValue;
		    vb.setPrefHeight(height);
		    sb.setPrefHeight(height-70);
		    sb.setMax(height-50);
		}
	    });	
    }
    protected static void adjustWidth(final Stage s, final Button b, final VBox vb){
	s.widthProperty().addListener(new ChangeListener<Number>(){
		public void changed(ObservableValue<? extends Number> obervable,
				    Number oldValue, Number newValue){
		    Double width = (Double)newValue;
		    width = width*0.2;
		    vb.setPrefWidth(width);
		    vb.setMaxWidth(width);
		    vb.setMinWidth(width);
		    b.setPrefWidth(width);
		    //b.setMinWidth(width);
		    //b.setMaxWidth(width);
		}
	    });
}
    protected static void bindScroll(final ScrollBar sb, final VBox vb){
	sb.valueProperty().addListener(new ChangeListener<Number>() {
		public void changed(ObservableValue<? extends Number> ov,
				    Number old_val, Number new_val) {
                    vb.setLayoutY(50-new_val.doubleValue());
		}
        });
    }

    protected static void adjustWidth(final VBox sp, final ImageView iv){
	sp.widthProperty().addListener(new ChangeListener<Number>(){
		public void changed(ObservableValue<? extends Number> obervable,
				    Number oldValue, Number newValue){
		    Double width = (Double)newValue;
		    iv.setFitWidth(width);
		}
	    });
    }

    protected static void adjustHeight(final VBox sp){
	sp.heightProperty().addListener(new ChangeListener<Number>(){
		public void changed(ObservableValue<? extends Number> obervable,
				    Number oldValue, Number newValue){
		    Double height = (Double)newValue;
		    // pic.setFitHeight(height*0.2);
		}
	    });	
    }

    protected static void adjust(final VBox src, final VBox dst){
	src.widthProperty().addListener(new ChangeListener<Number>(){
		public void changed (ObservableValue<?extends Number> observable,
				     Number oldVal, Number newVal){
		    Double width = (Double) newVal;
		    dst.setLayoutX(width+100);
		    dst.setLayoutY(70);
		}
	    });
    }

}

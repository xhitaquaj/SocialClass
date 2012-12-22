package communication;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javafx.scene.image.Image;

public class StringManagement {
	
	protected StringManagement(){}
	
	public static final DateFormat df =new SimpleDateFormat("yyyy MM dd HH:mm:ss", Locale.ENGLISH);
	public static final String sep = "##";

	public static Image strtoPic(String string) { //Pour l'instant non-fonctionnelle.
		//TODO Voir comment convertir image -> string et vice-versa.
		return null;
	}
	
	public static String pictoStr(Image pic) {
		//TODO Voir comment convertir string -> image  et vice-versa.
		return null;
	}
	
}

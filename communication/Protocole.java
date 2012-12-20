package communication;

import java.util.*;

public class Protocole {

	private Protocole(){}
	
	public static void treatmentProtocol(int reqcode, String dataReceived){
		for(;;){
			if(connect(reqcode, dataReceived)) break;

			if(statusReceived(reqcode, dataReceived)) break;

			if(commentReceived(reqcode, dataReceived)) break;

			if(friendRequest(reqcode, dataReceived)) break;

			if(friendYesAnswer(reqcode, dataReceived)) break;

			if(friendNoAnswer(reqcode, dataReceived)) break;

			if(imageStatus(reqcode, dataReceived)) break;

			if(imageProfile(reqcode, dataReceived)) break;

			if(errorMessage(reqcode, dataReceived)) break;

			sendErrorMessage(reqcode);
			break;
		}
	}

	private static boolean connect(int reqcode, String dataReceived){
		if( reqcode != 00)
			return false;
		Hashtable<String, String> donnesRequete = Splitter.connect(dataReceived);
		return true;
	}


	private static boolean statusReceived(int reqcode, String dataReceived){
		if (reqcode != 10)
			return false;
		Hashtable<String, String> donnesRequete = Splitter.status(dataReceived);
		return true;
	}

	private static boolean commentReceived(int reqcode, String dataReceived){
		if (reqcode != 11)
			return false;
		Hashtable<String, String> donnesRequete = Splitter.comment(dataReceived);
		return true;
	}

	private static boolean friendRequest(int reqcode, String dataReceived){
		if (reqcode != 20)
			return false;
		Hashtable<String, String> donnesRequete = Splitter.friendRequest(dataReceived);
		return true;
	}

	private static boolean friendYesAnswer(int reqcode, String dataReceived){
		if (reqcode != 21)
			return false;
		Hashtable<String, String> donnesRequete = Splitter.friendResponse(dataReceived);
		return true;
	}

	private static boolean friendNoAnswer(int reqcode, String dataReceived){
		if (reqcode != 22)
			return false;
		Hashtable<String, String> donnesRequete = Splitter.friendResponse(dataReceived);
		return true;
	}

	private static boolean imageStatus(int reqcode, String dataReceived){
		if (reqcode != 40)
			return false;
		Hashtable<String, String> donnesRequete = Splitter.image(dataReceived);
		return true;
	}
	
	private static boolean imageProfile(int reqcode, String dataReceived){
		if (reqcode != 41)
			return false;
		Hashtable<String, String> donnesRequete = Splitter.image(dataReceived);
		return true;
	}

	private static boolean errorMessage(int reqcode, String dataReceived){
		if (reqcode != 99)
			return false;
		Hashtable<String, String> donnesRequete = Splitter.errorMessage(dataReceived);
		return true;
	}

	private static void sendErrorMessage(int reqcode){
		String error = "Erreur: le cas "+reqcode+" n'est pas traité.";
		//Function d'envoi
	}


}
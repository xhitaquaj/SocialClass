package communication;

import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.*;

import core.Profile;

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

			sendErrorMessage("42 Poyo", Profile.mine);
			break;
		}
	}

	private static boolean connect(int reqcode, String dataReceived){
		if( reqcode != 00)
			return false;
		Hashtable<String, String> dataTable = Splitter.connect(dataReceived);
		int i = Profile.mine.getFriend(dataTable.get("Name"));
		if (i < 0)
			sendErrorMessage("Vous n'êtes pas mon ami monsieur. Lâchez ma veste.", new Profile("Monsieur", dataTable.get("Host")));
		else
		{
			try {
				Profile.mine.getFriend(i).getNode().setHost(InetAddress.getByName(dataTable.get("Host")));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return true;
	}


	private static boolean statusReceived(int reqcode, String dataReceived){
		if (reqcode != 10)
			return false;
		Hashtable<String, String> dataTable = Splitter.status(dataReceived);
		int i = Profile.mine.getFriend(dataTable.get("Name"));
		if (i < 0)
			sendErrorMessage("Vous n'êtes pas mon ami monsieur. Lâchez ma veste.", new Profile("Monsieur", dataTable.get("Host")));
		else
		{
			try {
				Profile.mine.getFriend(i).addThought(dataTable.get("Status"), new SimpleDateFormat("yyyy MM dd HH:mm:ss", Locale.ENGLISH).parse(dataTable.get("Date")));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	private static boolean commentReceived(int reqcode, String dataReceived){
		if (reqcode != 11)
			return false;
		Hashtable<String, String> dataTable = Splitter.comment(dataReceived);
		
		return true;
	}

	private static boolean friendRequest(int reqcode, String dataReceived){
		if (reqcode != 20)
			return false;
		Hashtable<String, String> dataTable = Splitter.friendRequest(dataReceived);
		return true;
	}

	private static boolean friendYesAnswer(int reqcode, String dataReceived){
		if (reqcode != 21)
			return false;
		Hashtable<String, String> dataTable = Splitter.friendResponse(dataReceived);
		return true;
	}

	private static boolean friendNoAnswer(int reqcode, String dataReceived){
		if (reqcode != 22)
			return false;
		Hashtable<String, String> dataTable = Splitter.friendResponse(dataReceived);
		return true;
	}

	private static boolean imageStatus(int reqcode, String dataReceived){
		if (reqcode != 40)
			return false;
		Hashtable<String, String> dataTable = Splitter.image(dataReceived);
		return true;
	}
	
	private static boolean imageProfile(int reqcode, String dataReceived){
		if (reqcode != 41)
			return false;
		Hashtable<String, String> dataTable = Splitter.image(dataReceived);
		return true;
	}

	private static boolean errorMessage(int reqcode, String dataReceived){
		if (reqcode != 99)
			return false;
		Hashtable<String, String> dataTable = Splitter.errorMessage(dataReceived);
		return true;
	}

	private static void sendErrorMessage(String error, Profile sendto){
		send(99, error, sendto);
	}

	private static void send(int reqcode, String req, Profile sendto)	//Fonction qui va envoyer [reqcode]+[req] à l'user sendto
	{
		try
		{
			Socket s = new Socket(sendto.getNode().getHost().getHostAddress(), sendto.getNode().getPort());
			OutputStream os = s.getOutputStream();
			PrintStream ps = new PrintStream(os, false, "utf-8");
			ps.println(reqcode+req);
			ps.flush();
			ps.close();
			s.close();
		}
		catch (Exception e)
		{
			System.out.print(e.toString());
		}
	}

}
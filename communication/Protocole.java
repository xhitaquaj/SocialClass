package communication;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.Date;
import java.util.Hashtable;

import core.Comm;
import core.Node;
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

			Sender.sendErrorMessage("42 Poyo", Profile.mine);
			break;
		}
	}

	private static boolean connect(int reqcode, String dataReceived){
		if( reqcode != 00)
			return false;
		Hashtable<String, String> dataTable = Splitter.connect(dataReceived);
		int i = Profile.mine.getFriendIndex(dataTable.get("Name"));
		if (i < 0)
			Sender.sendErrorMessage("Vous n'êtes pas mon ami monsieur. Lâchez ma veste.", new Profile("Monsieur", dataTable.get("Host")));
		else
			try {
				Profile.mine.getFriend(i).getNode().setHost(InetAddress.getByName(dataTable.get("Host")));
			} catch (Exception e) {	e.printStackTrace(); }
		return true;
	}


	private static boolean statusReceived(int reqcode, String dataReceived){
		if (reqcode != 10)
			return false;
		Hashtable<String, String> dataTable = Splitter.status(dataReceived);
		int i = Profile.mine.getFriendIndex(dataTable.get("Name"));
		if (i < 0)
			Sender.sendErrorMessage("Vous n'êtes pas mon ami monsieur. Lâchez ma veste.", new Profile("Monsieur", dataTable.get("Host")));
		else
			try {
				Profile.mine.getFriend(i).addThought(dataTable.get("Status"), StringManagement.df.parse(dataTable.get("Date")));
			} catch (Exception e) { e.printStackTrace(); }
		return true;
	}

	private static boolean commentReceived(int reqcode, String dataReceived){
		if (reqcode != 11)
			return false;
		Hashtable<String, String> dataTable = Splitter.comment(dataReceived);
		try {
			Profile statusPoster = Profile.mine.getFriend(Profile.mine.getFriendIndex(dataTable.get("StatusName")));
			Date postDate = StringManagement.df.parse(dataTable.get("StatusDate"));
			Date commentDate = StringManagement.df.parse(dataTable.get("CommentDate"));
			Comm comment = new Comm(dataTable.get("Comment"), commentDate, Profile.mine.getFriend(Profile.mine.getFriendIndex(dataTable.get("CommentName"))));
			statusPoster.findThought(postDate).addCom(comment);
		} catch (ParseException e) { e.printStackTrace(); }
		return true;
	}

	private static boolean friendRequest(int reqcode, String dataReceived){
		if (reqcode != 20)
			return false;
		
		Hashtable<String, String> dataTable = Splitter.friendRequest(dataReceived);
		String friendName = dataTable.get("NewFriendName");
		int index;
		Profile p;
		Profile newFriend;
		try {
			newFriend = new Profile(dataTable.get(friendName), new Node(InetAddress.getByName(dataTable.get("NewFriendHost"))));
			for(int ami = 0; ami < Profile.mine.getFriends().size(); ami++)
			{
				p = Profile.mine.getFriend(ami);
				index = p.getFriendIndex(friendName);
				if(index > -1)
					p = Profile.mine.getFriend(ami).getFriend(index);
				if(index > -2)
				{
					newFriend = p; 
					break;
				}
			}
			for(int i=1; i<dataTable.size(); i++)
			{
				String friendOfAFriend = "Friend"+i+"Name";
				for(int ami = 0; ami < Profile.mine.getFriends().size(); ami++)
				{
					p = Profile.mine.getFriend(ami);
					index = p.getFriendIndex(friendOfAFriend);
					if(index > -1)
						p = Profile.mine.getFriend(ami).getFriend(index);
					if(index > -2)
					{
						newFriend.addFriend(p); 
						break;
					}
				}
				if (Profile.mine.getFriendIndex(friendOfAFriend)==-1)
					newFriend.addFriend(Profile.mine);
				else
					newFriend.addFriend(new Profile(friendOfAFriend, new Node(InetAddress.getByName(dataTable.get("Friend"+i+"Host")))));
			}
			Profile.mine.addFriend(newFriend);
		} catch (UnknownHostException e) { e.printStackTrace();	}
		//TODO Faire ajouter une notification pour que l'interface puisse gérer l'évènement.
		return true;
	}

	private static boolean friendYesAnswer(int reqcode, String dataReceived){
		if (reqcode != 21)
			return false;
		Hashtable<String, String> dataTable = Splitter.friendResponse(dataReceived);
		Profile.mine.getFriend(Profile.mine.getFriendIndex(dataTable.get("Name"))).setCloseFriend(true);
		return true;
	}

	private static boolean friendNoAnswer(int reqcode, String dataReceived){
		if (reqcode != 22)
			return false;
		Hashtable<String, String> dataTable = Splitter.friendResponse(dataReceived);
		Profile.mine.getFriend(Profile.mine.getFriendIndex(dataTable.get("Name"))).setCloseFriend(false);
		return true;
	}

	private static boolean imageStatus(int reqcode, String dataReceived){
		if (reqcode != 40)
			return false;
//		Hashtable<String, String> dataTable = Splitter.status(dataReceived);
		statusReceived(10, dataReceived); //Tant qu'on ne gere pas les images en statuts.
		return true;
	}
	
	private static boolean imageProfile(int reqcode, String dataReceived){
		if (reqcode != 41)
			return false;
		Hashtable<String, String> dataTable = Splitter.image(dataReceived);
		Profile.mine.getFriend(Profile.mine.getFriendIndex(dataTable.get("Name"))).setProfilePic(StringManagement.strtoPic(dataTable.get("Image")));
		return true;
	}

	private static boolean errorMessage(int reqcode, String dataReceived){ 
		//TODO Voir le systme de notifications.
		if (reqcode != 99)
			return false;
//		Hashtable<String, String> dataTable = Splitter.errorMessage(dataReceived);
		return true;
	}

}
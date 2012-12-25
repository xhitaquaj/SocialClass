import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.LinkedList;

import communication.Server;
import communication.StringManagement;

import core.Node;
import core.Profile;
import core.XmlTreatment;

public class RunTest {		//Classe de test.
	
	public static void main(String[] args)
	{
		Node n = null;
		try {
			n = new Node();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		Profile.mine = new Profile(core.XmlTreatment.getUserName(Profile.mine.getFriendFile()), n);
		LinkedList<String> friends = core.XmlTreatment.getFriends(Profile.mine.getFriendFile());
		for(int ami=0; ami < friends.size()/3; ami++)
			Profile.mine.addFriend(new Profile(friends.get(ami), friends.get(ami+1), friends.get(ami+2).equals("true")));
		LinkedList<String> status = core.XmlTreatment.getStatus(Profile.mine.getThoughtFile());
		for(int stat=0; stat < status.size()/2; stat++)
			try {
				Profile.mine.addThought(status.get(stat), StringManagement.df.parse(status.get(stat+1)));
			} catch (ParseException e) {}
		Server server = new Server();
		server.run();
		
		while(true);
	}


}
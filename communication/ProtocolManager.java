package communication;

import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Locale;

import core.Comm;
import core.Node;
import core.Profile;
import core.Thought;

public class ProtocolManager
{
	private Node myNode;
	public static Hashtable<String, String> Notif;
	public static String sep = "_&§&_";
	public static String sep2 = "_§§_";
	public static String sep3 = "_&&_";
	public static String sep4 = "_o/";

	public ProtocolManager(Node myNode)
	{
		this.myNode = myNode;
	}
	
	public Node getNode()
	{
		return myNode;
	}

	public static String manage(String query) 											//Recoit la requete, la prepare et l'envoie a la fonction qui va la gerer
	{
		int reqcode = Integer.parseInt(query.substring(0,2));
		String req = query.substring(2);
		int numami = Profile.mine.getFriendIndex(req.split(sep)[0]);
		if(numami>-2)
		{
			if (req.split(sep).length > 1)
			{
				if(numami==-1)
					handle(reqcode, req.split(sep), Profile.mine);
				handle(reqcode, req.split(sep), Profile.mine.getFriends().get(numami));	//Another fonction = more clarity.
				return "SUCCESS\n";
			}
			else
				return "Pas d'arguments.\n";
		}
		else
		{
			return "T'ES PAS MON COPAIN.\n";
		}
	}

	private static void handle(int reqcode, String[] req, Profile p) {					//Fonction qui va gerer la requete en fonction de son header
		int s2 = reqcode%10;
		switch((int) Math.floor(reqcode/10)){
		case 1 :
			switch(s2){
			case 0 :			//LISTEN TO ME							- 10Sender_&§&_date_&§&_status
				Thought th;
				try {
					th = new Thought(req[2], new SimpleDateFormat("yyyy MM dd HH:mm:ss", Locale.ENGLISH).parse(req[1]));
					p.addThought(th);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				break;
			case 1 :			//I'M GONNA TALK ABOUT YOU TALKING		- 11s_username_&§&_s_date_&§&_c_username_&§&_c_commentaire 
				Comm comm = new Comm(req[3], Profile.mine.getFriends().get(Profile.mine.getFriendIndex(req[2])));
				try {
					p.findThought(new SimpleDateFormat("yyyy MM dd HH:mm:ss", Locale.ENGLISH).parse(req[1])).addCom(comm);
				} catch (ParseException e) {
					e.printStackTrace();
				}				
				break;
			}
		case 2 :
			switch(s2){
			case 0 :			//"PLZ BE MAH FRIEND?"			- 20Sender_&§&_adresse
				//fonction evenement qui enclenche l'apparition d'une popup
				break;
			case 1 :			//"YEA. ILL BE UR BUDDY"		- 21Sender_&§&_amis1_§§_amis2_&§&_{Date_§§_Statuts1_&&_commentaire1_&&_commentaire2_&&_...}{Status2_&&_.....}
				String[] friends = req[1].split(sep2);
				for (int i = 0; i<friends.length; i++)
					p.addFriend(new Profile(friends[i].split(sep4)[0], friends[i].split(sep4)[1]));
				String[] thoughts = req[2].split("}{");
				for (int i = 0; i<thoughts.length; i++)
				{
					Thought t = null;
					try {
						t = new Thought(thoughts[i].split(sep3)[0].split(sep2)[1], new SimpleDateFormat("yyyy MM dd HH:mm:ss", Locale.ENGLISH).parse(thoughts[i].split(sep3)[0].split(sep2)[0].substring(1)));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					for (i = 0; i < thoughts[i].split(sep3).length; i++)
					{
						t.addCom(new Comm(thoughts[i].split(sep3)[i].split(sep4)[0], p.getFriends().get(p.getFriendIndex(thoughts[i].split(sep3)[i].split(sep4)[1]))));
 					}
				}
				Profile.mine.addFriend(p);
				break;
			case 2 :			//"NOPE. WALK AWAY PEASANT."	- 22Sender_&§&_amis1_§§_amis2_&§&_{_&&_Statuts1_&&_commentaire1_&&_commentaire2_&&_..._&&_}...
				break;
			case 3 :			//"PLZ SEND MOAR FRIENDS"		- 23Sender_&§&_adresse
				break;
			case 4 :			//"KAI. HERE THEI R"		 	- 24Sender_&§&_amis1_§§_amis2
				break;
			}
			break;
		case 3 :
			switch(s2){
			case 0 :			//"GUIES. WHAT R YOU THINKIN' BOUT ?"		- 30Sender_&§&_adresse
				break;
			case 1 :			//"YOU KNO. LOTSA STUFF. IM A DEEP GUY"		- 31Sender_&§&_{_&&_Statuts1_&&_commentaire1_&&_commentaire2_&&_..._&&_}{_&&_Status2_&&_....._&&_} 
				break;
			}
			break;
		case 4 :
			switch(s2){
				case 0:			//the computer turned on by itself and took a photo ok i'm not a model
					break;
				case 2:			//Ping pong.
					String name;
					if(p.getName().equals("A"))
						name = "B";
					else 
						name = "A";
					String mess = name+sep+" POYO.";
					send(42, mess, p);
					Profile.check(mess);
					break;
			}
			break;
		}
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

package communication;

import java.io.*;
import java.net.*;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

import sun.util.calendar.BaseCalendar.Date;

import core.*;

public class ProtocolManager
{
	private Node myNode;
	private static String sep = "_&§&_";

	public ProtocolManager(Node myNode)
	{
		this.myNode = myNode;
	}
	
	public Node getNode()
	{
		return myNode;
	}

	public static String manage(String query, int port)
	{
		int reqcode = Integer.parseInt(query.substring(0,2));
		String req = query.substring(2);
		int numami = Profile.mine.getFriend(req.split(sep)[0]);
		if(numami>-2)
		{
			if (req.split(sep).length > 1)
			{
				if(numami==-1)
					handle(reqcode, req.split(sep), Profile.mine);
				handle(reqcode, req.split(sep), Profile.mine.getFriends().get(numami)); //Another fonction = more clarity.
				return "Hey !";
			}
			else
				return "I don't understand sorry.\n";
		}
		else
		{
			return "T'ES PAS MON COPAIN.";
		}
	}

	private static void handle(int reqcode, String[] req, Profile p) {
		int s2 = reqcode%10;
		switch((int) Math.floor(reqcode/10)){
		case 1 :
			switch(s2){
			case 0 :			//10Sender_&§&_date_&§&_status
				Thought th;
				try {
					th = new Thought(req[2], new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).parse(req[1]));
					p.addThought(th);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				break;
			case 1 :			//11s_username_&§&_s_date_&§&_c_username_&§&_c_commentaire 
				Comm comm = new Comm(req[3], Profile.mine.getFriends().get(Profile.mine.getFriend(req[2])));
				p.findThought(ew SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).parse(req[1])).addCom(comm);				
				break;
			}
		case 2 :
			switch(s2){
			case 0 :			//20Sender_&§&_adresse
				//friend request
				break;
			case 1 :			//21Sender_&§&_amis1_§§_amis2_&§&_{Statuts1_&&_commentaire1_&&_commentaire2_&&_...}{Status2_&&_.....} 
				//friend request accepted
				break;
			case 2 :			//22Sender_&§&_amis1_§§_amis2_&§&_{_&&_Statuts1_&&_commentaire1_&&_
				//friend request refused
				break;
			case 3 :			//23Sender_&§&_adresse
				//asking for friend list
				break;
			case 4 :			//24Sender_&§&_amis1_§§_amis2
				//sending friend list
				break;
			}
			break;
		case 3 :
			switch(s2){
			case 0 :			//30Sender_&§&_adresse
				//asking thoughts
				break;
			case 1 :			//31Sender_&§&_{_&&_Statuts1_&&_commentaire1_&&_commentaire2_&&_..._&&_}{_&&_Status2_&&_....._&&_} 
				//sending thought
				break;
			}
			break;
		case 4 :
			//pic
			break;
		}
	}
	private static void send(String thought, Profile sendto)
	{
		try
		{
			Socket s = new Socket(sendto.getNode().getHost().getHostAddress(), sendto.getNode().getPort());
			OutputStream os = s.getOutputStream();
			PrintStream ps = new PrintStream(os, false, "utf-8");
				String name;
			if(sendto.getName().equals("A"))
				name = "B";
			else 
				name = "A";
			ps.println("10"+name+":");
			Profile.check("");
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

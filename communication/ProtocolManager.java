package communication;

import java.io.*;
import java.net.*;
import java.util.*;

import sun.util.calendar.BaseCalendar.Date;

import core.*;

public class ProtocolManager
{
	private Node myNode;

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
		int numami = Profile.mine.isFriend(req.split(":")[0]);
		if(numami>-1)
		{
			if (req.split(":").length > 1)
			{
				handle(reqcode, req, Profile.mine.getFriends().get(numami)); //Another fonction = more clarity.
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

	private static void handle(int reqcode, String req, Profile sender) {
		int s2 = reqcode%10;
		switch((int) Math.floor(reqcode/10)){
		case 1 :
			switch(s2){
			case 0 :
				Thought th = new Thought(req.split(":")[1]);
				sender.addThought(thought);
				break;
			case 1 :
				String comm = req;
				
				break;
			}
		case 2 :
			switch(s2){
			case 0 :
				//friend request
				break;
			case 1 :
				//friend request accepted
				break;
			case 2 :
				//friend request refused
				break;
			case 3 :
				//asking for friend list
				break;
			case 4 :
				//sending friend list
				break;
			}
			break;
		case 3 :
			switch(s2){
			case 0 :
				//asking thoughts
				break;
			case 1 :
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
			ps.println("10"+name+":Bite");
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

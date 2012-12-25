import java.text.ParseException;
import java.util.Date;

import communication.Sender;
import communication.StringManagement;

import core.*;

/**
 * Class containing ready-to-be-bound methods and combining-stock-and-core methods.
 * This class is made to be a link between packages.
 * @author aquaj
 *
 */
public class UpLevel {
	
	private UpLevel(){};
	
	
	public static void addThought(String tContent)
	{
		Thought t = new Thought(tContent);
		addThought(Profile.mine, t);
		for(int ami = 0; ami < Profile.mine.getFriends().size(); ami++)
			Sender.sendStatus(t, Profile.mine.getFriend(ami));
	};
	
	public static void addComm(String tOwner, String tDate, String cContent, String cOwner, String cDate)
	{
		Comm c = null;
		Date tdate = null;
		Profile ptOwner = Profile.mine.getFriend(Profile.mine.getFriendIndex(tOwner));
		try {
			c = new Comm(cContent, StringManagement.df.parse(cDate), Profile.mine.getFriend(Profile.mine.getFriendIndex(cOwner)));
			tdate = StringManagement.df.parse(tDate);
			addComm(ptOwner, tdate, c);
		} catch (ParseException e) {}
		for(int ami = 0; ami < Profile.mine.getFriends().size(); ami++)
			Sender.sendComment(ptOwner, c, ptOwner.findThought(tdate), Profile.mine.getFriend(ami));
	};
	
	public static void addFriend(String friendName, String friendHost) 
	//to call when a friend request is sent
	{
		Profile friend = new Profile(friendName, friendHost);
		addFriend(Profile.mine, friend);
		Sender.friendRequest(friend);
	};
	
	
	public static void addThought(Profile p, Thought t)
	{
		p.addThought(t);
		String[] status = new String[3];
		status[0] = new Boolean(t.isPublic).toString();
		status[1] = t.content;
		status[2] = StringManagement.df.format(t.date);
		XmlTreatment.addStatus(status, p.getThoughtFile());
	}
	
	public static void addComm(Profile tOwner, Date tDate, Comm c)
	{
		tOwner.findThought(tDate).addCom(c);
		String[] comm = new String[3];
		comm[0] = StringManagement.df.format(tDate);
		comm[1] = c.getOwner().getName();
		comm[2] = c.content;
		XmlTreatment.addComment(comm, tOwner.getThoughtFile());
	}
	
	public static void addFriend(Profile p, Profile friend)
	{
		p.addFriend(friend);
		String[] friendInfo = new String[6];
		friendInfo[0] = friend.getName();
		friendInfo[1] = friend.getNode().getHost().getHostAddress();
		friendInfo[2] = new Boolean(friend.isCloseFriend()).toString();
		friendInfo[3] = Sender.pictoStr(friend.getProfilePic());
		friendInfo[4] = friend.getFriendFile();
		friendInfo[5] = friend.getThoughtFile();
		XmlTreatment.addFriend(friendInfo, p.getFriendFile());
	}

}

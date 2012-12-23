package communication;

import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Date;

import javafx.scene.image.Image;

import core.*;

/**
 * @author aquaj
 *
 */
public class Sender extends StringManagement {
	
	protected Sender(){};
	
	public static void connexion(Profile sendto)
	{
		String req = Profile.mine.getName()+sep+Profile.mine.getNode().getHost().getHostAddress();
		send(00, req, sendto);
	}
	
	public static void sendStatus(Thought t, Profile sendto)
	{
		String req = Profile.mine.getName()+sep+df.format(t.date)+sep+t.content;
		send(10, req, sendto);
	}
	
	/**
	 * @param c - Comment
	 * @param t - Thought being commented
	 * @param sendto - User to send to.
	 */
	public static void sendComment(Profile tOwner, Comm c, Thought t, Profile sendto)
	{
		String req = tOwner.getName()+sep+df.format(t.date)+sep+c.getOwner()+sep+df.format(c.date)+sep+c.content;
		send(11, req, sendto);
	}

	public static void friendRequest(Profile sendto)
	{
		String req = Profile.mine.getName()+sep+Profile.mine.getNode().getHost().getHostAddress();
		for(int ami = 0; ami < Profile.mine.getFriends().size(); ami++)
		{
			req += sep+Profile.mine.getFriend(ami).getName()+sep+Profile.mine.getFriend(ami).getNode().getHost().getHostAddress();
		}
		send(20, req, sendto);
	}

	public static void friendAnswer(boolean answer, Profile sendto)
	{
		send(21+(answer?1:0), Profile.mine.getName(), sendto);
		for(int i=0; i < Profile.mine.getThoughts().size(); i++)
		{
			Thought t = Profile.mine.getThoughts().get(i);
			if (t.isPublic)
			{
				sendStatus(t, sendto);
				for(int c = 0; c < t.getComm().size(); c++)
				{
					Comm com = t.getComm().get(c);
					sendComment(Profile.mine, com, t, sendto);
				}
			}
			else if (answer)
				sendStatus(t, sendto);
		}
		sendto.setCloseFriend(answer);
	}

	public static void imageStatus(Image pic, Date d, Profile sendto)
	{
		String req = Profile.mine.getName()+sep+df.format(d)+sep+pictoStr(pic);
		send(40, req, sendto);
	}

	public static void imageProfile(Image pic, Profile sendto)
	{
		String req = Profile.mine.getName()+sep+pictoStr(pic);
		send(41, req, sendto);
	}

	public static void sendErrorMessage(String error, Profile sendto){
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

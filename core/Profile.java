package core;

import java.util.Date;
import java.util.LinkedList;

public class Profile
{
	private String name;
	private LinkedList<Thought> thoughts;
	private LinkedList<Profile> friends;
	private Node node;
	
	public Profile(String name, Node node)
	{
		this.name = name;
		this.node = node;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
	
	public void addTought(String message)
	{
		thoughts.add(new Thought(message));
	}
	
	public void addTought(String message, Date d)
	{
		thoughts.add(new Thought(message, d));
	}
	
	public LinkedList<Thought> getThoughts()
	{
		return thoughts;
	}
	
	public void addFriend(Profile p)
	{
		friends.add(p);
	}
	
	public LinkedList<Profile> getFriends()
	{
		return friends;
	}
	
	public void setNode(Node n)
	{
		this.node = n;
	}
	
	public Node getNode()
	{
		return node;
	}
	
	public boolean isFriend(Node n)
	{
		boolean b = false;
		int i = 0;
		while (!b && i<friends.size())
		{
			b = (friends.get(i).getNode() == node);
		}
		return b;
	}
	
}
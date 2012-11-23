package core;

import java.util.Date;
import java.util.LinkedList;

public class Profile
{
	private String name;
	private LinkedList<Thought> thoughts;

	public Profile(String name)
	{
		this.name = name;
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
}
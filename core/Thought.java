package core;

import java.util.*;

public class Thought			//Gere les statuts et commentaires.
{
	public String content;
	public Date date;
	protected LinkedList<Comm> comList;
	
	public Thought(String content, Date date)
	{
		this.content = content;
		this.date = date;
	}
	
	public Thought(String content)
	{
		this(content, new Date());
	}
	
	public String toString()
	{
		return this.content;
	}
	
	public void addCom(Comm t)
	{
		this.comList.add(t);
	}
	
	public LinkedList<Comm> getComm()
	{
		return this.comList;
	}
	
}
package core;

import java.util.*;

public class Thought
{
	public String content;
	public Date date;
	private LinkedList<Thought> comList;
	
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
	
	public void addCom(Thought t)
	{
		this.comList.add(t);
	}
	
	public LinkedList<Thought> getCom()
	{
		return this.comList;
	}
	
}
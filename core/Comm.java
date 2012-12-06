package core;

import java.util.Date;
import java.util.LinkedList;

public class Comm extends Thought {
	
	private Profile owner;
	
	public Comm(String content, Date date, Profile owner)
	{
		super(content, date);
		this.owner = owner;
	}
	
	public Comm(String content, Profile owner)
	{
		super(content);
		this.owner= owner;
	}
	
	public String toString()
	{
		return ("["+this.date.toString()+""+this.owner.toString()+" : "+this.content);
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

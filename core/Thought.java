package core;

import java.util.Date;

public class Thought
{
	public String content;
	public Date date;
	
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
}

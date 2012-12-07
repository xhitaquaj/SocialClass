package core;

import java.util.Date;
import java.io.*;
import java.net.*;
import java.util.LinkedList;

public class Profile
{
	public static Profile mine;
	
	private String name;
	private LinkedList<Thought> thoughts;
	private LinkedList<Profile> friends;
	private Node node;
	
	public Profile(String name, Node node)
	{
		this.name = name;
		this.node = node;
		this.friends = new LinkedList<Profile>();
		this.thoughts = new LinkedList<Thought>();
	}
	
	public Profile(String name, InetAddress addr)
	{
		this.name = name;
		this.node = new Node(addr);
		this.friends = new LinkedList<Profile>();
		this.thoughts = new LinkedList<Thought>();
	}
	
	public Profile(String name, String addr)
	{
		this.name = name;
		try 
		{
			this.node = new Node(addr);
		} 
		catch (UnknownHostException e) 
		{
			e.printStackTrace();
		}
		this.friends = new LinkedList<Profile>();
		this.thoughts = new LinkedList<Thought>();
	}

	public String getName()
	{
		return name;
	}
	
	public String toString()
	{
		return name+", "+node.getHost();
	}

	public void setName(String name)
	{
		this.name = name;
	}
	
	public void addThought(Thought t)
	{
		thoughts.add(t);
	}
	
	public void addThought(String message)
	{
		thoughts.add(new Thought(message));
	}
	
	public void addThought(String message, Date d)
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
	
	public boolean getFriend(Node n)
	{
		boolean b = false;
		int i = 0;
		while (!b && i<friends.size())
		{
			b = (friends.get(i).getNode().getHost() == node.getHost());
			i++;
		}
		return b;
	}
	
	public int getFriend(String name)
	{
		boolean b = false;
		int i = 0;
		while (!b && i<friends.size())
		{
			b = (friends.get(i).getName().equals(name));
			i++;
		}
		if (mine.name.equals(name))
			return -1;
		if (!b)
			return -2;
		return --i;
	}
	
	public void loadFriends(String path)
	{
		try{
			InputStream ips=new FileInputStream(path); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null){
				Node n = new Node(InetAddress.getByName(ligne.split(":")[1]), Integer.parseInt(ligne.split(":")[2]));
				Profile p = new Profile(ligne.split(":")[0], n);
				addFriend(p);
				}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
	}

	public static void check(String str) {
		System.out.println("Check "+str);
			}

	public Thought findThought(Date d) {
		boolean b = false;
		int i = 0;
		while (!b && i<thoughts.size())
		{
			b = (Math.floor(thoughts.get(i).date.getTime()/1000) == Math.floor(d.getTime()/1000));
			i++;
		}
		if (i < thoughts.size())
		{
			return thoughts.get(i);
		}
		else
		{
			Thought t = new Thought("Ahahaha, ya eu une erreur quelque part la. Donc en fait, j'ai aucune raison de rire, POURQUOI JE RIS MOI ? *sobs*");
			addThought(t);
			return t;
		}
	}
}
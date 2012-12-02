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

	public String getName()
	{
		return name;
	}
	
	public String toString()
	{
		return name+", "+node.getIP();
	}

	public void setName(String name)
	{
		this.name = name;
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
	
	public boolean isFriend(Node n)
	{
		boolean b = false;
		int i = 0;
		while (!b && i<friends.size())
		{
			System.out.println(i+" "+friends.size()+" "+friends.get(i).getNode().getIP()+" "+node.getIP().toString());
			b = (friends.get(i).getNode().getIP() == node.getIP());
			i++;
		}
		return b;
	}
	
	public int isFriend(String name)
	{
		boolean b = false;
		int i = -1;
		while (!b && i<friends.size())
		{
			i++;
			b = (friends.get(i).getName().equals(name));
		}
		return i;
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
}
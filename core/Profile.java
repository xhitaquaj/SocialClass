package core;

import java.util.Date;
import java.io.*;
import java.net.*;
import java.util.LinkedList;

import javafx.scene.image.Image;

public class Profile						//Informations (Status/Nom/Amis/...) sur l'ami.
{
	public static Profile mine;
	
	public static void check(String str) {		//Fonction de debuggage parce que OUI j'ai la flemme de taper System.out.println("check") quand je veux tester.
		System.out.println("Check "+str);
			}
	
	private String name;
	private LinkedList<Thought> thoughts;
	private LinkedList<Profile> friends;
	private Node node;
	private boolean closeFriend = false; 
	private Image profilePic;
	
	private String thoughtfile;
	private String friendfile;

	public Profile(String name, InetAddress addr)
	{
		this.name = name;
		this.node = new Node(addr);
		this.friends = new LinkedList<Profile>();
		this.thoughts = new LinkedList<Thought>();
		this.friendfile = "friends_"+name.replace(" ", "_")+".xml";
		this.thoughtfile = "thoughts_"+name.replace(" ", "_")+".xml";
	}

	public Profile(String name, InetAddress addr, boolean isClose)
	{
		this(name, addr);
		this.closeFriend = isClose;
	}
	
	public Profile(String name, Node node)
	{
		this.name = name;
		this.node = node;
		this.friends = new LinkedList<Profile>();
		this.thoughts = new LinkedList<Thought>();
		this.friendfile = "friends_"+name.replace(" ", "_")+".xml";
		this.thoughtfile = "thoughts_"+name.replace(" ", "_")+".xml";
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
		this.friendfile = "friends_"+name.replace(" ", "_")+".xml";
		this.thoughtfile = "thoughts_"+name.replace(" ", "_")+".xml";
	}

	public Profile(String name, String addr, boolean isClose) 
	{
		this(name, addr);
		this.closeFriend = isClose;
	}

	public void addFriend(Profile p)
	{
		friends.add(p);
	}

	public void addThought(String message)
	{
		addThought(new Thought(message));
	}
	
	public void addThought(String message, Date d)
	{
		addThought(new Thought(message, d));
	}

	public void addThought(Thought t)
	{
		if (!findThought(t.date).content.equals("-1"))		//Evite l'ajout de statut deja presents. Un statut par personne et par seconde.
			thoughts.add(t);
	}
	
	public Thought findThought(Date d) {		//Essentielle a l'ajout de commentaires, permet de retrouver un statut parmi les statuts postes par le Profile a partir de la date de celui-ci.
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
			Thought t = new Thought("-1");
			addThought(t);
			return t;
		}
	}
	
	public String getThoughtFile() {
		return thoughtfile;
	}
	
	public String getFriendFile() {
		return friendfile;
	}
	
	public Profile getFriend(int index)
	{
		Profile p = friends.get(index);
		return p;
	}
	
	public boolean getFriendIndex(InetAddress host)		//cf getFriend(Node n) mais en passant un InetAddress a la place.
	{
		return getFriendIndex(new Node(host));
	}
	
	public boolean getFriendIndex(Node n)		//cf getFriend(String name) mais en passant un Node a la place.
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
	
	public int getFriendIndex(String name)		//Permet de retourner un Profile d'un ami en indiquant seulement son nom.
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
	
	public LinkedList<Profile> getFriends()
	{
		return friends;
	}
	
	public String getName()
	{
		return name;
	}
	
	public Node getNode()
	{
		return node;
	}
	
	public Image getProfilePic() {
		return profilePic;
	}
	
	public LinkedList<Thought> getThoughts()
	{
		return thoughts;
	}
	
	public boolean isCloseFriend() {
		return closeFriend;
	}
	
	public void loadFriends(String path)	//Fonction recuperant les amis et créant leur profil à partir d'un fichier externe.
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

	public void setCloseFriend(boolean closeFriend) {
		this.closeFriend = closeFriend;
	}

	public void setThoughtFile(String file) {
		this.thoughtfile = file;
	}
	
	public void setFriendFile(String file) {
		this.friendfile = file;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}

	public void setNode(Node n)
	{
		this.node = n;
	}

	public void setProfilePic(Image image) {
		this.profilePic = image;
	}

	@Override
	public String toString()
	{
		return name+", "+node.getHost();
	}
}
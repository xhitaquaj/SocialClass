package core;
import java.net.*;

public class Node		//Contient les informations "techniques" du contact.
{
	private InetAddress host;
	private int port;

	public Node() throws UnknownHostException
	{
		this(InetAddress.getLocalHost(), 5234);
	}
	
	public Node(String str) throws UnknownHostException
	{
		this(InetAddress.getByName(str));
	}
	
	public Node(InetAddress addr)
	{
		this(addr, 5234);
	}
		
	public Node(InetAddress addr, int prt)
	{
		this.port = prt;
		this.host = addr;
	}
	
	public int getPort()
	{
		return this.port;
	}
	
	public InetAddress getHost()
	{
		return this.host;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setHost(InetAddress host) {
		this.host = host;
	}
}

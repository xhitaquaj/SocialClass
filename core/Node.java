package core;
import java.net.*;

public class Node
{
	private InetAddress ip;
	private int port;

	public Node()
	{
		this.port = 4242;
		try
		{
			this.ip = InetAddress.getLocalHost();
		}
		catch (UnknownHostException e)
		{
		}
	}
	
		
	public Node(InetAddress addr, int prt)
	{
		this.port = prt;
		this.ip = addr;
	}
	
	public int getPort()
	{
		return this.port;
	}
	
	public InetAddress getIP()
	{
		return this.ip;
	}
}

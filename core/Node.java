package core;
import java.net.*;

public class Node
{
	private InetAddress ip;
	private int port;

	public Node()
	{
	try{
		this.port = 4242;
			this.ip = InetAddress.getLocalHost();
	}
	catch (UnknownHostException e)
	{
		return;
	}
		
	}
	
		
	public Node(int prt, InetAddress addr)
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

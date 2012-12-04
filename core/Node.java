package core;
import java.net.*;

public class Node
{
	private InetAddress host;
	private int port;

	public Node()
	{
		this.port = 5234;
		try
		{
			this.host = InetAddress.getLocalHost();
		}
		catch (UnknownHostException e)
		{
		}
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
}

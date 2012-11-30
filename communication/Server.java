package communication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import core.*;

public class Server extends Thread
{
	private ServerSocket socket;
	private int port;

	public Server()
	{
		this(4242);
	}
	
	public Server(int port)
	{
		boolean created = false;
		int maxport = port + 42;
		
		while (!created && port < maxport) 
		{
			try
			{	
				socket = new ServerSocket(port);
				created = true;
			}
			catch (IOException e)
			{
				port++;
			}
		}
		
		this.port = port;
	}

	@Override
	public void run()
	{
		System.out.println("Server running...");

		try
		{
			Socket client;
			SessionThread session;

			while (true)
			{
				client = socket.accept();
				session = new SessionThread(client);
				session.start();
			}
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getPort()
	{
		return port;
	}
}

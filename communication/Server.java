package communication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread
{
	private ServerSocket socket;
	
	public Server()
	{
		try
		{
			socket = new ServerSocket(4242);
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run()
	{
		System.out.println("Server running...");
		
		try
		{
			Socket client;
			SessionThread session;
			
			while(true)
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
}

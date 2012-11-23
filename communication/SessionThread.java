package communication;

import java.io.BufferedReader;
import java.io.*;
import java.net.Socket;

public class SessionThread extends Thread
{
	private Socket clientSocket;

	public SessionThread(Socket clientSocket)
	{
		this.clientSocket = clientSocket;
	}

	@Override
	public void run()
	{
		System.out.println("New client accepted.");
		boolean connected = true;
		
		try
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
			
			writer.write("Welcome\n");
			writer.flush();

			while (connected)
			{
				String line = reader.readLine();
				if (line == null)
					connected = false;
				else
					System.out.println("Content: " + line);
			}
			
			System.out.println("Client disconnected.");
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

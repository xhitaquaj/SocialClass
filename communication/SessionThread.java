package communication;

import java.io.*;
import java.net.Socket;

import core.*;

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
		ProtocolManager pm = new ProtocolManager(new Node(clientSocket.getPort(), clientSocket.getInetAddress()));
		System.out.println("New client accepted.");
		System.out.println("IP Address: " + pm.getNode().getIP());
		System.out.println("Port: " + pm.getNode().getPort());
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
				{
					System.out.println("Content" + clientSocket.getInetAddress() + ": " + line);
					writer.write(pm.answer(line));
					writer.flush();
				}
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

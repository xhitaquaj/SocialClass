package communication;

import java.net.*;

import core.Profile;

public class TestCom			//Classe de test.
{

	/** @param args */
	public static void main(String[] args)
	{
		Server server = new Server();
		server.run();
		
		while(true);
		
	}

}

package communication;

import java.net.*;

import core.Profile;

public class TestCom			//Classe de test.
{

	/** @param args */
	public static void main(String[] args)
	{
		Server server = new Server();
		if (args.length >0)				//Bloc demarrant un 'ping-pong' entre deux machines.
		{
			try {
				if(InetAddress.getLocalHost().getHostName().toString().equals(Profile.mine.getFriends().get(Profile.mine.getFriend("A")).getNode().getHost().getHostName()))
				{
					ProtocolManager.manage("42B"+ProtocolManager.sep+"Let's start a war !");
				}
				else
				{
					ProtocolManager.manage("42A"+ProtocolManager.sep+"Let's start a war !");
					Profile.check("bluh");
				}
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}
		server.run();
		
		while(true);
		
	}

}

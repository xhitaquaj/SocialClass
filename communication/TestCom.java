package communication;

import java.net.*;

public class TestCom
{

	/** @param args */
	public static void main(String[] args)
	{
		Server server = new Server();
		try {
			if(InetAddress.getLocalHost().getHostName().toString().equals("endor"))
				ProtocolManager.manage("10B:test", 5234);
			else
				ProtocolManager.manage("10A:test", 5234);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		server.run();
		
		while(true);
		
	}

}

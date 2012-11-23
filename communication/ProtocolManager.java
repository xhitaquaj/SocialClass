package communication;

import core.*;

public class ProtocolManager
{
	private Node myNode;

	public ProtocolManager(Node myNode)
	{
		this.myNode = myNode;
	}

	public static String answer(String query)
	{
		if (query.equals("Who are you?"))
			return "I am the Alpha and the Omega, the First and the Last, the Beginning and the End";
		else
			return "I don't understand sorry.";
	}
}

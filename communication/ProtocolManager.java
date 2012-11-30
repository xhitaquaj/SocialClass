package communication;

import core.*;

public class ProtocolManager
{
	private Node myNode;

	public ProtocolManager(Node myNode)
	{
		this.myNode = myNode;
	}
	
	public Node getNode()
	{
		return myNode;
	}

	public String answer(String query)
	{
//		if (Profile.mine.isFriend(this.myNode))
		int numami = (Profile.mine.isFriend(query.split("§:§")[0]));
		if(numami>-1)
		{
			System.out.println(query.split("§:§")[1].split("!:!")[0]);
			if (query.split("§:§")[1].split("!:!")[0].equals("th"))
			{
				Profile.mine.getFriends().get(numami).addTought(query.split("§:§")[1].split("!:!")[1]); //Bug de List, a controler plus tard.
				return "Tout s'est bien passé.";
			}
			else
				return "I don't understand sorry.\n";
		}
		else
		{
			return "T'ES PAS MON COPAIN.";
		}
	}
}

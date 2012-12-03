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

	public String manage(String query)
	{
		int reqcode = Integer.parseInt(query.substring(0,2));
		String req = query.substring(2);
		int numami = Profile.mine.isFriend(req.split(":")[0]);
		if(numami>-1)
		{
			if (req.split(":").length > 1)
			{
				handle(reqcode, req); //Another fonction = more clarity.
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

	private void handle(int reqcode, String req) {
		int s2 = reqcode%10;
		switch((int) Math.floor(reqcode/10)){
		case 1 :
			switch(s2){
			case 0 :
				String thoughts = req;
				break;
			case 1 :
				String comm = req;
			}
		case 2 :
			switch(s2){
			case 0 :
				//friend request
				break;
			case 1 :
				//friend request accepted
				break;
			case 2 :
				//friend request refused
				break;
			case 3 :
				//asking for friend list
				break;
			case 4 :
				//sending friend list
				break;
			}
			break;
		case 3 :
			switch(s2){
			case 0 :
				//asking thoughts
				break;
			case 1 :
				//sending thought
				break;
			}
			break;
		case 4 :
			//pic
			break;
		}
	}
}

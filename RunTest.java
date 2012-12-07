import java.net.UnknownHostException;

import communication.TestCom;

import core.Node;
import core.Profile;

public class RunTest {		//Classe de test.
	
	public static void main(String[] args)
	{
		Node n = null;
		try {
			n = new Node();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		Profile.mine = new Profile("Moi", n);
		Profile.mine.loadFriends("test.txt");
		TestCom.main(args);
	}


}
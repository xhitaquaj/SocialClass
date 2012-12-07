import communication.TestCom;

import core.Node;
import core.Profile;

public class RunTest {
	
	public static void main(String[] args)
	{
		Node n = new Node();
		Profile.mine = new Profile("Moi", n);
		Profile.mine.loadFriends("test.txt");
		TestCom.main(args);
	}


}
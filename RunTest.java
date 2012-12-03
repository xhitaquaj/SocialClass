import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.*;
import java.nio.channels.*;
import java.util.*;

import communication.TestCom;

import core.*;

public class RunTest {
	
	public static void main(String[] args)
	{
		Node n = new Node();
		Profile.mine = new Profile("Moi", n);
		Profile.mine.loadFriends("test.txt");
		TestCom.main(args);
	}


}
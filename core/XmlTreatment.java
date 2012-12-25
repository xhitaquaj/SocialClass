package core;

import java.io.*;

import org.jdom2.*;
import org.jdom2.output.*;
import org.jdom2.input.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;

public class XmlTreatment {

	private XmlTreatment(){}

	static Element root = new Element("root");
	static Document document = new Document(root);

	public static void addComment(String[] comm, String filename) {
		try{
			readFile(filename);
		}catch(Exception e){}

		root.getChildren();
		List<Element> listTmp = root.getChildren("status");
		Iterator<Element> i = listTmp.iterator();
		while(i.hasNext()){
			Element curr = i.next();
			if(comm[0].equals(curr.getChild("date").getText())){

				Element comment = new Element("commentary");
				curr.addContent(comment);

				Element owner = new Element("owner");
				owner.setText(comm[1]);
				comment.addContent(owner);

				Element content= new Element("content");
				content.setText(comm[2]);
				comment.addContent(content);

				break;
			}
		}
		saveFile(filename);
	}

	public static void addFriend(String[] friendInfo, String filename){
		try{
			readFile(filename);
		} catch(Exception e) {}

		Element friend = new Element("friend");
		root.addContent(friend);

		Element name = new Element("name");
		name.setText(friendInfo[0]);
		friend.addContent(name);

		Element host = new Element("host");
		host.setText(friendInfo[1]);
		friend.addContent(host);

		Element status = new Element("isClose");
		status.setText(friendInfo[2]);
		friend.addContent(status);

		Element picture = new Element("picture");
		picture.setText(friendInfo[3]);
		friend.addContent(picture);
		
		Element friendfile = new Element("friendfile");
		friendfile.setText(friendInfo[4]);
		friend.addContent(friendfile);

		Element thoughtfile = new Element("thoughtfile");
		thoughtfile.setText(friendInfo[5]);
		friend.addContent(thoughtfile);
		
		saveFile(filename);
	}

	public static void addStatus(String[] status, String filename){
		try{
			readFile(filename);
		}catch(Exception e){}

		Element stat = new Element("status");
		root.addContent(stat);

		Element type = new Element("type");
		type.setText(status[0]);
		stat.addContent(type);

		Element content= new Element("content");
		content.setText(status[1]);
		stat.addContent(content);

		Element date = new Element("date");
		date.setText(status[2]);
		stat.addContent(date);

		saveFile(filename);

	}

	public static void deleteFriend(String name){
		List<Element> listElement = root.getChildren("name");
		Iterator<Element> i = listElement.iterator();
		while(i.hasNext()){
			Element curr = i.next();
			if(curr.getChild(name)!=null){
				curr.removeChild(name);
				curr.setName("name_modified");
			}
		}
	}

	public static LinkedList<String> getFriends(String filename){
		try {
			readFile(filename);
		} catch (Exception e) {}
		root.getChildren();
		List<Element> listTmp = root.getChildren("friend");
		Iterator<Element> i = listTmp.iterator();
		LinkedList<String> friends = new LinkedList<String>();
		while(i.hasNext()){
			Element curr = i.next();
			friends.add(curr.getChild("name").getText());;
			friends.add(curr.getChild("host").getText());
			friends.add(curr.getChild("isClose").getText());
			friends.add(curr.getChild("picture").getText());
			friends.add(curr.getChild("friendfile").getText());
			friends.add(curr.getChild("thoughtfile").getText());
		}
		return friends;
	}

	public static LinkedList<String> getStatus(String filename){
		try {
			readFile(filename);
		} catch (Exception e) {}
		root.getChildren();
		List<Element> listTmp = root.getChildren("status");
		Iterator<Element> i = listTmp.iterator();
		LinkedList<String> status = new LinkedList<String>();
		while(i.hasNext()){
			Element curr = i.next();
			status.add(curr.getChild("content").getText());
			status.add(curr.getChild("date").getText());
		}
		return status;
	}

	public static String getUserName(String filename){
		try{
			readFile(filename);
		}catch(Exception e){}

		List<Element> listName = root.getChildren("myProfile");
		Element e = listName.iterator().next();
		return e.getChild("name").getText();
	}

	static void readFile(String file) throws Exception
	{
		document = new SAXBuilder().build(new File(file));
		root = document.getRootElement();
	}

	static void saveFile(String file){
		try{
			XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
			sortie.output(document, new FileOutputStream(file));
		} catch(java.io.IOException e){}
	}


}
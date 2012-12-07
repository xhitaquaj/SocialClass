import java.util.Hashtable<K,V>;

public class Notifier {

    public void listenFriendRequest(Hashtable<String,String>  notif){
	ObservableHashtable table = new ObservableHashTable();  
	table.addPropertyChangeListener(new PropertyChangeListener() {  
		public void propertyChange(PropertyChangeEvent event) {  
		    Hashtable paramTable = (Hashtable) event.getSource();  
		    Enumeration enum = paramTable.keys();  
		    while(enum.hasMoreElements()) {  
			Object obj = enum.nextElement();  
			System.out.println(obj + ": " + paramTable.get(obj));  
		    }  
		}  
		/*
		  
		  Listener requestListener = new Listener(){
		  public void requested(Event e){
		    AddFriendRequest.pop(notif.get("name"));
		}
	    }	
	    notif.addListener(requestListener);
	    }*/
    
}

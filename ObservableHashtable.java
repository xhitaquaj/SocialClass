import java.beans.*;  
class ObservableHashtable extends Hashtable {  
     
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);  
    
    
    public void addPropertyChangeListener(PropertyChangeListener l) {  
	pcs.addPropertyChangeListener(l);  
    }  
    
    public Object put(Object key, Object value) {  
	Object result = super.put(key, value);  
        pcs.firePropertyChange("contents", null, null);  
        return result;  
    }  
}  

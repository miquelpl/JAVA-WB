package listener;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Data {
	 private PropertyChangeSupport changes = new PropertyChangeSupport(this); 
	 int count=0;
	 
	 public void addListener(  PropertyChangeListener li) {
		 changes.addPropertyChangeListener(li);
	 }
	 
	 public void test() {
			while(true) {
				
				
				try {
					Thread.sleep(1000);
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				System.out.println("Observable: "+count);
				changes.firePropertyChange("MyCounter", count, ++count);
				
			}
		}
	 
}

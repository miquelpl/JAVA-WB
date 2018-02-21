package listener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MyPropertyChangeListener implements PropertyChangeListener {

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println("getPropertyName: "+ evt.getPropertyName());
		System.out.println("getSource: "+evt.getSource());
		System.out.println("getOldValue: "+evt.getOldValue());
		
		System.out.println("getNewValue: "+evt.getNewValue());
		
	}

}

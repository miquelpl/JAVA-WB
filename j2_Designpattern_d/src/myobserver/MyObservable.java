package myobserver;

import java.util.ArrayList;
import java.util.List;

public class MyObservable {
	private boolean changed;
	private List<MyObserver> list = new ArrayList<>();

	public boolean hasChanged() {
		return changed;
	}

	public void setChanged() {
		this.changed = true;
	}

	public void addObserver(MyObserver obs) {
		if(list.contains(obs))
			list.add(obs);
	}

	public void removeObserver(MyObserver obs) {
		list.remove(obs);
	}

	public void notifyObservers() {
		notifyObservers(null);
	}

	public void notifyObservers(Object obj) {
		if (changed) {
			for (MyObserver myObserver : list) {
				myObserver.update(this, obj);// Polymorphie

			}
			changed = false;
		}
	}

}

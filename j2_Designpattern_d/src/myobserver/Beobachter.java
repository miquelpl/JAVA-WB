package myobserver;

import java.util.Observable;
import java.util.Observer;

public class Beobachter implements MyObserver{

	@Override
	public void update(MyObservable o, Object obj) {
		Data data = (Data) o;
		System.out.println("Beobachter: "+data.count+" Object:"+obj);
		
	}

}

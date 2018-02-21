package observer;

import java.time.LocalDate;
import java.util.Observable;

/*
 * -Subjekt
 * -Observable
 * -Datenverwalter  
 */
public class Data  extends Observable{
	int count=0;
	
	public void test() {
		while(true) {
			count++;
			
			try {
				Thread.sleep(1000);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println("Observable: "+count);
			setChanged();  // Daten haben sich geändert
			//notifyObservers();//"sendet" Daten
			notifyObservers(LocalDate.now());
			
		}
	}
	
	
	

}

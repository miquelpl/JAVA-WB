package singleton;

/*
 * nur eine Instanz eines Objektes
 */
public final class Singleton {

	private static Singleton instance = null;

	private Singleton() {

	}

	public synchronized static Singleton getInstance() {
		if (instance == null) {
			instance = new Singleton();
		}
		return instance;
	}
	
	
	///---------------- hier die "normalen" Methoden
	
	public void methode() {
		
	}
	
	

}

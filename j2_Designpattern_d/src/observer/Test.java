package observer;

public class Test {

	public static void main(String[] args) {

		Data d = new Data();
		Beobachter b1 = new Beobachter();
		d.addObserver(b1);// Observer registrieren
		d.test();
		
	}

}

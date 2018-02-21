package listener;

public class Test {

	public static void main(String[] args) {
		Data d = new Data();
		d.addListener(new MyPropertyChangeListener());
		
		d.test();

	}

}

package strategy;

public class Test {

	public static void main(String[] args) {
		// verhalten "Umschalten" durch neues Objekt
		
		Sorter sorter = new Sorter(new BubbleSort());
		sorter.sort();//BubbleSort...
		sorter.setSort(new QuickSort());
		sorter.sort();//QuickSort...
		
		
	

	}

}

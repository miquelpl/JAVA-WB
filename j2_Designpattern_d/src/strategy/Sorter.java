package strategy;


/**
 * Kontext-Klasse: benutzt Interface (oder Oberklasse)
 * @author Student
 *
 */
public class Sorter {
	
	private Sort sort;
	

	
	/*
	 * parameterloser Konstruktor hier nicht sinnvoll
	 */
	public Sorter(Sort sort) {
		this.sort = sort;
	}

	public Sort getSort() {
		return sort;
	}

	public void setSort(Sort sort) {
		this.sort = sort;
	}
	
	public void sort() {
		sort.sort();// hier Polymorphie -> BubbleSort, QuickSort,... -Objekt 
	}

}

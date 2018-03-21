package model;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Lotto {
	
	public Integer[] getLottoZahlen(int anz, int max) {
		
		Integer[] wetten = null;
		
		if(validParameter(anz, max)) {
			wetten = getRandomArray(anz, max);
		}
//		else {
//			throw new IllegalArgumentException();
//		}
		return(wetten);
	}

	private Integer[] getRandomArray(int anz, int max) {
		Random rnd = new Random();
		Set<Integer> s = new HashSet<Integer>();
		
		while(s.size()<anz) {
			s.add(Integer.valueOf(Math.abs(rnd.nextInt()) % max)+1);
		}
		return(s.toArray(new Integer[anz]));
	}

	private boolean validParameter(int anz, int max) {
		return(anz>0 && anz<=6 && anz <max && max<50); 
	}

}

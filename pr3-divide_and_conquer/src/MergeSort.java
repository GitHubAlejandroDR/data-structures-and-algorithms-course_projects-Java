import java.util.ArrayList;
import java.util.List;

/**
 * Mergesort
 * @author ccottap
 *
 */
public class MergeSort extends SortingAlgorithm {

	@Override

	public <E extends Comparable<? super E>> void sort(List<E> A, int l, int r)
	{
		if(l<r) {
			int m = (l+r)/2;
		    List<E> B = new ArrayList<>();
		    List<E> C = new ArrayList<>();
			List<E> AOrd = new ArrayList<>();
			for (int i = l; i <= r; i++){
				E e = A.get(i);
				if (i < m )B.add(e);
				else C.add(e);
			}
			sort(B,0,B.size()-1);
			sort(C,0,B.size()-1);
			
			mezclar(AOrd,B,C);
			
			for (int i = 0; i < AOrd.size(); i++) A.set(l+i,AOrd.get(i));
		}
	}

	/**
	 * Merges two sorted lists
	 * @param <E> the class of the elements in the list
	 * @param A the merged list
	 * @param B a list to be merged
	 * @param C another list to be merged
	 */

	private <E extends Comparable<? super E>> void mezclar (List<E> A, List<E> B, List<E> C)
	{
		while(!B.isEmpty() && !C.isEmpty()) {
			E b = B.get(0);
			E c = C.get(0);
			if(b.compareTo(c) <= 0) {
				A.add(b);
				B.remove(0);
			} else {
				A.add(c);
				C.remove(0);
			}
		}
		if (B.isEmpty()) {
			while(!C.isEmpty()) {
				A.add(C.get(0));
				C.remove(0);
			}
		}else{		
			while(!B.isEmpty()) {
				A.add(B.get(0));
				B.remove(0);
			}
		}
	}

	
	@Override
	public String getName() {
		return "MergeSort";
	}

}

import java.util.List;


/**
 * Quicksort
 * 
 * @author ccottap
 *
 */
public class QuickSort extends SortingAlgorithm {


	public <E extends Comparable<? super E>> void sort(List<E> A, int l, int r) {
		// TODO
		int m = (l + r) / 2;
		if (r > l) {
			m = partir(A, l, r, m);
			sort(A, l, m - 1);
			sort(A, m + 1, r);
		}
	}


	protected <E extends Comparable<? super E>> int partir(List<E> A, int l, int r, int m) {
		int p = m;
		l--;
		do{
			do l++; while (l <= r && (A.get(l).compareTo( A.get(p)) <= 0 ));
			while (l <= r && (A.get(r).compareTo( A.get(p)) > 0)) r--;

			if (l < r) {
				E aux = A.get(l);
				A.set(l,A.get(r));
				A.set(r,aux);
			}
		}while (l<r);
		
		E aux = A.get(p);
		A.set(p,A.get(r));
		A.set(r,aux);
		return r;
	}
	@Override
	public String getName() {
		return "quicksort";
	}

}

import java.util.List;

/**
 * Insertion sort
 * @author ccottap
 *
 */
public class InsertionSort extends SortingAlgorithm {

/* (non-Javadoc)
	 * @see sortingAlgorithms.SortingAlgorithm#getName()
	 */
	@Override
	public String getName() {
		return "InsertionSort";
	}
	
	/* (non-Javadoc)
	 * @see sortingAlgorithms.SortingAlgorithm#sort(int[],int,int)
	 */
	@Override
	public <E extends Comparable<? super E>> void sort(List<E> A, int l, int r) {
		if (A == null || A.size() <= l || A.size() <= r || l >= r) //Condiciones
			throw new RuntimeException("parametros incorrectos");
		
		for (int i = (l+1); i <= r; i++){
			
			int j = i-1;// Elemento anterior
			E auxiliar = A.get(i);// Elemento posterior
			
			while (j>=l && A.get(j).compareTo(auxiliar) > 0) {
				swap(A, j+1, j);
				j--;
			}
			
			A.set(j+1, auxiliar);
		}
	}

	

}

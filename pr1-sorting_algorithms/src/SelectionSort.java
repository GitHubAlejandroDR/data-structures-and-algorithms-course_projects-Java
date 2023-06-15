import java.util.List;

/**
 * Selection sort
 * @author ccottap
 *
 */
public class SelectionSort extends SortingAlgorithm {



	/* (non-Javadoc)
	 * @see sortingAlgorithms.SortingAlgorithm#getName()
	 */
	@Override
	public String getName() {
		return "Selection Sort";
	}


	/* (non-Javadoc)
	 * @see sortingAlgorithms.SortingAlgorithm#sort(int[],int,int)
	 */
	@Override
	public <E extends Comparable<? super E>> void sort(List<E> A, int l, int r) {
		if (A == null || A.size() <= l || A.size() <= r || l >= r)  // Condiciones 
			throw new RuntimeException("parametros incorrectos");
		
		for (int i = l; i <= r; i++){			
			int min = i;//Elegimos el primer elemento "l"
			for (int j=i+1;j <= r;j++) {
				if (A.get(j).compareTo(A.get(min))< 0) min = j;//Buscamos en las posiciones posteriores si hay menores
			}//end for
			
			swap(A, i, min);
		}//end for
	}//end sort

}

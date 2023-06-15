import java.util.List;

/**
 * Bubble sort
 * @author ccottap
 *
 */
public class BubbleSort extends SortingAlgorithm {

    /* (non-Javadoc)
	 * @see sortingAlgorithms.SortingAlgorithm#getName()
	 */
	@Override
	public String getName() {
		return "BubbleSort";
	}


	/* (non-Javadoc)
	 * @see sortingAlgorithms.SortingAlgorithm#sort(int[],int,int)
	 */
	@Override
	public <E extends Comparable<? super E>> void sort(List<E> A, int l, int r) {
		if (A == null || A.size() <= l || A.size() <= r || l >= r) //Condiciones 
			throw new RuntimeException("parametros incorrectos");
		
		for (int i = l; i <= r ; i++){//Recorro lista desde l hasta r
			for (int j = r; j > i ;j--) {//Recorro lista desde r hasta hasta i 
				if (A.get(j).compareTo(A.get(j-1)) < 0) swap(A,j,j-1);
				
			}//end for
		}//end for
	}//end sort

	
}//end class

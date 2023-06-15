
import java.util.List;

/**
 * Generic superclass of Sorting Algorithms
 * @author ccottap
 *
 */
public abstract class SortingAlgorithm {
	/**
	 * Sorts a list 
	 * @param <E> class of the elements in the list
	 * @param a a list
	 */
    public <E extends Comparable<? super E>> void sort (List<E> a)
    {
    	sort (a, 0, a.size()-1);
    }
    
	/**
	 * Sorts a list from position l up to r
	 * @param <E> class of the elements in the list
	 * @param a a list
	 * @param l index of the leftmost position to sort
	 * @param r index of the rightmost position to sort
	 */
    public abstract <E extends Comparable<? super E>> void sort (List<E> a, int l, int r);
    

    /**
     * Gets the name of the sorting method
     * @return a string with the name of the sorting method
     */
    public abstract String getName ();
    
    /**
     * Swaps the contents of two positions of a list
	 * @param <E> class of the elements in the list
     * @param A a list
     * @param i an index
     * @param j another index
     */
	protected <E> void swap (List<E> A, int i, int j) 
	{
		if (i!=j) {
			E tmp = A.get(i);
			A.set(i, A.get(j));
			A.set(j, tmp);
		}
	}

}

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Testing the running time of some basic sorting algorithms
 * @author ccottap
 *
 */
public class TestSortingAlgorithms {
	
	/**
	 * Factory method for generating a sorting algorithm given its name
	 * @param name the name of the sorting algorithm
	 * @return the sorting algorithm
	 */
	private static SortingAlgorithm getMethod (String name) {
		if (name.equals("quicksort"))
			return new QuickSort();
		else if (name.equals("mergesort"))
			return new MergeSort();
		/* else if (name.equals("selection"))
			return new SelectionSort();
		else if (name.equals("insertion"))
			return new InsertionSort();
		else if (name.equals("bubble"))
			return new BubbleSort(); */		
		else 
			return null;
	}
	
	/**
	 * default value for the initial array size when testing sorting algorithms
	 */
	private static final int defaultArraySize = 2000;
	/**
	 * default value for the number of times the array size is doubled when testing sorting algorithms
	 */
	private static final int defaultDoublings = 6;
	/**
	 * default value for the number of tests per array size when testing sorting algorithms
	 */
	private static final int defaultTests = 10;

	/**
	 * Main method to conduct the tests
	 * @param args command-line arguments: sorting method  (optional): size of the arrays, number of size doublings, and number of tests per size
	 * @throws IOException if the data cannot be written to file
	 */
	public static void main(String[] args) throws IOException {
		final int n1  = args.length > 1 ? Integer.parseInt(args[1]) : defaultArraySize; // initial array size
	    final int dup = args.length > 2 ? Integer.parseInt(args[2]) : defaultDoublings; // no. of times the size is doubled
	    final int nt  = args.length > 3 ? Integer.parseInt(args[3]) : defaultTests; 	// no. of tests per array size
	    final Random random = new Random(1);								// RNG
	    SortingAlgorithm method;											// sorting method
	    
	    if (args.length > 0) {
	    	method = getMethod(args[0]); 
	    	if (method == null) {
		    	System.out.println("ERROR: sorting method must be one of {selection, insertion, bubble}");
		    	System.exit(1);
	    	}
	    	
	        long[][] stats = new long [dup][nt+1]; // to store runtimes

	        for (int n=n1, k=0; k<dup; k++, n*=2) {
	        	ArrayList<Double> a = new ArrayList<Double>(n);

	        	System.out.println("n = " + n);

	        	stats[k][0] = n;

	        	for (int j = 0; j < nt; ++j) {
	        		for(int k1=0; k1<n; k1++) {
	           			a.add(random.nextDouble());
	            	}
	        		stats[k][j+1] = timeSorts(method, a);
	        		assert checkSorted(a) : "Sorting Error";
	        		a.clear();
	        	}
	        }
			writeStats (args[0]+".txt", stats);
	    }
	    else {
	    	System.out.println("Parameters:");
	    	System.out.println("\tsorting method:     selection, insertion, bubble, quicksort, mergesort");
	    	System.out.println("\tinitial size (opt): initial size of the array (default " + defaultArraySize + ")");
	    	System.out.println("\tdoublings    (opt): times the size is doubled (default " + defaultDoublings + ")");
	    	System.out.println("\ttests        (opt): test per array size (default " + defaultTests + ")");
	    	System.exit(1);
	    }
	    


        		
    }
	
	/**
	 * Tests whether a list is sorted in ascending order
	 * @param <E> the class of the elements in the list
	 * @param a a list
	 * @return true iff a is sorted in ascending order
	 */
	protected static <E extends Comparable<? super E>> boolean checkSorted (ArrayList<E> a) {
        boolean OK = true;

        for (int i=1; i<a.size(); i++)
        	if (a.get(i-1).compareTo(a.get(i))>0)
        		return false;
        
        return OK;
	}
	
	/**
	 * Sorts a list using a certain sorting algorithm and returns the time required
	 * @param <E> the class of the elements in the list
	 * @param algorithm a sorting algorithm
	 * @param a a list
	 * @return the time (ms) to sort the list
	 */
	protected static <E extends Comparable<? super E>> long timeSorts (SortingAlgorithm algorithm, List<E> a) {
	        long interval;
	        final long start = System.currentTimeMillis();
	        algorithm.sort(a);
	        final long finish = System.currentTimeMillis();
	        interval = finish - start;
	        System.out.println(algorithm.getName() + " took " + interval + "ms");
	        return interval;
	}

	/**
	 * Writes the stats to a file
	 * @param filename the name of the file
	 * @param stats a table with the time required: each row a size, each column a test (except the first column which is the size) 
	 * @throws IOException if the data cannot be written
	 */
    protected static void writeStats (String filename, long[][] stats) throws IOException {
    	PrintWriter out = new PrintWriter(new FileWriter(filename));
    	final int n1 = stats.length;
    	final int n2 = stats[0].length;
    	for (int i=0; i<n1; i++) {
			out.print(stats[i][0]);
    		for (int j=1; j<n2; j++)
    			out.print("\t" + stats[i][j]);
    		out.println();
    	}
    	out.close();
    }

}

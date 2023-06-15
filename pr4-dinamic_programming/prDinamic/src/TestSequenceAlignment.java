


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


/**
 * Test the alignment of two sequences
 * @author ccottap
 *
 */
public class TestSequenceAlignment {
	/**
	 * Filename for storing statistics
	 */
	private final static String outputFilename = "alignment.txt";
	
	/**
	 * Finds the best alignment of two strings
	 * @param args command-line arguments (to read data from file or generate at random)
	 * @throws IOException if data cannot be read from file or statistics cannot be written to file
	 */
	public static void main(String[] args) throws IOException {
		if (args.length<1) {
			System.out.println("java TestSequenceAlignment (-f|-g|-b|-t) <arguments>");
			System.out.println("\t-f: reads data from a file");
			System.out.println("\t-g: generates a problem instance at random with specified parameters");
			System.out.println("\t-b: performs a batch test");
			System.out.println("\t-t: measures time");
		}
		else {
			List<String> sequences = null;
			SequenceAlignment aligner = new NeedlemanWunsch();
			
			if (args[0].equals("-f")) {
				if (args.length < 2) {
					System.out.println("java TestSequenceAlignment -f <filename>");
					System.exit(-1);
				}
				else {
					sequences = SequenceUtil.readSequencesFromFile(args[1]);
					aligner.setVerbosity(true);
					List<String> s = aligner.run(sequences);

					System.out.println("Alignment: \n\t" + s.get(0) + "\n\t" + s.get(1));
					System.out.println("Score: " + aligner.evaluate(s));
				}
			}
			else if (args[0].equals("-g")) {
				if (args.length < 4) {
					System.out.println("java TestSequenceAlignment -g <lenght1> <lenght2> <alphabet_size> [<random seed>]");
					System.exit(-1);
				}
				else {
					if (args.length > 4) {
						SequenceUtil.setSeed(Integer.parseInt(args[4]));
					}
					int alphabetSize = Integer.parseUnsignedInt(args[3]);
					String s1 = SequenceUtil.randomString(Integer.parseUnsignedInt(args[1]), alphabetSize);
					String s2 = SequenceUtil.randomString(Integer.parseUnsignedInt(args[2]), alphabetSize);
					sequences = new ArrayList<String>(2);
					sequences.add(s1);
					sequences.add(s2);
					aligner.setVerbosity(true);
					List<String> s = aligner.run(sequences);

					System.out.println("Alignment: \n\t" + s.get(0) + "\n\t" + s.get(1));
					System.out.println("Score: " + aligner.evaluate(s));
				}
			}
			else if (args[0].equals("-b")) {
				if (args.length < 2) {
					System.out.println("java TestSequenceAlignment -b <filename>");
					System.exit(-1);
				}
				else {
					sequences = SequenceUtil.readSequencesFromFile(args[1]);
					runBatch(aligner, sequences);
				}
			}
			else if (args[0].equals("-t")) {
				if (args.length < 4) {
					System.out.println("java TestSequenceAlignment -t <initial-lenght> <doublings> <tests-per-length>");
					System.exit(-1);
				}
				else {
					int initialLength = Integer.parseUnsignedInt(args[1]);
					int doublings = Integer.parseUnsignedInt(args[2]);
					int testsPerLength = Integer.parseUnsignedInt(args[3]);
					long[][] statistics = runTimer(aligner, initialLength, doublings, testsPerLength);
					writestatistics(outputFilename, statistics);
				}
			}
			else {
				System.out.println("Wrong argument: " + args[0]);
				System.exit(-1);
			}
		}

	}

	/**
	 * Performs a series of timed experiments with sequences of different sizes
	 * @param aligner the alignment algorithm	 
	 * @param initialLength initial length of sequences
	 * @param doublings number of times the sequence length is doubled
	 * @param testsPerLength number of tests per sequence length
	 */
	private static long[][] runTimer(SequenceAlignment aligner, int initialLength, int doublings, int testsPerLength) {
		final int alphabetSize = 4;
		long[][] statistics = new long[doublings][testsPerLength+1];
		List<String> sequences = new ArrayList<String>(2);
		long start, finish, interval;
		
		aligner.setVerbosity(false);
		for (int l = initialLength, dup = 0; dup < doublings; l*=2, dup++) {
			statistics[dup][0] = l;
	        System.out.println("Trying sequences of length " + l);
			for (int j=0; j<testsPerLength; j++) {
				String s1 = SequenceUtil.randomString(l, alphabetSize);
				String s2 = SequenceUtil.randomString(l, alphabetSize);
				sequences.add(s1);
				sequences.add(s2);
		        start = System.currentTimeMillis();
				aligner.run(sequences);
		        finish = System.currentTimeMillis();
		        interval = finish - start;
		        statistics[dup][j+1] = interval;
		        System.out.println(aligner.getName() + " took " + interval + "ms");
				sequences.clear();
			}
		}
		
		return statistics;
		
	}

	/**
	 * Performs a series of alignments with pairs of adjacent sequences in the list
	 * @param aligner the alignment algorithm
	 * @param sequences a list of strings
	 */
	private static void runBatch(SequenceAlignment aligner, List<String> sequences) {
		int n = sequences.size()/2;
		List<String> s = new ArrayList<String>(2);
		
		aligner.setVerbosity(false);
		for (int i=0; i<n; i++) {
			s.add(sequences.get(2*i));
			s.add(sequences.get(2*i+1));
			List<String> sol = aligner.run(s);
			System.out.println(aligner.evaluate(sol));
			s.clear();
		}
		
	}
	
	/**
	 * Writes the statistics to a file
	 * @param filename the name of the file
	 * @param statistics a table with the time required: each row a size, each column a test (except the first column which is the size) 
	 * @throws IOException if the data cannot be written
	 */
    private static void writestatistics (String filename, long[][] statistics) throws IOException {
    	PrintWriter out = new PrintWriter(new FileWriter(filename));
    	final int n1 = statistics.length;
    	final int n2 = statistics[0].length;
    	for (int i=0; i<n1; i++) {
			out.print(statistics[i][0]);
    		for (int j=1; j<n2; j++)
    			out.print("\t" + statistics[i][j]);
    		out.println();
    	}
    	out.close();
    }

}



import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Utilities for sequence-based problems
 * @author ccottap
 *
 */
public class SequenceUtil {
	/**
	 * Class-level random number generator
	 */
	private static Random r = new Random(1);

	/**
	 * Creates a random string of the given length and alphabet size
	 * @param len the length of the string
	 * @param alphabet_size the size of the alphabet from which symbols are drawn
	 * @return a string of the given length and alphabet size
	 * @throws RuntimeException if the alphabet size is greater than 256
	 */
	public static String randomString(int len, int alphabet_size) throws RuntimeException {
		String s = "";
		int base = 0;
		
		if (alphabet_size <= 26) 
			base = (int)'a';
		else if (alphabet_size <= 94)
			base = (int)'!';
		else if (alphabet_size > 256)
			throw new RuntimeException("Alphabet size cannot exceed 256");
		
		for (int i=0; i<len; i++) {
			s = s + (char)(base + r.nextInt(alphabet_size));
		}
	
		return s;
	}

	/**
	 * Sets a new seed for the RNG
	 * @param seed the integer seed
	 */
	public static void setSeed(int seed) {
		r.setSeed(seed);
	}
	
	/**
	 * Reads a collection of sequences from a file
	 * @param filename the name of the file
	 * @return a list of sequences
	 * @throws FileNotFoundException if the file cannot be opened
	 */
	public static List<String> readSequencesFromFile (String filename) throws FileNotFoundException	{
		Scanner inputFile = new Scanner (new File(filename));
		ArrayList<String> seqs = new ArrayList<String>();
		
		while (inputFile.hasNextLine()) {
			seqs.add(inputFile.nextLine());
		}
		inputFile.close();
		return seqs;
	}

}

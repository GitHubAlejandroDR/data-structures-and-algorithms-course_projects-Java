

import java.util.List;

/**
 * Generic interface of a sequence-alignment algorithm
 * @author ccottap
 *
 */
public interface SequenceAlignment {
	/**
	 * Runs the algorithm on some sequence data
	 * @param sequences a list of sequences
	 * @return a list of sequences augmented with gaps and aligned to each other
	 */
	List<String> run(List<String> sequences);
	
	/**
	 * Evaluates the quality of an alignment according to the internal score used by the algorithm
	 * @param alignment a list of strings, where the symbol '-' denotes a gap
	 * @return the quality of the alignment
	 */
	int evaluate(List<String> alignment);
	
	/**
     * Gets the name of the alignment method
     * @return a string with the name of the alignment method
     */
    public String getName ();
	
	/**
	 * Defines the verbosity level of the algorithm
	 * @param verbosity boolean value to activate (if true) / deactivate (if false) verbosity
	 */
	void setVerbosity(boolean verbosity);

}

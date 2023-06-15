

import java.util.ArrayList;
import java.util.List;

/**
 * Optimal alignment of two sequences using Needleman-Wunsch algorithm
 * @author ccottap
 *
 */
public class NeedlemanWunsch implements SequenceAlignment {
	/**
	 * score for matching positions
	 */
	protected int matchScore = 1;
	/**
	 * score for mismatching positions
	 */
	protected int mismatchScore = -1;
	/**
	 * score for opening a gap
	 */
	protected int indelScore = -1;
	/**
	 * verbosity of the algorithm
	 */
	protected boolean verbosity = false;
	

	/**
	 * Default constructor
	 */
	public NeedlemanWunsch() {
	}

	/**
	 * Gets the match score used
	 * @return the matchScore
	 */
	protected int getMatchScore() {
		return matchScore;
	}

	/**
	 * Sets the value of the match score
	 * @param matchScore the value to set
	 */
	protected void setMatchScore(int matchScore) {
		this.matchScore = matchScore;
	}

	/**
	 * Gets the mismatch score used
	 * @return the mismatchScore
	 */
	protected int getMismatchScore() {
		return mismatchScore;
	}

	/**
	 * Sets the value of the mismatch score
	 * @param mismatchScore the value to set
	 */
	protected void setMismatchScore(int mismatchScore) {
		this.mismatchScore = mismatchScore;
	}

	/**
	 * Gets the indel score used 
	 * @return the indelScore
	 */
	protected int getIndelScore() {
		return indelScore;
	}

	/**
	 * Sets the value of the indel score
	 * @param indelScore the value to set
	 */
	protected void setIndelScore(int indelScore) {
		this.indelScore = indelScore;
	}

	@Override
	public List<String> run(List<String> sequences) {
		if (sequences.size() != 2) {
			System.err.println("Error: expected two strings as input, and received " + sequences.size());
			System.err.println(sequences);
			return null;
		}
		
		return findAlignment(sequences.get(0), sequences.get(1));
	}
	


	/**
	 * Implements Needleman-Wunsch algorithm
	 * @param s1 a string
	 * @param s2 another string
	 * @return the best alignment
	 */
	private List<String> findAlignment(String s1, String s2) {
			int n1 = s1.length();						// length of the first sequence
			int n2 = s2.length();						// length of the second sequence
			int[][] S = new int[n1+1][n2+1];			// to store scores: S[i][j] = best alignment of s1[i..n1-1] and s2[j..n2-1]
			char[][] D = new char[n1+1][n2+1];			// to store decisions 
			List<String> s = new ArrayList<String>(2);	// the final alignment
			 
			/* 
			 * Computation of the optimal value
			 * 
			 * Fill matrices S and D (scores and decisions) as shown in slides 6 and 7
			 * 
			 * You can follow the following steps.
			 * 
			 */
									
			// Step 1: fill matrices S and D for i=n1 and j=n2
			S[n1][n2] = 0;
			D[n1][n2] = '0';
			
			// Step 2: fill matrices S and D for 0<=i<n1 and j=n2			
			for (int i = 0; i < n1 ; i++) {
				S[i][n2] = 0;
				
				for (int k = i; k < n1; k++) S[i][n2] += getIndelScore();
				
				D[i][n2] = 'v';
			}
			
			// Step 3: fill matrices S and D for 0<=j<n2 and i=n1
			for (int j = 0; j < n2 ; j++) {
				S[n1][j] = 0;
				
				for (int k = j; k < n2; k++) S[n1][j] += getIndelScore();
				
				D[n1][j] = '>';
			}
			
			// Step 3: fill matrices S and D for 0<=i<n1 and 0<=j<n2
			for (int i = n1-1; i >= 0; i--) {
				for (int j = n2-1; j >= 0; j--) {
					int s_i_j1 = S[i][j+1]+ getIndelScore();
					int s_i1_j = S[i+1][j]+ getIndelScore();
					int s_i1_j1 = S[i+1][j+1] + (s1.charAt(i) == s2.charAt(j)? getMatchScore() : getMismatchScore());
					//System.out.print(s_i_j1+":"+s_i1_j+":"+s_i1_j1);
					if (s_i_j1 >= s_i1_j && s_i_j1 >= s_i1_j1) {
						S[i][j] = s_i_j1;
						D[i][j] = '>';
					}else if (s_i1_j >= s_i_j1 && s_i1_j>= s_i1_j1) {
						S[i][j] = s_i1_j;
						D[i][j] = 'v';
					}else {
						S[i][j] = s_i1_j1;
						D[i][j] = '\\';
					}
					//System.out.println("-->" + S[i][j]);
				}
			}

			// Print S and D matrices
			if (verbosity) {
				System.out.println("\nScore matrix:\n");


				System.out.print(" \t|");
				for (int j=0; j<=n2; j++)
					System.out.print("\t"+j);
				System.out.print("\n   \t|");
				for (int j=0; j<n2; j++)
					System.out.print("\t"+s2.charAt(j));
				System.out.print("\t[END]\n---- \t+");
				for (int j=0; j<=n2; j++)
					System.out.print("\t----");
				for (int i=0; i<=n1; i++) {
					if (i<n1)
						System.out.print("\n"+i + " " + s1.charAt(i)+"\t|\t");
					else
						System.out.print("\n" + i + "[END]\t|\t");
					for (int j=0; j<=n2; j++) {
						System.out.format("%d \t", S[i][j]);
					}
				}

				System.out.println("\n\nDecision matrix:\n");

				System.out.print(" \t|\t");
				for (int j=0; j<=n2; j++)
					System.out.print(j%10);
				System.out.print("\n   \t|\t");
				for (int j=0; j<n2; j++)
					System.out.print(s2.charAt(j));
				System.out.print("[END]\n---- \t+\t");
				for (int j=0; j<=n2; j++)
					System.out.print("-");
				for (int i=0; i<=n1; i++) {
					if (i<n1)
						System.out.print("\n"+i + " " + s1.charAt(i)+"\t|\t");
					else
						System.out.print("\n" + i + "[END]\t|\t");
					for (int j=0; j<=n2; j++) {
						System.out.format("%c", D[i][j]);
					}
				}
				System.out.println("\n");
			}
			
			/* 
			 * Reconstruction of the optimal solution
			 * 
			 * Iterate over the D matrix as explained in slide 8
			 * 
			 */
			String s1_aligned = "";
			String s2_aligned = "";
			int i = 0;
			int j = 0;
			
		    while(D[i][j] != '0') {
		    	switch (D[i][j]) {
		    		case '>':
		    			s1_aligned += '-';
		    			s2_aligned += s2.charAt(j);
		    			j++;
		    			break;
		    		case 'v':
		    			s1_aligned += s1.charAt(i);
		    			s2_aligned += '-';;
		    			i++;
		    			break;
		    		default:
		    			s1_aligned += s1.charAt(i);
		    			s2_aligned += s2.charAt(j);
		    			i++;
		    			j++;
		    			break;
		    	}//end switch
		    }//end while
			
			s.add(s1_aligned);
			s.add(s2_aligned);
			
			return s;
	}

	@Override
	public int evaluate(List<String> alignment) {
		if (alignment.size() != 2) {
			System.err.println("Error: expected two strings as input, and received " + alignment.size());
			System.err.println(alignment);
			return Integer.MIN_VALUE;
		}
		
		String s1 = alignment.get(0);
		String s2 = alignment.get(1);
		int l = s1.length();
		if (l != s2.length()) {
			System.err.println("Error: the two strings do not have the same length");
			System.err.println("s1 (" + l + ") = " + s1);
			System.err.println("s2 (" + s2.length() + ") = " + s2);
			return Integer.MIN_VALUE;
		}
		int score = 0;
		for (int i=0; i<l; i++) {
			char c1 = s1.charAt(i);
			char c2 = s2.charAt(i);
			if (c1 == c2)
				score += matchScore;
			else if (c1 == '_')
				score += indelScore;
			else
				score += mismatchScore;
		}
		
		return score;
	}

	@Override
	public void setVerbosity(boolean verbosity) {
		this.verbosity = verbosity;
		
	}

	@Override
	public String getName() {
		return "Needleman-Wunsch Algorithm";
	}

}

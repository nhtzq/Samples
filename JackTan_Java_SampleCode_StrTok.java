/*  
 * Author: Jack Tan
 *
 * Deliverable: class which breaks up a string into tokens delimited by any of a set of characters.
 * For example, given the string "username=kilroy&password=secret" and the
 * delimiters "&=", successive calls to the nextToken method would return
 * "username", "kilroy", "password", "secret", and null.
 *   
 */
 
public class StrTok {
    private String s;
    private String delims;
	private int beginOfToken; // Index of the beginning of the next token
	private int endOfToken; // Index of the end of the next token

    /**
     * Constructs a new string tokenizer object.
     * @param s  string to be broken up into tokens
     * @param delims  set of delimiter characters (as a string)
     */
    public StrTok (String s, String delims) {
        if (s == null || delims == null)
            throw new IllegalArgumentException ("String and delimiters must not be null");
        this.s = s;
        this.delims = delims;
		beginOfToken = 0;
		endOfToken = -1;
    }

    /** Returns the next token in the string, or null if there are no more tokens. */
    public String nextToken() {
        // TODO
    	if (beginOfToken == -1) { // It means the search of the whole string s has finished
    		return null;
    	}
    	else {
    		beginOfToken = indexOfNonDelims(endOfToken + 1);
        	endOfToken = beginOfToken == -1 ? -1 : indexOfDelims(beginOfToken);	
        	if (beginOfToken != -1 && endOfToken == -1) { // It means the current search cannot find any delimiter at the end
        		endOfToken = s.length();
        	}
        	return beginOfToken == -1? null : s.substring(beginOfToken, endOfToken);
    	}
    }
    
    // Find out if a char with the index idxS in s is one of the delimiters, return true if it's a delimiter, otherwise false
    public boolean isDelims(int idxS) {
    	for (int idxD = 0; idxD < delims.length(); idxD++) {
    		if (s.charAt(idxS) == delims.charAt(idxD)) {
    			return true;
    		}
    	}
    	return false;
    }
    
    // Return the index of the first occurrence of any delimiter character in s that is greater than or equal to the fromIdx, or -1 if the character does not occur
    public int indexOfDelims(int fromIdx) {
    	int delimIdx = s.length();
    	for (int idxD = 0; idxD < delims.length(); idxD++) {
    		if (s.indexOf(delims.charAt(idxD), fromIdx) != -1) {
    			delimIdx = delimIdx > s.indexOf(delims.charAt(idxD), fromIdx) ? s.indexOf(delims.charAt(idxD), fromIdx) : delimIdx;
    		}
    	}
    	return delimIdx == s.length() ? -1 : delimIdx;
    }
    
    // Return the index of the first occurrence of any non delimiter character in s that is greater than or equal to the fromIdx, or -1 if the non delimiter character does not occur
    public int indexOfNonDelims(int fromIdx) {
    	for (int idxS = fromIdx; idxS < s.length(); idxS++) {
    		if (!isDelims(idxS)) {
    			return idxS;
    		}
    	}
    	return -1;
    }
    
    public static void main(String[] args) {
    	String s = "- This, a sample string.";
    	String delims = " ,.-";
    	StrTok st = new StrTok(s, delims);
    	// Try to call it 10 times
    	for (int i = 0; i < 10; i++) {
    		System.out.println("round: " + (i + 1));
    		System.out.println(st.nextToken() + "\n");
    	}
	}
}
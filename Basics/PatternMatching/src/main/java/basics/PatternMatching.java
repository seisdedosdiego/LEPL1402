package basics;

public class PatternMatching {

    /**
     * Look for one sequence of characters (the pattern) in an input
     * string, and return the position of the pattern in the string
     * (if present). If the pattern is present multiple times in the
     * string, the function must return the leftmost occurrence of the
     * pattern (i.e. the occurrence whose index is the lowest). The
     * function must be case-sensitive (i.e. <code>Hello</code> is not
     * the same as <code>hello</code>).
     * @param pattern The pattern to look for.
     * @param value The string to look in.
     * @result The index of the leftmost occurrence of the pattern in
     * the string. Must be <code>-1</code> if the pattern is absent
     * from the string.
     **/
    public static int find(String pattern,
                           String value) {
        for (int i=0 ; i<=value.length()-pattern.length() ; i++) {
            boolean pass = true;
            for (int j=0 ; j<pattern.length() ; j++) {
                    if (pattern.charAt(j) != value.charAt(i+j)) {
                        pass = false;
                        break;
                    }
            }
            if (pass) {
                return i;
            }
        }
        return -1;
    }

}

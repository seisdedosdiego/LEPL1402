package algorithms;

import java.util.Arrays;

public class Anagram {

    /**
     * Count the number of occurrences of each letter of the alphabet
     * (from 'A' to 'Z') in the given string. The function must return
     * an array containing 26 elements: The item with index 0 contains
     * the number of 'A' in the string, the item with index 1 contains
     * the number of 'B', and so on.
     *
     * This function must be case-insensitive, i.e. the character 'f'
     * must be considered as the same as character 'F'.
     *
     * Characters that are neither in the range 'a' to 'z', nor in the
     * range 'A' to 'Z' must be ignored.
     *
     * The time complexity of your method must be Theta(n),
     * where n is the size of the string.
     * 
     * @param s The string of interest.
     * @return An array counting the number of occurrences of each
     * letter.
     **/
    public static int[] countAlphabet(String s) {
        String ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        int[] res = new int[26];
        for (int i=0 ; i<s.length() ; i++) {
            char letter = s.charAt(i);
            if (ALPHA.indexOf(letter) != -1) {
                res[ALPHA.indexOf(letter)] += 1;
            } else if (alpha.indexOf(letter) != -1) {
                res[alpha.indexOf(letter)] += 1;
            }
        }
        return res;
    }


    /**
     * Check whether one string is an anagram of another string. It is
     * strongly advised to use the function "countAlphabet()" as a
     * building block for function "isAnagram()".
     *
     * The time complexity of your method must be O(n+m),
     * where n is the size of the first string, and m is the size of
     * the second string.
     *
     * @param s1 The first string.
     * @param s2 The second string.
     * @return <code>true</code> iff. the two strings are anagrams.
     **/
    public static boolean isAnagram(String s1,
                                    String s2) {
        int[] x1 = countAlphabet(s1);
        int[] x2 = countAlphabet(s2);

        return Arrays.equals(x1, x2);
    }
}

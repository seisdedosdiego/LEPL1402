package basics;

import static org.junit.jupiter.api.DynamicContainer.dynamicContainer;

public class StringUtils {


     /**
     * Split a string according to a delimiter
     *
     * @param str The string to split
     * @param delimiter The delimiter
     * @return An array containing the substring which fall
     *          between two consecutive occurence of the delimiter.
     *          If there is no occurence of the delimiter, it should
     *          return an array of size 1 with the string at element 0
     */
     public static String [] split(String str, char delimiter){
          int delimiterCount = 1;

          for (char cha : str.toCharArray()) {
               if (cha == delimiter) {
                    delimiterCount++;
               }
          }

          String[] res = new String[delimiterCount];

          int current = 0;
          int index = 0;
          String sub = "";
          while (current<str.length()) {
               if (str.charAt(current) == delimiter) {
                    res[index] = sub;
                    index++;
                    sub = "";
               } else {
                    sub += str.charAt(current);
               }
               current++;
          }
          res[index] = sub;

          return res;
     }


     /**
     * Find the first occurence of a substring in a string
     *
     * @param str The string to look in
     * @param sub The string to look for
     * @return The index of the start of the first appearance of
     *          the substring in str or -1 if sub does not appear
     *          in str
     */
     public static int indexOf(String str, String sub){
          for (int i=0; i<str.length()-sub.length()+1; i++) {
               boolean found = true;
               for (int j=0; j<sub.length(); j++) {
                    if (sub.charAt(j) != str.charAt(i+j)) {
                         found = false;
                    }
               } 
               if (found) {return i;}
          }
          return -1;
     }


     /**
     * Convert a string to lowercase
     *
     * @param str The string to convert
     * @return A new string, same as str but with every
     *          character put to lower case.
     */
     public static String toLowerCase(String str){
          return str.toLowerCase();
     }


     /**
     * Check if a string is a palyndrome
     *
     * A palyndrome is a sequence of character that is the
     * same when read from left to right and from right to
     * left.
     *
     * @param str The string to check
     * @return true if str is a palyndrome, false otherwise
     */
     public static boolean palindrome(String str){
          String reverse = "";
          for (int i=0; i<str.length(); i++) {
               reverse += String.valueOf(str.charAt(str.length()-i-1));
          }
          return (reverse.equals(str));
     }

}
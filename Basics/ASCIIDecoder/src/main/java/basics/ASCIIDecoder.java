package basics;
import java.util.ArrayList;

public class ASCIIDecoder {

    /*
     * The 2D array "sentences" contain a set of decimal ASCII code we want you
     * to translate. Each sub-element of this array is a different sentence.
     * Ex : if we pass this array : [ ["72", "101", "108", "108", "111"], ["87", "111", "114", "108", "100"]]
     * to your decode method, you should return : [ "Hello", "World" ].
     * 
     * Forbidden characters are passed as an array of int.
     * Each element of this array correspond to the decimal ASCII code
     * of a forbidden character OR null if there's no forbidden character
     * If you encounter one of these forbidden character
     * you must ignore it when you translate your sentence.
     *
     * Use the StringBuilder class and its method appendCodePoint(int) to translate the ASCII code.
     *
     * You should NEVER return null or an array containing null.
     */
    public static String[] decode(int[] forbidden, String[][] sentences) {
        ArrayList<String> results = new ArrayList<>();
        for (int i = 0; i < sentences.length; i++) {
            StringBuilder decodedSentence = new StringBuilder();
            for (int j = 0; j < sentences[i].length; j++) {
                int currentChar = Integer.parseInt(sentences[i][j]);
                boolean forbid = false;
                if (forbidden != null) {
                    for (int k = 0; k < forbidden.length; k++) {
                        if (currentChar == forbidden[k]) {
                            forbid = true;
                            break;
                        }
                    }
                }
                if (!forbid) {
                    decodedSentence.appendCodePoint(currentChar);
                }
            }
            /*System.out.println(decodedSentence);*/
            results.add(decodedSentence.toString());
        }
        /*System.out.println(results);*/
        return results.toArray(new String[0]);
    }

}
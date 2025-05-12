package basics;

public class IntroductionExercises {

     public static int variable = 0;

     public static int[] squares;

     /*
     * Function that bound variable to value
     */
     public static void attribute(int value){
          variable = value;
     }

     /*
     * Function that return the addition of the two parameters
     */
     public static int add(int a, int b){
          return a+b;
     }

     /*
     * return true is a and b are equal
     */
     public static boolean equalsIntegers(int a, int b){
          if (a == b) {
               return true;
          }
          return false;
     }

     /*
     * Function that return the max between a and b in one line
     * You must use a ternary operation
     */
     public static int max(int a, int b){
          return (a>b) ? a : b ;
     }

     /*
     * Function that return the middle value.
     * If a > b > c, the function must return b.
     * If two value are equals, return -1.
     */
     public static int middleValue(int a, int b, int c){
          if (a==b && a==c && b==c) {
               return -1;
          }
          return a + b + c - max(a, max(b,c)) - Math.min(a, Math.min(b,c));
     }
     /*
          * This function must return :
          * "Good morning, sir!" if str is "Morning"
          * "Good evening, sir!" if str is "Evening"
          * "Hello, sir!" otherwise
          * Use a switch case statement
          * Your implementation must be case sensitive
          * And you should not use if/else!
          */
     public static String greetings(String str){
          switch (str) {
               case "Morning": 
                    return "Good morning, sir!";
               case "Evening":
                    return "Good evening, sir!";
               default: 
                    return "Hello, sir!";
          }
     }

     /*
          * This function must return a new array of length 3
          * The first element of this new array is the last element of a
          * The second element is the first element of a
          * The last element is the middle element of a
          */
     public static int[] lastFirstMiddle(int[] a){
          int[] array = new int[3];
          array[0] = a[a.length-1];
          array[1] = a[0];
          array[2] = a[a.length/2];
          return array;
     }

     /*
          * This function must return the sum of the elements of array using a for loop
          */
     public static int sum(int[] array){
          int sum = 0;
          for (int num : array) {
               sum += num;
          }
          return sum;
     }

     /*
          * return the maximum element of array using a while loop
          */
     public static int maxArray(int[] array){
          int max = Integer.MIN_VALUE;
          int i = 0;
          while (i<array.length) {
               if (max < array[i]) {max = array[i];} 
               i++;
          }
          return max;
     }


     /**
          * Assign to the variable square, the square of the
          * parameters.
          *
          * Let assume that the program is invoked with the following
          * line:
          *  java IntroductionExercises 0 3 4 5
          *
          * The arguments of the program are 0, 3, 4 and 5.
          * After the execution of the main, the variable squares
          * should be:
          *  squares = [0, 9, 16, 25]
          *
          * If an exception occurs when converting an argument to
          * an integer, put 0 at the corresponding index. For example
          *
          *  java IntroductionExercise 0 3.1 4 5
          *
          * would yield
          *
          *  squares = [0, 0, 16, 25]
          *
          * because 3.1 can not be converted to an integer
          * */
     public static void main(String... args){
          squares = new int[args.length];

          if (args != null) {
               for (int i=0; i<args.length; i++) {
                    try {
                         int val = Integer.parseInt(args[i]);
                         squares[i] = val*val;
                    } catch (NumberFormatException e) {
                         squares[i] = 0;
                    }
               }
          }
     }

}
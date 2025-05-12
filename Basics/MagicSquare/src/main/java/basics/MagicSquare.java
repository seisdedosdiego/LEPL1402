package basics;

import java.util.HashSet;
import java.util.Set;


public class MagicSquare {


    /**
     * A magic square is an (n x n) matrix such that:
     *
     * - all the positive numbers 1,2, ..., n*n are present (thus each number appears exactly once)
     * - the sums of the numbers in each row, each column and both main diagonals are the same
     *
     *   For instance a 3 x 3 magic square is the following
     *
     *   2 7 6
     *   9 5 1
     *   4 3 8
     *
     *   You have to implement the method that verifies if a matrix is a valid magic square
     */

    /**
     *
     * @param matrix a square matrix of size n x n
     * @return true if matrix is a n x n magic square, false otherwise
     */
    public static boolean isMagicSquare(int [][] matrix) {

        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] <= 0 || matrix[i][j] > n*n) {
                    return false;
                }
            }
        }

        boolean check1 = rowsCheck(matrix);
        boolean check2 = columnsCheck(matrix);
        boolean check3 = diagonalsCheck(matrix);
        boolean check4 = numberCheck(matrix);
        return check1 && check2 && check3 && check4;
    }

    public static boolean rowsCheck(int[][] matrix){
        int length = matrix.length;
        int[] rowsSum = new int[length];
        for (int i=0 ; i<length ; i++) {
            int sum = 0;
            for (int elem : matrix[i]) {
                sum += elem;
            }
            rowsSum[i] = sum;
        }
        boolean x = true;
        for (int sum : rowsSum) {
            if (rowsSum[0] != sum) {
                x = false;
                break;
            }
        }
        return x;
    }

    public static boolean columnsCheck(int[][] matrix){
        int length = matrix[0].length;
        int[] columnsSum = new int[length];
        for (int i=0 ; i<length ; i++) {
            int sum = 0;
            for (int[] elem : matrix) {
                sum += elem[i];
            }
            columnsSum[i] = sum;
        }
        boolean x = true;
        for (int sum : columnsSum) {
            if (columnsSum[0] != sum) {
                x = false;
                break;
            }
        }
        return x;
    }

    public static boolean diagonalsCheck(int[][] matrix){
        int length = matrix.length;
        int[] diagonalsSum = new int[2];
        for (int i=0 ; i<length ; i++) {
            diagonalsSum[0] += matrix[i][i];
            diagonalsSum[1] += matrix[i][length-i-1];
        }
        return diagonalsSum[0] == diagonalsSum[1];
    }

    public static boolean numberCheck(int[][] matrix) {
        int nxn = matrix.length*matrix.length;
        int[] count = new int[nxn];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                int value = matrix[i][j];
                if (value < 1 || value > nxn) {
                    return false;
                }
                count[value - 1]++;
            }
        }
        for (int i = 0; i < nxn; i++) {
            if (count[i] != 1) {
                return false;
            }
        }
        return true;
    }
}

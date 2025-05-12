package basics;

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
        boolean check = false;
        int magicNumber = -1;
        int n = matrix.length;
        boolean[] numberPresence = new boolean[n*n];

        for(int[] row : matrix) {
            int rowSum = 0;
            for (int num : row) {
                if (0 < num && num < n*n+1) {
                    if (numberPresence[num-1] == false) {
                        numberPresence[num-1] = true;
                    }
                } else { return false; }
                rowSum += num;
            }
            if (check == false) {
                magicNumber = rowSum;
                check = true;
            } else {
                if (magicNumber != rowSum) { return false; }
            }
        }

        for (boolean num : numberPresence) {
            if (num != true) {
                return false;
            }
        }

        for(int i=0; i<n; i++) {
            int columnSum = 0;
            for (int j=0; j<n; j++) {
                columnSum += matrix[j][i];
            }
            if (columnSum != magicNumber) { return false; }
        } 

        int diagonal1Sum = 0;
        for(int i=0; i<n; i++) {
            diagonal1Sum += matrix[i][i];
        }
        if (diagonal1Sum != magicNumber) { return false; }

        int diagonal2Sum = 0;
        for(int i=0; i<n; i++) {
            diagonal2Sum += matrix[i][n-i-1];
        }
        if (diagonal2Sum != magicNumber) { return false; }

         return true;
    }
}

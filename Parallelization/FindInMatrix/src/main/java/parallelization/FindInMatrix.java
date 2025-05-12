package parallelization;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FindInMatrix {
    // You are allowed to add methods or class members, but do not change the signature
    // of the existing methods and class members.

    public static class Result {
        private int row;
        private List<Integer> columns;

        Result(int row,
               List<Integer> columns) {
            this.row = row;
            this.columns = columns;
        }

        public int getRow() {
            return row;
        }

        public List<Integer> getColumns() {
            return columns;
        }
    }

    /**
     * This method finds the row that contains the most number of occurrences of a
     * certain value and returns the row index of that row and the column indexes
     * (in increasing order) where the value occurs in that row.
     * The matrix is represented by a two-dimensional array.
     *
     * Example: if the method is called with the value "3" and the following matrix
     *     (1   3  2  -4)          // <- there is a "3" in column 1
     *     (-3  4  5  -2)
     *     (3   0  3   2)          // <- there is a "3" in column 0 and column 2
     * then the result of the search is:
     *      row=2 , columns=[0,2]
     * because row 2 contains the most number of occurrences of "3" (in columns 0 and 2).
     *
     * Your solution MUST use a thread pool to accelerate the operation.
     *
     * @param matrix a rectangular matrix
     * @param value the value to find
     * @param poolSize the thread pool size
     * @return the result of the search
     *
     * You can assume that there is exactly one row in the matrix with the
     * most number of occurrences of the value.
     * Catch exceptions and ignore them.
     */

    public static Result findValue(int[][] matrix, int value, int poolSize) {
        // TODO
        // Hint:
        // One row of the matrix -> One future.

        if (matrix == null || matrix.length == 0 || poolSize == 0) {
            throw new IllegalArgumentException();
        }

        ExecutorService threadPool = Executors.newFixedThreadPool(poolSize);
        Stack<Future<Result>> futures = new Stack<>();

        for (int row=0 ; row<matrix.length ; row++) {
            int[] currentRow = matrix[row];
            int currentRowIndex = row;

            futures.add(threadPool.submit(() -> {
                List<Integer> columns = new ArrayList<>();
                for (int col=0; col<currentRow.length ; col++) {
                    if (currentRow[col] == value) {
                        columns.add(col);
                    }
                }
                return new Result(currentRowIndex, columns);
            }));
        }

        threadPool.shutdown();

        Result bestResult = null;
        
        try {
            int maxOccurences = 0;
            while (!futures.isEmpty()) {
                Result result = futures.pop().get();
                if (result.getColumns().size() > maxOccurences) {
                    maxOccurences = result.getColumns().size();
                    bestResult = result;
                }
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

         return bestResult;
    }
}
package algorithms;


interface SwapList {
    /**
     * Get the number of elements in the list
     */
    int getSize();
    /**
     * Get the first element of the list
     */
    int getFirst();
    /**
     * Get the second element of the list
     */
    int getSecond();
    /**
     * Swap the first and second element of the list
     */
    void swap();
    /**
     * Take the first element and put it at the end of the list
     */
    void pop();
    /**
    boolean isSorted();

    /**
     * You have to sorts a list of integers in increasing order.
     * This ist is a bit special since you only have access to the first two elements.
     *
     * We expect your algorithm to run in O(n^2), where n is the size of the list.
     * Precisely, we expect that you make at MOST n^2 calls to list.pop() and at
     * MOST n^2 calls to list.swap().
     *
     * You will pass the tests if
     *
     * - The list is sorted AND
     * - You make less than n^2 calls to swap AND
     * - you make less than n^2 calls to pop
     *
     * @param list: a list of integers to be sorted.
     */
    public static void sort(SwapList list) {
        int n = list.getSize();

        // Perform n-1 iterations to ensure all elements are sorted.
        for (int iter = 0; iter < n - 1; iter++) {
            // Bubble up the largest element to its correct position for this iteration
            for (int i = 0; i < list.getSize()-iter-1; i++) {
                if (list.getFirst() > list.getSecond()) {
                    list.swap();
                }
                list.pop();
            }
            for (int i = 0; i <= iter; i++) {
                list.pop();
            }
        }
    }
}




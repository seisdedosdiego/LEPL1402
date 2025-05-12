package fp;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Various exercises with streams.
 **/
public class VariousStreams {

    /**
     * Count the number of strings in a stream that start with the provided character.
     */
    static public long countStringsWithFirstLetter(Stream<String> stream,
                                                   char firstLetter) {
        String prefix = String.valueOf(firstLetter);
        return stream.filter(s -> s.startsWith(prefix)).count();
    }


    /**
     * Convert a stream of strings either to uppercase or to
     * lowercase, depending on the value of the "uppercase" argument.
     */
    static public Stream<String> changeCase(Stream<String> stream,
                                            boolean uppercase) {
         return stream.map(s -> uppercase ? s.toUpperCase() : s.toLowerCase());
    }


    /**
     * Compute the sum of all the even numbers inside the provided
     * stream if "isEven" is "true", or the sum of all the odd numbers
     * inside the provided stream if "isEven" is "false".
     */
    static public int getSumOfEvenOrOddNumbers(Stream<Integer> stream,
                                               boolean isEven) {
         return stream.filter(n -> isEven ? n%2 == 0 : n%2 != 0).reduce(0, (a,b) -> a+b);
    }


    /**
     * Remove the duplicates out of a stream of integers.
     *
     * Hint: Check out the JavaDoc of "Stream<T>", there is one method
     * that is especially well suited!
     */
    static public Stream<Integer> removeDuplicates(Stream<Integer> stream) {
         return stream.distinct();
    }


    /**
     * Sort a stream of strings, either in ascending order (if
     * "isAscending" is "true"), or in descending order (if
     * "isAscending" is "false").
     */
    static public Stream<String> sortAscendingOrDescending(Stream<String> stream,
                                                           boolean isAscending) {
         return stream.sorted(isAscending ? Comparator.naturalOrder() : Comparator.reverseOrder());
    }


    /**
     * Compute the average value of a stream of integer numbers. If
     * the stream is empty, return 0.0.
     *
     * Hint: Check out the "average()" method of specialized class
     * DoubleStream, and of the "orElse()" method of OptionalDouble
     * class.
     */
    static public double computeAverage(Stream<Integer> stream) {
         return stream.mapToInt(Integer::intValue).average().orElse(0.0);
    }


    /**
     * Class that wraps a pair of integers corresponding to a minimum
     * value and to a maximum value.
     */
    static public class MinMaxValue {
        private int minValue;
        private int maxValue;

        public MinMaxValue(int minValue,
                           int maxValue) {
            this.minValue = minValue;
            this.maxValue = maxValue;
        }

        int getMinValue() {
            return minValue;
        }

        int getMaxValue() {
            return maxValue;
        }
    }


    /**
     * Compute the minimum and the maximum value in a stream of
     * integers.  If the stream is empty, the resulting Optional must
     * have "isPresent()" answer "false".
     *
     * Hint: Use "map()" to create "MinMaxValue", then use "reduce()".
     */
    static public Optional<MinMaxValue> computeMinMaxValue(Stream<Integer> stream) {
        
        return stream.map(x -> Optional.of(new MinMaxValue(x, x))).reduce(Optional.empty(),
        (a, b) -> a.isPresent() ?
                Optional.of(new MinMaxValue(
                        Math.min(a.get().getMinValue(), b.get().getMinValue()),
                        Math.max(a.get().getMaxValue(), b.get().getMaxValue()))) :
                b);
    }


    /**
     * Generate the infinite stream of Fibonacci numbers, starting at 2.
     * This sequence corresponds to: [ 2, 3, 5, 8, 13, 21, 34, 55, 89, 144... ]
     *
     * Hint: Use the "generator()" method of "Stream<T>" with a supplier.
     */
    public static Stream<Integer> generateFibonacci() {
        return Stream.generate(new Supplier<Integer>() {
            private int previous = 1;
            private int current = 1;

            @Override
            public Integer get() {
                int backup = current;
                current = previous + current;
                previous = backup;
                return current;
            }
        });
    }
}

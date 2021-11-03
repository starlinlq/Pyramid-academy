import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    /**
     * Returns the largest absolute value in the array of numbers.
     *
     * @param numbers the input array of String integer numbers
     * @return the maximum integer absolute value in the array
     */
    public static int maxAbsValue(String[] numbers) {
        // write your code here
        return Arrays.stream(numbers).mapToInt(Integer::parseInt).map(Math::abs).reduce(0, Math::max);

    }

    // Don't change the code below
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(maxAbsValue(scanner.nextLine().split("\\s+")));

        List<Integer> list = List.of(1, 2, 3, 4);
        System.out.println( list.stream().max(Integer::compareTo));
        IntStream stream = IntStream.of(1,2,3,5);
    }
}
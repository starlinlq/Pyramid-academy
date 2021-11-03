import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Utils {

    public static List<Integer> sortOddEven(List<Integer> numbers) {
        List<Integer> odds = new ArrayList<Integer>(numbers);
        List<Integer> even = new ArrayList<>(numbers);
        odds.removeIf(number->number% 2 == 0);
        even.removeIf(number-> number% 2 !=0 );
        Collections.sort(odds);
        even.sort(Collections.reverseOrder());
        odds.addAll(even);
        return odds;
    }
}
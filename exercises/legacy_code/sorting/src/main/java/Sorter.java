import java.util.ArrayList;
import java.util.List;

// Do not edit this class
public class Sorter {
    public static List<String> sort(List<String> list) {
        List<String> result = new ArrayList<>(list);
        result.sort((a, b) -> {
            try {
                return Integer.compare(
                    Integer.valueOf(a),
                    Integer.valueOf(b));
            } catch (NumberFormatException e) {
                return 0;
            }
        });
        return result;
    }
}

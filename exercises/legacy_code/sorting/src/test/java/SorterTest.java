import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class SorterTest {
    // given a pair of a numeric-string and a number-string, sorts the numeric-string first
    @Test
    public void givenANumericStringAndALargerNumericStringSortsTheSmallerFirst() {
        assertEquals(List.of("1", "2"), Sorter.sort(List.of("2", "1")));
    }
}

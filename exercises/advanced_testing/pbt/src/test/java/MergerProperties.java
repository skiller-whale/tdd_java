import net.jqwik.api.*;
import net.jqwik.api.constraints.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MergerProperties {

    // Property: The merged output should always be in non-decreasing order.
    //
    // This currently passes.
    @Property
    void mergedOutputIsSorted(
            @ForAll List<@IntRange(min = 0, max = 100) Integer> aRaw,
            @ForAll List<@IntRange(min = 0, max = 100) Integer> bRaw) {

        List<Integer> a = sorted(aRaw);
        List<Integer> b = sorted(bRaw);

        List<Integer> merged = Merger.merge(a, b);

        for (int k = 1; k < merged.size(); k++) {
            assertTrue(merged.get(k - 1) <= merged.get(k),
                "expected sorted output but found " + merged);
        }
    }

    // TODO: the merged list should contain every element from both inputs, so its length should
    // equal a.size() + b.size().
    // Add an assertion to check that, then run `gradle test`.
    @Property
    void mergePreservesLength(
            @ForAll List<@IntRange(min = 0, max = 100) Integer> aRaw,
            @ForAll List<@IntRange(min = 0, max = 100) Integer> bRaw) {

        List<Integer> a = sorted(aRaw);
        List<Integer> b = sorted(bRaw);

        List<Integer> merged = Merger.merge(a, b);

        // TODO: assert something about merged.size()
    }

    // TODO: the merged list should contain exactly the same elements as the two inputs combined. 
    // Sorting both sides and comparing is one easy way to check this.
    // Add an assertion and run the tests.
    @Property
    void mergePreservesAllElements(
            @ForAll List<@IntRange(min = 0, max = 100) Integer> aRaw,
            @ForAll List<@IntRange(min = 0, max = 100) Integer> bRaw) {

        List<Integer> a = sorted(aRaw);
        List<Integer> b = sorted(bRaw);

        List<Integer> merged = Merger.merge(a, b);

        // TODO: assert merged holds the same elements as a and b combined
    }

    // Helper: returns a sorted copy of the given list, so we always feed Merger valid (sorted) input.
    private static List<Integer> sorted(List<Integer> list) {
        List<Integer> copy = new ArrayList<>(list);
        Collections.sort(copy);
        return copy;
    }
}

import net.jqwik.api.*;
import net.jqwik.api.constraints.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Property-based tests for Merger.merge.
//
// Instead of hand-picking examples, we describe properties that should hold for *every* pair of
// sorted inputs, and let jqwik generate hundreds of cases trying to break them. When a property
// fails, jqwik shrinks the failure to the smallest counter-example it can find — read it carefully.
public class MergerProperties {

    // A worked example: the merged output should always be in non-decreasing order.
    //
    // Notice this property PASSES even though Merger has a bug. One property on its own is rarely
    // enough — the properties you add below tell a fuller story.
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
    // equal a.size() + b.size(). Add an assertion to check that, then run `gradle test` and read
    // the shrunk counter-example.
    @Property
    void mergePreservesLength(
            @ForAll List<@IntRange(min = 0, max = 100) Integer> aRaw,
            @ForAll List<@IntRange(min = 0, max = 100) Integer> bRaw) {

        List<Integer> a = sorted(aRaw);
        List<Integer> b = sorted(bRaw);

        List<Integer> merged = Merger.merge(a, b);

        // TODO: assert something about merged.size()
    }

    // TODO: the merged list should contain exactly the same elements as the two inputs combined
    // (the same "multiset" — same values, same number of each). Sorting both sides and comparing
    // is one easy way to check this. Add the assertion, run the tests, and read the shrunk case.
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

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EvaluateGuessTest {

    @Test
    void correct() {
        assertEquals("ggggg", EvaluateGuess.evaluateGuess("crane", "crane"));
    }

    @Test
    void incorrect() {
        assertEquals("-----", EvaluateGuess.evaluateGuess("bumps", "crane"));
    }

    @Test
    void yellowLetters() {
        assertEquals("yy-yy", EvaluateGuess.evaluateGuess("acorn", "crane"));
    }

    @Test
    void mixed() {
        assertEquals("-ggyg", EvaluateGuess.evaluateGuess("grace", "crane"));
    }

    @Test
    void duplicateLetters() {
        assertEquals("yg---", EvaluateGuess.evaluateGuess("error", "crane"));
    }
}

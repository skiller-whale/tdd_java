import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class ValidateGuess2Test {

    @Nested
    class WithAValidGuess {
        @Test
        void returnsValidForARecognised5LetterWord() {
            ValidateGuess.Result result = ValidateGuess.validateGuess("crane", List.of("crane", "rates"));
            assertTrue(result.valid());
        }
    }

    @Nested
    class WithAnInvalidGuess {
        @Test
        void returnsInvalidWithAReasonForAGuessThatIsTooShort() {
            ValidateGuess.Result result = ValidateGuess.validateGuess("cran", List.of("crane", "rates"));
            assertFalse(result.valid());
            assertEquals("Guess must be 5 letters", result.reason());
        }

        @Test
        void returnsInvalidWithAReasonForNonLetterCharacters() {
            ValidateGuess.Result result = ValidateGuess.validateGuess("cr4ne", List.of("crane", "rates"));
            assertFalse(result.valid());
            assertEquals("Guess must only contain letters", result.reason());
        }

        @Test
        void returnsInvalidWithAReasonForAGuessNotInTheWordList() {
            ValidateGuess.Result result = ValidateGuess.validateGuess("bumps", List.of("crane", "rates"));
            assertFalse(result.valid());
            assertEquals("Not a recognised word", result.reason());
        }
    }
}

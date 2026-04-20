import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class ValidateGuessTest {
    @Test
    public void returnsValidForA5LetterWordInTheWordList() {
        assertEquals(new ValidateGuess.Result(true, null),
            ValidateGuess.validateGuess("crane", List.of("crane", "audio")));
    }

    @Test
    public void returnsInvalidForAWordThatIsNot5Letters() {
        assertEquals(new ValidateGuess.Result(false, "Guess must be 5 letters"),
            ValidateGuess.validateGuess("cr", List.of("crane", "audio")));
    }

    @Test
    public void returnsInvalidForAWordContainingNonLetterCharacters() {
        assertEquals(new ValidateGuess.Result(false, "Guess must only contain letters"),
            ValidateGuess.validateGuess("cr4ne", List.of("crane", "audio")));
    }

    @Test
    public void returnsInvalidForAWordNotInTheWordList() {
        assertEquals(new ValidateGuess.Result(false, "Not a recognised word"),
            ValidateGuess.validateGuess("audio", List.of("crane")));
    }
}

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class ValidateGuess1Test {

    @Test
    void validatesAndInvalidatesGuessesCorrectly() {
        List<String> words = List.of("crane", "rates", "towns");
        assertTrue(ValidateGuess.validateGuess("crane", words).valid());
        assertFalse(ValidateGuess.validateGuess("cran", words).valid());
        assertFalse(ValidateGuess.validateGuess("cr4ne", words).valid());
        assertFalse(ValidateGuess.validateGuess("bumps", words).valid());
    }

    @Test
    void returnsAReasonForInvalidGuesses() {
        List<String> words = List.of("crane");
        ValidateGuess.Result tooShort = ValidateGuess.validateGuess("cran", words);
        assertTrue(tooShort.reason() != null);
        ValidateGuess.Result nonLetter = ValidateGuess.validateGuess("cr4ne", words);
        assertTrue(nonLetter.reason() != null);
        ValidateGuess.Result notInList = ValidateGuess.validateGuess("bumps", words);
        assertNotNull(notInList.reason());
    }
}

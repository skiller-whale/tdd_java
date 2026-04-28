import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class WordleGameTest {

    @Nested
    class Constructor {
        @Test
        void initializesTheGameState() {
            List<String> wordList = List.of("whale", "water", "fishy", "skill");
            WordleGame game = new WordleGame("whale", wordList);
            assertEquals("whale", game.correctAnswer);
            assertEquals(wordList, game.wordList);
            assertEquals(List.of(), game.guesses);
            assertEquals(List.of(), game.evaluations);
        }

        @Test
        void throwsAnErrorIfTheAnswerIsNotInTheWordList() {
            List<String> wordList = List.of("whale", "water", "fishy", "skill");
            assertThrows(Exception.class, () -> new WordleGame("crane", wordList));
        }
    }

    @Nested
    class SubmitGuess {
        @Test
        void correctlyEvaluatesTheGuess() {
            List<String> wordList = List.of("whale", "water", "fishy", "skill");
            WordleGame game = new WordleGame("whale", wordList);
            game.submitGuess("water");
            assertEquals(EvaluateGuess.evaluateGuess("water", "whale"), game.evaluations.get(0));
        }

        @Test
        void doesNotAcceptFurtherGuessesAfterTheGameIsOver() {
            List<String> wordList = List.of("whale", "water", "fishy", "skill");
            WordleGame game = new WordleGame("whale", wordList);
            game.submitGuess("whale");
            game.submitGuess("water");
            assertEquals(1, game.guesses.size());
        }

        @Test
        void throwsAnErrorForInvalidGuesses() {
            List<String> wordList = List.of("whale", "water", "fishy", "skill");
            WordleGame game = new WordleGame("whale", wordList);
            assertThrows(Exception.class, () -> game.submitGuess("crane"));
        }
    }
}

import java.util.List;

public class ValidateGuess {

    public record Result(boolean valid, String reason) {}

    /**
     * Validates a word before accepting it as a Wordle guess.
     *
     * @param word     The word to validate.
     * @param wordList The list of valid 5-letter words.
     * @return a Result with valid=true, or valid=false and a reason string.
     */
    public static Result validateGuess(String word, List<String> wordList) {
        if (word.length() != 5) {
            return new Result(false, "Guess must be 5 letters");
        }
        if (!word.matches("[a-z]+")) {
            return new Result(false, "Guess must only contain letters");
        }
        if (!wordList.contains(word)) {
            return new Result(false, "Not a recognised word");
        }
        return new Result(true, null);
    }
}

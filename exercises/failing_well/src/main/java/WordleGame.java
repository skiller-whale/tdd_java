import java.util.ArrayList;
import java.util.List;

public class WordleGame {

    public String correctAnswer;
    public List<String> wordList;
    public List<String> guesses = new ArrayList<>();
    public List<String> evaluations = new ArrayList<>();

    /**
     * Creates a new game of Wordle.
     *
     * @param correctAnswer The correct answer for this game of Wordle.
     * @param wordList      The list of valid words that can be guessed.
     * @throws IllegalArgumentException If the correct answer is not in the word list.
     */
    public WordleGame(String correctAnswer, List<String> wordList) {
        if (!wordList.contains(correctAnswer)) {
            throw new IllegalArgumentException("Answer must be in the word list");
        }
        this.correctAnswer = correctAnswer;
        this.wordList = wordList;
    }

    /**
     * Submits a guess. If valid, the guess is evaluated and added to the game
     * state. If the game is already over, the guess is silently ignored. If the
     * guess is invalid, an exception is thrown.
     *
     * @param guess The guessed word.
     * @throws IllegalArgumentException If the guess is invalid.
     */
    public void submitGuess(String guess) {
        if ((!guesses.isEmpty() && guesses.get(guesses.size() - 1).equals(correctAnswer))
                || guesses.size() >= 6) {
            return;
        }
        ValidateGuess.Result validationResult = ValidateGuess.validateGuess(guess, wordList);
        if (!validationResult.valid()) {
            throw new IllegalArgumentException(validationResult.reason());
        }
        String evaluation = EvaluateGuess.evaluateGuess(guess, correctAnswer);
        guesses.add(guess);
        evaluations.add(evaluation);
    }
}

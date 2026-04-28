public class EvaluateGuess {

    /**
     * Determines which letters in a Wordle guess are correct (green), present but
     * in the wrong place (yellow), or not present at all (gray).
     *
     * @param guess         The guessed word.
     * @param correctAnswer The correct word.
     * @return A string representing the evaluation of each letter:
     *         'g' for green, 'y' for yellow, '-' for gray.
     */
    public static String evaluateGuess(String guess, String correctAnswer) {
        char[] evaluationArray = "-----".toCharArray();
        char[] remainingArray = correctAnswer.toCharArray();

        // check for greens
        for (int i = 0; i < 5; i++) {
            if (correctAnswer.charAt(i) == guess.charAt(i)) {
                evaluationArray[i] = 'g';
                remainingArray[i] = '\0';
            }
        }

        // check for yellows
        for (int i = 0; i < 5; i++) {
            if (correctAnswer.charAt(i) != guess.charAt(i)) {
                for (int j = 0; j < remainingArray.length; j++) {
                    if (remainingArray[j] == guess.charAt(i)) {
                        evaluationArray[i] = 'y';
                        remainingArray[j] = '\0';
                        break;
                    }
                }
            }
        }

        return new String(evaluationArray);
    }
}

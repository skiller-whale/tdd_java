import java.util.List;

public class GetGameStatus {
    public static String getGameStatus(List<String> guesses, String target) {
        if (!guesses.isEmpty() && guesses.get(guesses.size() - 1).equals(target)) return "won";
        if (guesses.size() >= 6) return "lost";
        return "in_progress";
    }
}

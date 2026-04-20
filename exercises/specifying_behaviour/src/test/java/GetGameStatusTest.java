import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class GetGameStatusTest {
    @Test
    public void returnsWonWhenTheLastGuessMatchesTheTarget() {
        assertEquals("won", GetGameStatus.getGameStatus(List.of("crane"), "crane"));
    }

    @Test
    public void returnsLostWhen6WrongGuessesHaveBeenMade() {
        assertEquals("lost", GetGameStatus.getGameStatus(
            List.of("audio", "ghost", "plumb", "fizzy", "words", "crane"), "blank"));
    }

    @Test
    public void returnsInProgressWhenFewerThan6GuessesHaveBeenMade() {
        assertEquals("in_progress", GetGameStatus.getGameStatus(List.of("audio"), "ghost"));
    }
}

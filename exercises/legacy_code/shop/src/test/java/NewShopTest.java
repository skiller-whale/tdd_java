import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class NewShopTest {
    @Test
    public void sourdoughStarterNeverDecreasesInQuality() {
        Item item = new Item("Sourdough Starter", 5, 80);
        new NewShop(List.of(item)).updateQuality();
        assertEquals(80, item.quality);
    }

    @Test
    public void sourdoughStarterNeverChangesItsSellInDate() {
        Item item = new Item("Sourdough Starter", 5, 80);
        new NewShop(List.of(item)).updateQuality();
        assertEquals(5, item.sellIn);
    }
}

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

// This suite looks fairly thorough and is fully green, but it still leaves some important
// behaviour untested. Your job in the exercise is to find those gaps with mutation testing.
public class TillTest {

    private final Till till = new Till();

    @Test
    public void emptyOrderChargesNothingAndEarnsNoPoints() {
        Receipt receipt = till.checkout(List.of(), false);
        assertEquals(0.0, receipt.total(), 0.001);
        assertEquals(0, receipt.loyaltyPoints());
    }

    @Test
    public void smallOrderHasDeliveryFeeAdded() {
        Receipt receipt = till.checkout(List.of(new LineItem("Croissant", 3, 2.00)), false);
        assertEquals(9.50, receipt.total(), 0.001);
        assertEquals(6, receipt.loyaltyPoints());
    }

    @Test
    public void bulkDiscountAppliedWhenBuyingPlentyOfAnItem() {
        Receipt receipt = till.checkout(List.of(new LineItem("Roll", 20, 1.00)), false);
        assertEquals(21.50, receipt.total(), 0.001);
        assertEquals(18, receipt.loyaltyPoints());
    }

    @Test
    public void noBulkDiscountForAModestQuantity() {
        Receipt receipt = till.checkout(List.of(new LineItem("Roll", 8, 1.00)), false);
        assertEquals(11.50, receipt.total(), 0.001);
        assertEquals(8, receipt.loyaltyPoints());
    }

    @Test
    public void membersGetAnExtraDiscountOffTheWholeOrder() {
        Receipt receipt = till.checkout(List.of(new LineItem("Cake", 1, 20.00)), true);
        assertEquals(22.50, receipt.total(), 0.001);
    }

    @Test
    public void largeOrderShipsForFree() {
        Receipt receipt = till.checkout(List.of(new LineItem("Cake", 2, 15.00)), false);
        assertEquals(30.00, receipt.total(), 0.001);
        assertEquals(30, receipt.loyaltyPoints());
    }

    @Test
    public void multipleLinesAreSummedWithBulkDiscountPerLine() {
        Receipt receipt = till.checkout(
            List.of(
                new LineItem("Croissant", 2, 2.00),
                new LineItem("Roll", 12, 1.00)
            ),
            false
        );
        assertEquals(18.30, receipt.total(), 0.001);
        assertEquals(14, receipt.loyaltyPoints());
    }
}

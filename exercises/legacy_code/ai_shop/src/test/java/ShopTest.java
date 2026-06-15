import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class ShopTest {

    @Nested
    class NormalItems {
        @Test
        public void decreasesSellInBy1EachDay() {
            Item item = new Item("Bread", 5, 20);
            new Shop(List.of(item)).updateQuality();
            assertEquals(4, item.sellIn);
        }

        @Test
        public void decreasesQualityBy1BeforeTheSellByDate() {
            Item item = new Item("Bread", 5, 20);
            new Shop(List.of(item)).updateQuality();
            assertEquals(19, item.quality);
        }

        @Test
        public void decreasesQualityBy2OnTheSellByDateSellIn0() {
            Item item = new Item("Bread", 0, 20);
            new Shop(List.of(item)).updateQuality();
            assertEquals(18, item.quality);
        }

        @Test
        public void decreasesQualityBy2EachDayAfterTheSellByDate() {
            Item item = new Item("Bread", -3, 20);
            new Shop(List.of(item)).updateQuality();
            assertEquals(18, item.quality);
        }

        @Test
        public void continuesToDecreaseSellInWhenQualityIs0() {
            Item item = new Item("Bread", 5, 0);
            new Shop(List.of(item)).updateQuality();
            assertEquals(4, item.sellIn);
        }

        @Test
        public void qualityNeverDropsBelow0BeforeTheSellByDate() {
            Item item = new Item("Bread", 5, 0);
            new Shop(List.of(item)).updateQuality();
            assertEquals(0, item.quality);
        }

        @Test
        public void qualityNeverDropsBelow0OnTheSellByDate() {
            Item item = new Item("Bread", 0, 1);
            new Shop(List.of(item)).updateQuality();
            assertEquals(0, item.quality);
        }

        @Test
        public void qualityNeverDropsBelow0AfterTheSellByDate() {
            Item item = new Item("Bread", -1, 1);
            new Shop(List.of(item)).updateQuality();
            assertEquals(0, item.quality);
        }

        @Test
        public void degradesCorrectlyOverMultipleDaysSpanningTheSellByDate() {
            Item item = new Item("Bread", 2, 10);
            Shop shop = new Shop(List.of(item));
            shop.updateQuality(); // sellIn 1, quality 9
            shop.updateQuality(); // sellIn 0, quality 8
            shop.updateQuality(); // sellIn -1, quality 6
            shop.updateQuality(); // sellIn -2, quality 4
            assertEquals(4, item.quality);
            assertEquals(-2, item.sellIn);
        }
    }

    @Nested
    class FruitCake {
        @Test
        public void decreasesSellInBy1() {
            Item item = new Item("Fruit Cake", 5, 10);
            new Shop(List.of(item)).updateQuality();
            assertEquals(4, item.sellIn);
        }

        @Test
        public void increasesQualityBy1BeforeTheSellByDate() {
            Item item = new Item("Fruit Cake", 5, 10);
            new Shop(List.of(item)).updateQuality();
            assertEquals(11, item.quality);
        }

        @Test
        public void increasesQualityBy2OnTheSellByDateSellIn0() {
            Item item = new Item("Fruit Cake", 0, 10);
            new Shop(List.of(item)).updateQuality();
            assertEquals(12, item.quality);
        }

        @Test
        public void increasesQualityBy2AfterTheSellByDate() {
            Item item = new Item("Fruit Cake", -2, 10);
            new Shop(List.of(item)).updateQuality();
            assertEquals(12, item.quality);
        }

        @Test
        public void qualityNeverExceeds50() {
            Item item = new Item("Fruit Cake", 5, 50);
            new Shop(List.of(item)).updateQuality();
            assertEquals(50, item.quality);
        }

        @Test
        public void qualityIsCappedAt50BeforeTheSellByDate() {
            Item item = new Item("Fruit Cake", 5, 49);
            new Shop(List.of(item)).updateQuality();
            assertEquals(50, item.quality);
        }

        @Test
        public void qualityIsCappedAt50AfterTheSellByDate() {
            Item item = new Item("Fruit Cake", -1, 49);
            new Shop(List.of(item)).updateQuality();
            assertEquals(50, item.quality);
        }

        @Test
        public void increasesQualityOverManyDaysSpanningTheSellByDate() {
            Item item = new Item("Fruit Cake", 2, 40);
            Shop shop = new Shop(List.of(item));
            shop.updateQuality(); // +1, sellIn 1, quality 41
            shop.updateQuality(); // +1, sellIn 0, quality 42
            shop.updateQuality(); // +2, sellIn -1, quality 44
            shop.updateQuality(); // +2, sellIn -2, quality 46
            assertEquals(46, item.quality);
        }
    }

    @Nested
    class SourdoughStarter {
        @Test
        public void neverChangesQuality() {
            Item item = new Item("Sourdough Starter", 0, 80);
            new Shop(List.of(item)).updateQuality();
            assertEquals(80, item.quality);
        }

        @Test
        public void neverChangesTheSellInDate() {
            Item item = new Item("Sourdough Starter", 0, 80);
            new Shop(List.of(item)).updateQuality();
            assertEquals(0, item.sellIn);
        }

        @Test
        public void remainsCompletelyUnchangedAfterManyDays() {
            Item item = new Item("Sourdough Starter", 0, 80);
            Shop shop = new Shop(List.of(item));
            for (int i = 0; i < 30; i++) {
                shop.updateQuality();
            }
            assertEquals(80, item.quality);
            assertEquals(0, item.sellIn);
        }
    }

    @Nested
    class WeddingCake {
        @Test
        public void decreasesSellInBy1() {
            Item item = new Item("Wedding Cake", 15, 20);
            new Shop(List.of(item)).updateQuality();
            assertEquals(14, item.sellIn);
        }

        @Test
        public void increasesQualityBy1WithMoreThan10DaysRemaining() {
            Item item = new Item("Wedding Cake", 15, 20);
            new Shop(List.of(item)).updateQuality();
            assertEquals(21, item.quality);
        }

        @Test
        public void increasesQualityBy1WithExactly11DaysRemaining() {
            Item item = new Item("Wedding Cake", 11, 20);
            new Shop(List.of(item)).updateQuality();
            assertEquals(21, item.quality);
        }

        @Test
        public void increasesQualityBy2WithExactly10DaysRemaining() {
            Item item = new Item("Wedding Cake", 10, 20);
            new Shop(List.of(item)).updateQuality();
            assertEquals(22, item.quality);
        }

        @Test
        public void increasesQualityBy2With6DaysRemaining() {
            Item item = new Item("Wedding Cake", 6, 20);
            new Shop(List.of(item)).updateQuality();
            assertEquals(22, item.quality);
        }

        @Test
        public void increasesQualityBy3WithExactly5DaysRemaining() {
            Item item = new Item("Wedding Cake", 5, 20);
            new Shop(List.of(item)).updateQuality();
            assertEquals(23, item.quality);
        }

        @Test
        public void increasesQualityBy3With1DayRemaining() {
            Item item = new Item("Wedding Cake", 1, 20);
            new Shop(List.of(item)).updateQuality();
            assertEquals(23, item.quality);
        }

        @Test
        public void dropsQualityTo0OnTheSellByDateSellIn0() {
            Item item = new Item("Wedding Cake", 0, 20);
            new Shop(List.of(item)).updateQuality();
            assertEquals(0, item.quality);
        }

        @Test
        public void qualityStays0AfterTheSellByDate() {
            Item item = new Item("Wedding Cake", -1, 0);
            new Shop(List.of(item)).updateQuality();
            assertEquals(0, item.quality);
        }

        @Test
        public void sellInContinuesToDecreaseAfterTheSellByDate() {
            Item item = new Item("Wedding Cake", -1, 0);
            new Shop(List.of(item)).updateQuality();
            assertEquals(-2, item.sellIn);
        }

        @Test
        public void qualityNeverExceeds50() {
            Item item = new Item("Wedding Cake", 5, 50);
            new Shop(List.of(item)).updateQuality();
            assertEquals(50, item.quality);
        }

        @Test
        public void qualityIsCappedAt50WithHighRateIncreases() {
            Item item = new Item("Wedding Cake", 5, 48);
            new Shop(List.of(item)).updateQuality();
            assertEquals(50, item.quality);
        }

        @Test
        public void qualityIsCappedAt50WithModerateRateIncreases() {
            Item item = new Item("Wedding Cake", 10, 49);
            new Shop(List.of(item)).updateQuality();
            assertEquals(50, item.quality);
        }

        @Test
        public void simulatesAFullRunUpToTheWedding() {
            Item item = new Item("Wedding Cake", 15, 5);
            Shop shop = new Shop(List.of(item));

            // Days 15-11: +1/day for 5 days = +5
            for (int i = 0; i < 5; i++) shop.updateQuality();
            assertEquals(10, item.quality);
            assertEquals(10, item.sellIn);

            // Days 10-6: +2/day for 5 days = +10
            for (int i = 0; i < 5; i++) shop.updateQuality();
            assertEquals(20, item.quality);
            assertEquals(5, item.sellIn);

            // Days 5-1: +3/day for 5 days = +15
            for (int i = 0; i < 5; i++) shop.updateQuality();
            assertEquals(35, item.quality);
            assertEquals(0, item.sellIn);

            // Day 0: quality drops to 0
            shop.updateQuality();
            assertEquals(0, item.quality);
            assertEquals(-1, item.sellIn);
        }
    }

    @Nested
    class MultipleItems {
        @Test
        public void updatesAllItemsInASingleCall() {
            Item bread = new Item("Bread", 3, 10);
            Item fruitCake = new Item("Fruit Cake", 3, 10);
            Item sourdough = new Item("Sourdough Starter", 0, 80);
            Item weddingCake = new Item("Wedding Cake", 8, 20);

            new Shop(List.of(bread, fruitCake, sourdough, weddingCake)).updateQuality();

            assertEquals(9, bread.quality);
            assertEquals(11, fruitCake.quality);
            assertEquals(80, sourdough.quality);
            assertEquals(22, weddingCake.quality);
        }
    }

    @Nested
    class ReturnValue {
        @Test
        public void returnsTheSameItemsList() {
            List<Item> items = List.of(new Item("Bread", 5, 10));
            List<Item> result = new Shop(items).updateQuality();
            assertSame(items, result);
        }
    }
}

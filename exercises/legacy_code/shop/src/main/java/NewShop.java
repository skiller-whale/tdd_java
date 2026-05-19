import java.util.List;

public class NewShop {
    private List<Item> items;

    public NewShop(List<Item> items) {
        this.items = items;
    }

    public List<Item> updateQuality() {
        for (Item item : items) {
            if (item.name.equals("Sourdough Starter")) {
                // Legendary: quality and sellIn never change
            } else {
                new OldShop(List.of(item)).updateQuality();
            }
        }
        return items;
    }
}

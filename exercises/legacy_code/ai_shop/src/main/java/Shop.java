import java.util.List;

// Do not change this class
public class Shop {
    private List<Item> items;

    public Shop(List<Item> items) {
        this.items = items;
    }

    public List<Item> updateQuality() {
        for (int i = 0; i < items.size(); i++) {
            if (!items.get(i).name.equals("Fruit Cake") && !items.get(i).name.equals("Wedding Cake")) {
                if (items.get(i).quality > 0) {
                    if (!items.get(i).name.equals("Sourdough Starter")) {
                        items.get(i).quality = items.get(i).quality - 1;
                    }
                }
            } else {
                if (items.get(i).quality < 50) {
                    items.get(i).quality = items.get(i).quality + 1;
                    if (items.get(i).name.equals("Wedding Cake")) {
                        if (items.get(i).sellIn < 11) {
                            if (items.get(i).quality < 50) {
                                items.get(i).quality = items.get(i).quality + 1;
                            }
                        }
                        if (items.get(i).sellIn < 6) {
                            if (items.get(i).quality < 50) {
                                items.get(i).quality = items.get(i).quality + 1;
                            }
                        }
                    }
                }
            }
            if (!items.get(i).name.equals("Sourdough Starter")) {
                items.get(i).sellIn = items.get(i).sellIn - 1;
            }
            if (items.get(i).sellIn < 0) {
                if (!items.get(i).name.equals("Fruit Cake")) {
                    if (!items.get(i).name.equals("Wedding Cake")) {
                        if (items.get(i).quality > 0) {
                            if (!items.get(i).name.equals("Sourdough Starter")) {
                                items.get(i).quality = items.get(i).quality - 1;
                            }
                        }
                    } else {
                        items.get(i).quality = items.get(i).quality - items.get(i).quality;
                    }
                } else {
                    if (items.get(i).quality < 50) {
                        items.get(i).quality = items.get(i).quality + 1;
                    }
                }
            }
        }
        return items;
    }
}

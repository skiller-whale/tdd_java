import java.util.List;

public class Till {

    private static final int BULK_THRESHOLD = 10;
    private static final double BULK_DISCOUNT = 0.10;
    private static final double MEMBER_DISCOUNT = 0.05;
    private static final double FREE_DELIVERY_THRESHOLD = 25.0;
    private static final double DELIVERY_FEE = 3.50;

    public Receipt checkout(List<LineItem> items, boolean member) {
        double subtotal = 0.0;
        for (LineItem item : items) {
            double lineTotal = item.quantity() * item.unitPrice();
            if (item.quantity() > BULK_THRESHOLD) {
                lineTotal -= lineTotal * BULK_DISCOUNT;
            }
            subtotal += lineTotal;
        }

        if (member) {
            subtotal -= subtotal * MEMBER_DISCOUNT;
        }

        double delivery = DELIVERY_FEE;
        if (subtotal >= FREE_DELIVERY_THRESHOLD) {
            delivery = 0.0;
        }
        if (subtotal == 0.0) {
            delivery = 0.0;
        }

        int points = (int) subtotal;
        if (member) {
            points *= 2;
        }

        double total = round(subtotal + delivery);
        return new Receipt(total, points);
    }

    private static double round(double amount) {
        return Math.round(amount * 100.0) / 100.0;
    }
}

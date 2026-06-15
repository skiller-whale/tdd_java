import java.util.List;

// The till for The Bun & Board bakery. It totals up an order, applies the shop's
// discounts and delivery rules, and works out how many loyalty points the customer earns.
//
// During the exercise you will deliberately 'mutate' this class to test your tests,
// then revert your changes. The behaviour it is supposed to have is:
//
//   * Each line costs quantity * unitPrice.
//   * Bulk discount: 10% off a line when more than 10 of that item are bought.
//   * Loyalty discount: members get a further 5% off the whole order.
//   * Delivery: free when the discounted subtotal is 25.00 or more, otherwise 3.50.
//     An empty order (subtotal of 0.00) is never charged for delivery.
//   * Loyalty points: 1 point per whole pound of the discounted subtotal, doubled for members.
//   * The charged total is rounded to the nearest penny.
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

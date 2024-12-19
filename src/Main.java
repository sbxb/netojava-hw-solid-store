import app.Cart;
import app.Inventory;
import datasource.FileDataSource;
import order.OrderProcessor;
import payment.CreditCardPayment;

public class Main {
    public static void main(String[] args) {
        System.out.println("1. Inventory before purchase");
        Inventory.init(new FileDataSource("./inventory_small.txt"));
        Inventory inventory = Inventory.get();
        inventory.printAvailableItems();

        System.out.println("\n\n2. Shopping cart");
        Cart cart = new Cart();
        cart.addItem(inventory.getItemByName("Alpha"), 15);
        cart.addItem(inventory.getItemByName("Beta"), 20);
        cart.addItem(inventory.getItemByName("Gamma"), 4000);
        cart.addItem(inventory.getItemByName("Beta"), 6);
        cart.addItem(inventory.getItemByName("Nonexistent"), 5);
        cart.printAvailableItems();

        System.out.println("\n\n3. Creating order");
        OrderProcessor op = new OrderProcessor();
        int orderId = op.createOrder(cart);
        op.printOrderInfo(orderId);

        System.out.println("\n\n4. Paying order");
        if (op.payOrder(orderId, new CreditCardPayment())) {
            System.out.println("Order #" + orderId + " successfully paid");
        }

        System.out.println("\n\nInventory after purchase");
        inventory.printAvailableItems();
    }
}

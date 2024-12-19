import app.Cart;
import app.Inventory;
import datasource.FileDataSource;
import order.OrderProcessor;

public class Main {
    public static void main(String[] args) {
        System.out.println("1. Inventory");
        //Inventory.init(new FileDataSource("./inventory.txt"));
        Inventory.init(new FileDataSource("./inventory_small.txt"));
        Inventory inventory = Inventory.get();
        inventory.printAvailableItems();

        System.out.println("2. Shopping cart");
        Cart cart = new Cart();
        cart.addItem(inventory.getItemByName("Alpha"), 15);
        cart.addItem(inventory.getItemByName("Beta"), 20);
        cart.addItem(inventory.getItemByName("Gamma"), 4000);
        cart.addItem(inventory.getItemByName("Beta"), 6);
        cart.addItem(inventory.getItemByName("Nonexistent"), 5);
        cart.printAvailableItems();

        System.out.println("3. Making order");
        OrderProcessor op = new OrderProcessor();
        int orderId = op.createOrder(cart);
        op.printOrderInfo(orderId);

        System.out.println("Cart AFTER");
        cart.printAvailableItems();

        System.out.println("Inventory AFTER");
        inventory.printAvailableItems();
    }
}

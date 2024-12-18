import app.Cart;
import app.Inventory;
import datasource.DataSource;
import datasource.FileDataSource;
import order.OrderProcessor;

public class Main {
    public static void main(String[] args) {
        System.out.println("1. app.Inventory");
        DataSource ds = new FileDataSource("./inventory.txt");
        Inventory inventory = new Inventory(ds);
        inventory.printAvailableItems();

        System.out.println("2. Shopping cart");
        Cart cart = new Cart();
        cart.addItem(inventory.getItemByName("Book"), 1);
        cart.addItem(inventory.getItemByName("Towel"), 5);
        cart.printAvailableItems();

        System.out.println("3. Making order");
        OrderProcessor op = new OrderProcessor();
        int orderId = op.createOrder(cart);
        op.printOrderInfo(orderId);
    }
}

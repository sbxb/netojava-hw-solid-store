import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderProcessor {
    private static int orderIdIncrement = 0;
    private final Map<Integer, Order> orders = new HashMap<>();

    public int createOrder(Cart cart) {
        int id = ++orderIdIncrement;
        OrderStatus status = OrderStatus.UNPAID;
        List<Item> items = cart.getItems();
        cart.clear();
        Order order = new Order(id, status, items);
        orders.put(id, order);
        return id;
    }

    public void printOrderInfo(int id) {
        Order o = orders.get(id);
        if (o != null) {
            o.printAvailableItems();
        }
    }
}

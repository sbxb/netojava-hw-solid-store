package order;

import app.Cart;
import app.Inventory;
import app.Item;
import payment.Payment;

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
        syncOrderWithInventory(items);
        cart.clear();
        Order order = new Order(id, status, items);
        orders.put(id, order);
        return id;
    }

    private void syncOrderWithInventory(List<Item> items) {
        Inventory inventory = Inventory.get();
        for (Item cartItem : items) {
            Item inventoryItem = inventory.getItemById(cartItem.getId());
            if (inventoryItem.getQuantity() >= cartItem.getQuantity()) {
                inventoryItem.setQuantity(inventoryItem.getQuantity() - cartItem.getQuantity());
            } else {
                cartItem.setQuantity(inventoryItem.getQuantity());
                inventoryItem.setQuantity(0);
            }
        }
    }

    public boolean payOrder(int id, Payment pm) {
        Order o = orders.get(id);
        if (o != null && o.getStatus() == OrderStatus.UNPAID && pm.pay(0)) {
            o.setStatus(OrderStatus.PAID);
            return true;
        }
        return false;
    }

    public void printOrderInfo(int id) {
        Order o = orders.get(id);
        if (o != null) {
            o.printAvailableItems();
        }
    }
}

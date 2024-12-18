package order;

import app.Item;

import java.util.List;

public class Order {
    private int id;

    private OrderStatus status;
    private int customerId;
    private List<Item> items;

    public Order(int id, OrderStatus status, List<Item> items) {
        this.id = id;
        this.status = status;
        this.items = items;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void printAvailableItems() {
        for (Item item : items) {
            if (item.getQuantity() > 0) {
                System.out.printf("%3d | %10s | %6d | %4d\n", item.getId(), item.getName(), item.getPrice(), item.getQuantity());
            }
        }
    }


}

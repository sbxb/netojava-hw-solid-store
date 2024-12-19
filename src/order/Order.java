package order;

import app.Item;
import app.Reportable;

import java.util.List;

public class Order implements Reportable {
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
        printHeader();
        for (Item item : items) {
            if (item.getQuantity() > 0) {
                printItem(item);
            }
        }
    }


}

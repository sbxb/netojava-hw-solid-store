package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart implements Reportable, TotalAmountable {
    private final Map<Integer, Item> items = new HashMap<>();

    public void addItem(Item item, int quantity) {
        if (item == null) {
            return;
        }

        Item tmp;
        if ((tmp = items.get(item.getId())) != null) {
            tmp.setQuantity(tmp.getQuantity() + quantity);
        } else {
            item.setQuantity(quantity);
            items.put(item.getId(), item);
        }
    }

    public void printAvailableItems() {
        printHeader();
        for (Item item : items.values()) {
            if (item.getQuantity() > 0) {
                printItem(item);
            }
        }
    }

    public int getTotalAmount() {
        return items.values()
                .stream()
                .map(item -> item.getPrice() * item.getQuantity())
                .reduce(0, Integer::sum);
    }

    public List<Item> getItems() {
        return new ArrayList<>(items.values());
    }

    public void clear() {
        items.clear();
    }
}

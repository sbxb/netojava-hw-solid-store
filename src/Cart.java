import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {
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
        for (Item item : items.values()) {
            if (item.getQuantity() > 0) {
                System.out.printf("%3d | %10s | %6d | %4d\n", item.getId(), item.getName(), item.getPrice(), item.getQuantity());
            }
        }
    }

    public List<Item> getItems() {
        return new ArrayList<>(items.values());
    }

    public void clear() {
        items.clear();
    }
}

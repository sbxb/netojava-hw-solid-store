import java.util.HashMap;
import java.util.Map;

public class Cart {
    private final Map<Integer, Item> items = new HashMap<>();

    public void addItem(Item item, int quantity) {
        if (item != null) {
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
}

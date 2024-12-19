package app;

import datasource.DataSource;
import datasource.DataSourceException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory {
    private static Inventory inventory;
    private Map<Integer, Item> repo;

    private Inventory(Map<Integer, Item> repo) {
        this.repo = repo;
    }

    public static void init(DataSource ds) {
        List<Item> items;
        try {
            items = ds.getItems();
        } catch (DataSourceException e) {
            throw new RuntimeException("CRITICAL ERROR: cannot initialize inventory: " + e);
        }

        Map<Integer, Item> repo = new HashMap<>();
        for (Item i : items) {
            repo.put(i.getId(), i);
        }

        inventory = new Inventory(repo);
    }

    public static Inventory get() {
        if (inventory == null) {
            throw new RuntimeException("CRITICAL ERROR: inventory was not initialized");
        }
        return inventory;
    }

    public void printAvailableItems() {
        for (Item item : repo.values()) {
            if (item.getQuantity() > 0) {
                System.out.printf("%3d | %10s | %6d | %4d\n", item.getId(), item.getName(), item.getPrice(), item.getQuantity());
            }
        }
    }

    public Item getItemByName(String name) {
        for (Item item : repo.values()) {
            if (item.getName().equals(name)) {
                return new Item(item.getId(), item.getName(), item.getPrice());
            }
        }
        return null;
    }

    public Item getItemById(int id) {
        return repo.get(id);
    }
}

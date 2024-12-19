package app;

import datasource.DataSource;
import datasource.DataSourceException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory implements Reportable {
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
        printHeader();
        for (Item item : repo.values()) {
            printItem(item);
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

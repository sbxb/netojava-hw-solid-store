import java.util.List;

public class Inventory {
    private List<Item> items;


    public Inventory(DataSource ds) {
        try {
            this.items = ds.getItems();
        } catch (DataSourceException e) {
            throw new RuntimeException("ERROR: cannot create inventory" + e);
        }
    }

    public void printAvailableItems() {
        for (Item item : items) {
            if (item.getQuantity() > 0) {
                System.out.printf("%3d | %10s | %6d | %4d\n", item.getId(), item.getName(), item.getPrice(), item.getQuantity());
            }
        }
    }
}

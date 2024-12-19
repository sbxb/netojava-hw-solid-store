package app;

public interface Reportable {
    default void printHeader() {
        System.out.printf("%3s | %10s | %6s | %5s\n", "ID", "Name", "Price", "Quant.");
    }

    default void printItem(Item item) {
        System.out.printf("%3d | %10s | %6d | %5d\n", item.getId(), item.getName(),
                item.getPrice(), item.getQuantity());
    }

    public void printAvailableItems();
}

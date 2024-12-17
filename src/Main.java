import java.util.List;

public class Main {
    public static void main(String[] args) {
        DataSource ds = new FileDataSource("./inventory.txt");
        Inventory inventory = new Inventory(ds);
        inventory.printAvailableItems();
    }
}

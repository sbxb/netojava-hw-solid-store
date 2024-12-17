import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileDataSource implements DataSource {
    private final static int FIELDS_PER_RECORD = 4;
    private final static String FIELDS_SEPARATOR = ";";
    private final String filename;
    private final List<Item> items = new ArrayList<>();

    public FileDataSource(String filename) {
        this.filename = filename;
    }

    @Override
    public List<Item> getItems() throws DataSourceException {
        if (items.isEmpty()) {
            loadData();
        }
        return items;
    }

    private void loadData() throws DataSourceException {
        try (BufferedReader in = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = in.readLine()) != null) {
                String[] parts = line.strip().split(FIELDS_SEPARATOR);
                if (parts.length != FIELDS_PER_RECORD) {
                    throw new DataSourceException("ERROR: Failed to load data: wrong line format");
                }
                Item item = convertPartsToItem(parts);
                items.add(item);
            }
        } catch (IOException e) {
            throw new DataSourceException("ERROR: Failed to load data: " + e.getMessage());
        }
    }

    private Item convertPartsToItem(String[] parts) {
        // Skip validation to make this class simple
        return new Item(
                Integer.parseInt(parts[0]),
                parts[1].strip(),
                Integer.parseInt(parts[2]),
                Integer.parseInt(parts[3])
        );
    }
}

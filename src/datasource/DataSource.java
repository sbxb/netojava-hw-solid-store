package datasource;

import app.Item;

import java.util.List;

public interface DataSource {
    List<Item> getItems() throws DataSourceException;
}

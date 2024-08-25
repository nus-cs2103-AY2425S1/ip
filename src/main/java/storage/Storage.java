package storage;

import java.util.List;

public abstract class Storage {

    /**
     * Loads all the data from storage
     * 
     * @return data as List
     */
    public abstract List<String> load();

    /**
     * Stores the element
     * 
     * @return data as List
     */
    public abstract List<String> store(String elem);


    /**
     * Overwrites existing data with new data
     * 
     * @return data as List
     */
    public abstract List<String> update(List<String> data);
}

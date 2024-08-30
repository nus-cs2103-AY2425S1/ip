package seedu.avo.storage;

import java.util.List;

/**
 * Represents a storage that handle read and writes
 * @param <T> type of write data
 * @param <S> type of read data
 */
public abstract class Storage<T, S> {
    public abstract void write(S data);
    public abstract List<T> fetchAll();
}

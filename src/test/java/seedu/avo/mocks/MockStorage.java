package seedu.avo.mocks;

import java.util.ArrayList;
import java.util.List;

import seedu.avo.storage.Storage;

/**
 * Represents a stub storage
 * @param <T> type of write data
 * @param <S> type of read data
 */
public class MockStorage<T, S> extends Storage<T, S> {
    @Override
    public void write(S data) {}
    @Override
    public List<T> fetchAll() {
        return new ArrayList<>();
    }
}

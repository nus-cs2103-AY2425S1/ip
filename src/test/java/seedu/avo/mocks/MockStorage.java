package seedu.avo.mocks;

import java.util.ArrayList;
import java.util.List;

import seedu.avo.storage.Storage;

public class MockStorage<T, S> extends Storage<T, S> {
    @Override
    public void write(S data) {}
    @Override
    public List<T> fetchAll() {
        return new ArrayList<>();
    }
}

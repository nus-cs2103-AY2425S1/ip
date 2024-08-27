package seedu.avo.mocks;

import seedu.avo.storage.Storage;

import java.util.ArrayList;
import java.util.List;

public class MockStorage<T, S> extends Storage<T, S> {
    @Override
    public void write(S data) {}
    @Override
    public List<T> fetchAll() {
        return new ArrayList<>();
    }
}

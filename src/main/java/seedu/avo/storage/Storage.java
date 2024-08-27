package seedu.avo.storage;

import java.util.List;

public abstract class Storage<T, S> {
    public abstract void write(S data);
    public abstract List<T> fetchAll();
}

package seedu.avo.storage;

import seedu.avo.exceptions.AvoException;

public abstract class FileParser<T> {
    abstract public T parse(String input) throws AvoException;
}

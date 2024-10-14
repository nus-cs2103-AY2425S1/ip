package seedu.avo.storage;

import seedu.avo.exceptions.AvoException;

/**
 * Represents a parser that parse file input
 * @param <T> type of read data
 */
public abstract class FileParser<T> {
    public abstract T parse(String input) throws AvoException;
}

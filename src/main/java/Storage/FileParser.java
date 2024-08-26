package Storage;

import Exceptions.AvoException;

public abstract class FileParser<T> {
    abstract public T parse(String input) throws AvoException;
}

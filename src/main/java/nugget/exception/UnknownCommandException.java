package nugget.exception;

import nugget.exception.NuggetException;

public class UnknownCommandException extends NuggetException {
    public UnknownCommandException() {
        super("OOPS!!! Please enter valid commands!");
    }
}
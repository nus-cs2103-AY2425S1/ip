package nugget;

import nugget.NuggetException;

public class UnknownCommandException extends NuggetException {
    public UnknownCommandException() {
        super("OOPS!!! Please enter valid commands!");
    }
}
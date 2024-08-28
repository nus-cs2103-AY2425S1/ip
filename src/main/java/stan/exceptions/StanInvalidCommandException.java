package stan.exceptions;

import stan.exceptions.StanException;

public class StanInvalidCommandException extends StanException {
    public StanInvalidCommandException() {
        super("I'm sorry, but I don't understand that command.");
    }
}


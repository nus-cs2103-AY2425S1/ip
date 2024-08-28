package stan.exceptions;

import stan.exceptions.StanInvalidArgumentException;

public class StanInvalidDateTimeFormatException extends StanInvalidArgumentException {
    public StanInvalidDateTimeFormatException(String message) {
        super(message);
    }
}


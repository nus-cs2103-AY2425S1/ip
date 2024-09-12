package yap.storage;

import java.io.IOException;

/**
 * Represents a bad data format exception, when the program cannot read the data file into storage correctly.
 */
public class BadDataFormatException extends IOException {

    /**
     * Constructs a BadDataFormatException with a message.
     *
     * @param message the message containing the reason and solution for the exception.
     */
    public BadDataFormatException(String message) {
        super(message);
    }
}

package brainrot.exceptions;

import java.io.IOException;

/**
 * The UnknownLaodingError class represents an Unknown Loading Error
 * It extends the Exception class and includes a constructor to throw the exception message
 */
public class UnknownLoadingError extends IOException {
    /**
     * Constructs a new Unknown Loading Error with a specific message.
     *
     * @param message The message of the exception
     */
    public UnknownLoadingError(IOException message) {
        super(message);
    }
}

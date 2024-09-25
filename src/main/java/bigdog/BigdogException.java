package bigdog;

/**
 * The {@code BigdogException} class is a custom unchecked exception that extends {@code RuntimeException}.
 * It is used to signal errors specific to the Bigdog application.
 * When this exception is thrown, an error message is provided and automatically formatted with a newline character.
 */
public class BigdogException extends RuntimeException {

    /**
     * Constructs a BigdogException with a specified error message.
     * The error message is appended with a newline character for formatting.
     *
     * @param str the detail message to be included with the exception.
     */
    public BigdogException(String str) {
        super(str + "\n");
    }

}

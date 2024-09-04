package bigdog;

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

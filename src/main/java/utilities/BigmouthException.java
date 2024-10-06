package utilities;

/**
 * A custom exception class for handling errors specific to the Bigmouth chatbot.
 */
public class BigmouthException extends Exception {

    /**
     * Constructs a new BigmouthException with the specified error message.
     *
     * @param message The detail error message.
     */
    public BigmouthException(String message) {
        super(message);
    }
}

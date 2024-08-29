package exception;

/**
 * Represents an exception that is unique to the Tako chatbot.
 */
public class TakoException extends Exception {

    String message;

    /**
     *
     * @param message the error message to be printed
     */
    public TakoException(String message) {
        this.message = message;
    }

    /**
     * Returns the error message that is input to the constructor
     * of TakoException
     *
     * @return error message
     */
    public String message() {
        return this.message;
    }
}

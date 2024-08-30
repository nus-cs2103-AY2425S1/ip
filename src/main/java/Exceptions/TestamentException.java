package Exceptions;

/**
 * Represents an exception from the Testament chatbot.
 */
public class TestamentException extends Exception {

    /**
     * Creates a TestamentException with no message.
     */
    public TestamentException() {
        super();
    }

    /**
     * Creates a TestamentException with the specified message.
     *
     * @param s Message for TestamentException.
     */
    public TestamentException(String s) {
        super(s);
    }
}

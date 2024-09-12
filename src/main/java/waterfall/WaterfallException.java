package waterfall;

/**
 * Represents a custom exception for waterfall chatbot.
 * The <code>WaterfallException</code> class extends <code>Exception</code>
 * class and provides custom error format for exceptions that occur while
 * running the Waterfall application.
 *
 * @author Wai Hong
 */

public class WaterfallException extends Exception {

    /**
     * Constructs a new WaterfallException with the specified detail message.
     *
     * @param message Detail for the exception.
     */
    public WaterfallException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Oops! " + super.toString() + " :(";
    }
}

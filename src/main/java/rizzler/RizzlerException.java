package rizzler;

/**
 * Represents the unique exception thrown
 * related to Rizzler's functions
 */
class RizzlerException extends Exception {

    /**
     * Constructs a new <code>RizzlerException</code>
     *
     * @param message Message to be passed to exception handler.
     */
    RizzlerException(String message) {
        super(message);
    }
}

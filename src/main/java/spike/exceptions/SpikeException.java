package spike.exceptions;

/**
 * Represents an exception specific to Spike.
 */
public class SpikeException extends Exception {

    /**
     * Constructor for SpikeException.
     *
     * @param message The message to be displayed when the exception is thrown.
     */
    public SpikeException(String message) {
        super(message);
    }
}

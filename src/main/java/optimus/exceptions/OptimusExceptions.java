package optimus.exceptions;

/**
 * Parent for all exceptions specific to Optimus product
 */
public class OptimusExceptions extends Exception {
    /**
     * Sets error message
     *
     * @param message - error message
     */
    public OptimusExceptions(String message) {
        super(message);
    }
}

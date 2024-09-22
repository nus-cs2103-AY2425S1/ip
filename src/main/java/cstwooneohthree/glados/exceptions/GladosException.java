package cstwooneohthree.glados.exceptions;

/**
 * GladosException class for when glados exceptions occur
 *
 * @author jayjay19630
 */
public abstract class GladosException extends Exception {
    /**
     * Constructs GladosException by calling the parent class.
     */
    public GladosException(String message) {
        super(message);
    }
}

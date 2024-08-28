package exceptions;

public abstract class GladosException extends Exception {
    /**
     * Constructs GladosException by calling the parent class.
     */
    public GladosException(String message) {
        super(message);
    }
}
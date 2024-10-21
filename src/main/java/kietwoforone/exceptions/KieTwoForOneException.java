package kietwoforone.exceptions;

/**
 * Represents any exception thrown when the user keys invalid/incomplete inputs.
 */
public class KieTwoForOneException extends Exception {

    /**
     * Constructor for the KieTwoForOneException object.
     *
     * @param message
     */
    public KieTwoForOneException(String message) {
        super(message);
    }

}

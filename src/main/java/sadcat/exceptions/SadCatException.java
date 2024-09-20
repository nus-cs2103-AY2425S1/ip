package sadcat.exceptions;

/**
 * Custom exception class for SadCat application.
 * This class extends the standard Exception class to provide
 * specific error handling for the SadCat application.
 */
public class SadCatException extends Exception {

    /**
     * Constructs a new SadCatException with the specified error message.
     *
     * @param e the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public SadCatException(String e) {
        super(e);
    }

    /**
     * Returns the detail message string of this exception.
     *
     * @return the detail message string of this SadCatException instance
     */
    @Override
    public String getMessage() {
        return super.getMessage();
    }
}

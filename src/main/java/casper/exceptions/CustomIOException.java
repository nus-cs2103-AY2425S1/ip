package casper.exceptions;

/**
 * A custom exception class for handling I/O errors with a specific error message format.
 * This exception provides a custom error message prefix to indicate I/O errors.
 */
//CHECKSTYLE.OFF: AbbreviationAsWordInName
public class CustomIOException extends Exception {
    //CHECKSTYLE.ON: AbbreviationAsWordInName

    public CustomIOException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return String.format("I/O ERROR: %s\n", super.getMessage());
    }
}


package screwllum.exception;

/**
 * An Exception specific to the Screwllum bot when accessing the task list.
 */
public class InvalidIndexException extends ScrewllumException {
    public InvalidIndexException(String index) {
        super("Your index " + index + " is out of bounds");
    }
}

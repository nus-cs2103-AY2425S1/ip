package screwllum.exception;

/**
 * An Exception specific to the Screwllum bot with regards to formatting of commands.
 */
public class InvalidDateFormatException extends ScrewllumException {
    public InvalidDateFormatException(String date) {
        super("Your date input: " + date + " does not match the following pattern yyyy-MM-dd or yyyy-M-d");
    }
}

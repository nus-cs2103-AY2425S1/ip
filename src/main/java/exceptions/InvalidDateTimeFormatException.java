package exceptions;

/**
 * Thrown when an invalid date or time is parsed by Him.
 *
 * @author IsaacPangTH
 */
public class InvalidDateTimeFormatException extends HimException {

    /**
     * Constructor for<code>InvalidDateTimeFormatException</code>.
     */
    public InvalidDateTimeFormatException() {
        super("Invalid Date or Time format!\nDate format: yyyy-MM-dd\nTime format: HH:mm");
    }
}

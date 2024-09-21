package jackson.exceptions;

/**
 * Used to handle invalid arguments.
 * Mostly a placeholder to signify the Event class received
 * invalid dates where the "from" date is after the "to" date.
 */
public class InvalidArgumentException extends JacksonException {
    public InvalidArgumentException() {
        super("");
    }
}

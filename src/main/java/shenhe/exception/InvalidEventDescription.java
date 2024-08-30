package shenhe.exception;

/**
 * Represents an exception thrown when an event description is invalid.
 * <p>
 * The {@code InvalidEventDescription} exception is used to indicate that a command for creating an event
 * is missing required parts, such as the description, the starting time, or the ending time, or that the format
 * of the event command is incorrect. The exception provides a specific error message to guide the user in
 * providing a valid event description.
 * </p>
 */
public class InvalidEventDescription extends Exception {

    /**
     * Constructs an {@code InvalidEventDescription} with a default error message.
     * <p>
     * The default error message is: "Sorry traveller. The event description must have 2 '/',
     * with the first part being the description, the second part being from what time,
     * and the third part being to what time".
     * </p>
     */
    public InvalidEventDescription() {
        super("Sorry traveller. The event description must have 2 '/', with the first part being the description," +
                "the second part being from what time and the third part being to what time");
    }
}

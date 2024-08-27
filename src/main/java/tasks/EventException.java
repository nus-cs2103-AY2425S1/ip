package tasks;

import ouiouibaguette.OuiOuiBaguetteException;

/**
 * Represents an exception that is thrown when there is an error related to an event task.
 */
public class EventException extends OuiOuiBaguetteException {

    /**
     * Constructs an EventException with the specified detail message.
     *
     * @param msg The detail message explaining the cause of the exception.
     */
    public EventException(String msg) {
        super(msg);
    }
}

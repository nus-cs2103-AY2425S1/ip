package tasks;

import ouiouibaguette.OuiOuiBaguetteException;

/**
 * Represents an exception that is thrown when there is an error related to a deadline task.
 */
public class DeadlineException extends OuiOuiBaguetteException {

    /**
     * Constructs a DeadlineException with the specified detail message.
     *
     * @param msg The detail message explaining the cause of the exception.
     */
    public DeadlineException(String msg) {
        super(msg);
    }
}

package tasks;

import ouiouibaguette.OuiOuiBaguetteException;

/**
 * Represents an exception that is thrown when there is an error related to a to-do task.
 */
public class ToDoException extends OuiOuiBaguetteException {

    /**
     * Constructs a ToDoException with the specified detail message.
     *
     * @param msg The detail message explaining the cause of the exception.
     */
    public ToDoException(String msg) {
        super(msg);
    }
}

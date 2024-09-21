package shnoop.exceptions;

/**
 * Encapsulates the situations where an Event or Deadline Task was created with insufficient input.
 */
public class IncompleteEventOrDeadlineException extends Exception {

    public IncompleteEventOrDeadlineException(String message) {
        super(message);
    }

    /**
     * Default exception message constructor.
     */
    public IncompleteEventOrDeadlineException() {
        super("✿ Shnoop ✿: We don't mind sand in our stilettos, but you've came to the beach without sunscreen.\n"
                + "✿ Shnoop ✿: Try adding '/by xxx', '/from xxx' or '/to xxx' after stating the task description.");
    }
}

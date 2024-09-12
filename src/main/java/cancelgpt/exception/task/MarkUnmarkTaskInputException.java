package cancelgpt.exception.task;

/**
 * Represents an abstract Exception for mark unmark task input.
 */
public abstract class MarkUnmarkTaskInputException extends Exception {
    /**
     * Initialises MarkUnmarkTaskInputException with action (mark or unmark),
     * with a message indication mark or unmark task input exception.
     *
     * @param action "mark" or "unmark"
     */
    public MarkUnmarkTaskInputException(String action) {
        super(action + " task input must be in the form of "
                + "`" + action + " [integer], where integer is the task number in"
                + "task list");
    }
}

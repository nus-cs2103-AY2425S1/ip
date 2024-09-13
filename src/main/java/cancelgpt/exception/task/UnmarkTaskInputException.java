package cancelgpt.exception.task;

/**
 * Represents an Exception for unmark task input.
 */
public class UnmarkTaskInputException extends MarkUnmarkTaskInputException {
    /**
     * Initialises UnMarkTaskInputException with message indication unmark task input exception.
     */
    public UnmarkTaskInputException() {
        super("unmark");
    }
}

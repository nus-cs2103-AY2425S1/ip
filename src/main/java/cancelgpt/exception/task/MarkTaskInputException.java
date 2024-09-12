package cancelgpt.exception.task;

/**
 * Represents an Exception for mark task input.
 */
public class MarkTaskInputException extends MarkUnmarkTaskInputException {
    /**
     * Initialises MarkTaskInputException with message indication mark task input exception.
     */
    public MarkTaskInputException() {
        super("mark");
    }
}

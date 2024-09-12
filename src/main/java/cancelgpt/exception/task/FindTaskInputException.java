package cancelgpt.exception.task;

/**
 * Represents the exception for find task input.
 */
public class FindTaskInputException extends RuntimeException {

    /**
     * Initialises FindTaskInputException with message indicating find task input exception.
     */
    public FindTaskInputException() {
        super("Find task input must be in the form `delete [keyword], where keyword is a string");
    }
}

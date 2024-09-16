package mel.exceptions;

/**
 * Exception thrown for providing an
 * invalid task input.
 */
public class TaskException extends Exception {
    /**
     * Constructor for TaskException.
     * @param input The intended format for valid task input
     */
    public TaskException(String input) {
        super(input);
    }

    @Override
    public String toString() {
        return "Mel sees something missing? Mel expected:\n"
                + "  " + super.getMessage() + " :((";
    }
}

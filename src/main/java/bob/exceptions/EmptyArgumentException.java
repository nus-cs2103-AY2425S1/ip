package bob.exceptions;

/**
 * EmptyArgumentException is thrown when a required argument is empty
 */
public class EmptyArgumentException extends Exception {

    /**
     * Constructor for EmptyArgumentException
     *
     * @param arg The argument which was empty
     * @param taskType The task type which required that argument
     */
    public EmptyArgumentException(String arg, String taskType) {
        super(String.format("OOPS!!! The \"%s\" field of a %s cannot be empty.", arg, taskType));
    }
}

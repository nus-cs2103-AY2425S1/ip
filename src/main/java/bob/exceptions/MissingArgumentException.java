package bob.exceptions;

/**
 * MissingArgumentException is thrown when the user does not include a required argument in the command
 */
public class MissingArgumentException extends Exception {

    /**
     * Constructor for MissingArgumentException
     * @param missing The argument which was not included
     * @param taskType The task type which required that argument
     */
    public MissingArgumentException(String missing, String taskType) {
        super(String.format("OOPS!!! A %s requires a \"%s\" field.", taskType, missing));
    }
}

package talkie.exception;

/**
 * Represents an exception thrown when a task cannot be found in the Talkie application.
 * <p>
 * {@code TalkieNoTaskFoundException} extends {@code TalkieException} and is used to indicate that
 * a specified task could not be located in the system. It provides a hint for the user to check the list of tasks.
 * </p>
 */
public class TalkieNoTaskFoundException extends TalkieException {

    /**
     * Returns a string representation of the exception.
     * <p>
     * This method provides a detailed message indicating that the task could not be found, along with a hint
     * to use the 'list' command to check the list of tasks and a suggestion to try again.
     * </p>
     *
     * @return A string representing the exception message.
     */
    @Override
    public String toString() {
        return "-------------------------------------------------------------------\n"
                + super.toString() + " I cannot find this task in my system!\n"
                + "Hint: Use the command 'list' to check the list of tasks.\n"
                + "Please try again! :D\n"
                + "-------------------------------------------------------------------\n";
    }
}

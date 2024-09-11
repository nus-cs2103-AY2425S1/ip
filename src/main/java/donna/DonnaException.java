package donna;

/**
 * Represents exceptions specific to the Donna Application
 * Provides custom error messages for various user errors
 */
public class DonnaException extends Exception {

    /**
     * Constructs a new DonnaException with the error message
     *
     * @param message The message to be included in the exception
     */
    public DonnaException(String message) {
        super(message);
    }

    /**
     * Returns a DonnaException for when a task's description is missing
     *
     * @param taskType The type of task
     * @return DonnaException with error message
     */
    public static DonnaException emptyDescription(String taskType) {
        return new DonnaException("Please provide the description for a " + taskType + " task");
    }

    /**
     * Returns a DonnaException for when a task's deadline is missing
     *
     * @return DonnaException with error message
     */
    public static DonnaException emptyDeadline() {
        return new DonnaException("Please provide a deadline for this task!" + "\n"
                + "Use /by to provide a deadline.");
    }

    /**
     * Returns a DonnaException for when an event task's time is missing
     *
     * @return DonnaException with error message
     */
    public static DonnaException emptyEventTime() {
        return new DonnaException("Please provide the timings for this event!"
                + "\n" + "Use /from and /to to provide timings.");
    }

    /**
     * Returns a DonnaException for when the wrong command is entered by the user
     *
     * @param taskType The command entered by the user
     * @return DonnaException with error message
     */
    public static DonnaException invalidTaskType(String taskType) {
        return new DonnaException("I'm sorry I don't understand that :(\n"
                + "Please use a valid task type \n"
                + "(todo / deadline / event)\n"
                + "or you may type list , delete or mark / unmark\n"
                + "to view, delete or mark tasks");
    }

    /**
     * Returns a DonnaException for when an invalid number is passed by the user
     *
     * @return DonnaException with error message
     */
    public static DonnaException invalidTaskNumber() {
        return new DonnaException("No task assigned to this number yet.\n"
                + "Retry with a valid task number!");
    }
}

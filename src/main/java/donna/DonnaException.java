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
        return new DonnaException("You forgot to give a description for this " + taskType + " task. \n"
                + "Donna doesn't forget details.");
    }

    /**
     * Returns a DonnaException for when a task's deadline is missing
     *
     * @return DonnaException with error message
     */
    public static DonnaException emptyDeadline() {
        return new DonnaException("You need to provide a deadline.\n"
                + "Let's do this right by using /by to provide a deadline.");
    }

    /**
     * Returns a DonnaException for when an event task's time is missing
     *
     * @return DonnaException with error message
     */
    public static DonnaException emptyEventTime() {
        return new DonnaException("You're missing the timings for this event.\n"
                + "I need all the details.\n"
                + "Use /from and /to to provide timings.");
    }

    /**
     * Returns a DonnaException for when the wrong command is entered by the user
     *
     * @param taskType The command entered by the user
     * @return DonnaException with error message
     */
    public static DonnaException invalidTaskType(String taskType) {
        return new DonnaException("I don't know what you mean by that :(\n"
                + "Let's stick to the words todo / deadline / event\n"
                + "to add a task. Or you may type, list, tag, \n"
                + "delete or mark / unmark followed by the task's\n"
                + "task's number to view, tag, delete or mark tasks.");
    }

    /**
     * Returns a DonnaException for when an invalid number is passed by the user
     *
     * @return DonnaException with error message
     */
    public static DonnaException invalidTaskNumber() {
        return new DonnaException("There’s no task for that number. \nTry again, and I’ll fix it for you.");
    }

    /**
     * Returns a DonnaException for when a task is tagged incorrectly
     *
     * @return DonnaException with error message
     */
    public static DonnaException invalidTag() {
        return new DonnaException("That's not how you tag a task!\n"
                + "Let's keep it easy- task number followed by a hash (#) please.");
    }
}

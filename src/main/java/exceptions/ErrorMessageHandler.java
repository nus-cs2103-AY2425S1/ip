package exceptions;

/**
 * The ErrorMessageHandler class provides a centralized way to retrieve common error messages.
 * These messages are used to provide feedback to the user when invalid input or operations occur.
 */
public class ErrorMessageHandler {

    /**
     * Returns the error message for an invalid task.
     *
     * @return A string indicating that the task provided is invalid.
     */
    public static String getInvalidTaskMessage() {
        return "THAT IS AN INVALID TASK LAH";
    }

    /**
     * Returns the error message when no valid index is provided.
     *
     * @return A string indicating that a valid index has not been given.
     */
    public static String getNoValidIndexGivenMessage() {
        return "A valid index has not been given!!";
    }

    /**
     * Returns the error message for an invalid date format.
     *
     * @return A string prompting the user to use the correct date format (dd/MM/yyyy).
     */
    public static String getInvalidDateMessage() {
        return "PLEASE USE THE PROPER DATE FORMAT: dd/MM/yyyy";
    }

    /**
     * Returns the error message when no task description is provided.
     *
     * @return A string indicating that a task description is required.
     */
    public static String getNoTaskDescriptionMessage() {
        return "Wah, no description then I record what?";
    }

    /**
     * Returns the error message for an invalid tag name.
     *
     * @return A string indicating that the specified tag does not exist.
     */
    public static String getInvalidTagNameMessage() {
        return "You are finding a tag that doesn't exist!!";
    }
}

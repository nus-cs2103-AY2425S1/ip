package asta;

/**
 * AstaException is a custom exception class used to handle errors specific to the Asta application. This class provides
 * static methods to throw specific error messages, ensuring consistent error handling throughout the application.
 */
public class AstaException extends Exception {
    /**
     * Constructs a new AstaException with the specified error message.
     *
     * @param message The detailed error message.
     */
    public AstaException(String message) {
        super(message);
    }

    /**
     * Throws an AstaException when an invalid command is encountered.
     *
     * @throws AstaException If the command is not recognized by Asta.
     */
    public static void handleInvalidCommand() throws AstaException {
        throw new AstaException("Unfortunately, Asta don't know what that means...");
    }

    /**
     * Throws an AstaException when a todo task is added without a description.
     *
     * @throws AstaException If the todo task description is empty.
     */
    public static void handleEmptyTodoDescription() throws AstaException {
        throw new AstaException("Unfortunately, Asta can't add a todo without a description...");
    }

    /**
     * Throws an AstaException when an invalid task number is provided for marking a task as done.
     *
     * @throws AstaException If the task number is invalid or out of bounds.
     */
    public static void handleInvalidMarkTaskNumber() throws AstaException {
        throw new AstaException("Unfortunately, the task number provided doesn't seem valid...");
    }

    /**
     * Throws an AstaException when an invalid task number is provided for unmarking a task as not done.
     *
     * @throws AstaException If the task number is invalid or out of bounds.
     */
    public static void handleInvalidUnmarkTaskNumber() throws AstaException {
        throw new AstaException("Unfortunately, the task number provided doesn't seem valid...");
    }

    /**
     * Throws an AstaException when a deadline task is added without either or both a description and a 'by' time.
     *
     * @throws AstaException If the deadline input is incomplete or incorrect.
     */
    public static void handleInvalidDeadlineInput() throws AstaException {
        throw new AstaException("Unfortunately, Asta needs both a description and a 'by' time for the deadline...");
    }

    /**
     * Throws an AstaException when an event task is added without either or both a description and 'from' and 'to'
     * times.
     *
     * @throws AstaException If the event input is incomplete or incorrect.
     */
    public static void handleInvalidEventInput() throws AstaException {
        throw new AstaException(
                "Unfortunately, Asta needs both a description and 'from' and 'to' times for the event...");
    }

    /**
     * Throws an AstaException when an invalid task number format is provided.
     *
     * @param command The command being executed (e.g., "mark", "delete").
     * @throws AstaException If the task number format is invalid.
     */
    public static void handleInvalidTaskNumberFormat(String command) throws AstaException {
        throw new AstaException("Unfortunately, Asta needs a valid task number to " + command + "...");
    }

    /**
     * Throws an AstaException when an invalid task number is provided for deletion.
     *
     * @throws AstaException If the task number is invalid or out of bounds for deletion.
     */
    public static void handleInvalidDeleteTaskNumber() throws AstaException {
        throw new AstaException("Unfortunately, the task number provided doesn't seem valid for deletion...");
    }
}

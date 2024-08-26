package bopes.exception;

/**
 * The BopesException class represents exceptions specific to the Bopes task management application.
 * This exception is thrown to indicate various errors related to task input and command parsing.
 */
public class BopesException extends Exception {

    /**
     * Constructs a new BopesException with the specified detail message.
     *
     * @param message the detail message
     */
    public BopesException(String message) {
        super(message);
    }

    /**
     * Returns a BopesException indicating that the task index provided by the user is out of range.
     *
     * @param size the number of tasks currently in the list
     * @return a BopesException with a message indicating the valid range of task indices
     */
    public static BopesException invalidIndex(int size) {
        return new BopesException("Error: The task index is out of range. Please provide a valid task number between 1 and " + size + ".");
    }

    /**
     * Returns a BopesException indicating that the user input is not a valid number.
     *
     * @return a BopesException with a message indicating that the input is not a valid number
     */
    public static BopesException invalidNumberFormat() {
        return new BopesException("Error: The provided input is not a valid number. Please enter a valid task number.");
    }

    /**
     * Returns a BopesException indicating that the deadline format provided by the user is incorrect.
     *
     * @return a BopesException with a message indicating the correct deadline format
     */
    public static BopesException invalidDeadlineFormat() {
        return new BopesException("Error: Incorrect deadline format. Please use the format 'deadline <task> /by <dd/MM/yyyy hh:mm a>'.");
    }

    /**
     * Returns a BopesException indicating that the event format provided by the user is incorrect.
     *
     * @return a BopesException with a message indicating the correct event format
     */
    public static BopesException invalidEventFormat() {
        return new BopesException("Error: Incorrect event format. Please use the format 'event <task> /from <dd/MM/yyyy hh:mm a> /to <dd/MM/yyyy hh:mm a>'.");
    }

    /**
     * Returns a BopesException indicating that the user has entered an unknown command.
     *
     * @return a BopesException with a message indicating the valid commands and their usage
     */
    public static BopesException unknownCommand() {
        return new BopesException("Error: Unknown command. Please use 'todo', 'deadline', or 'event' followed by the task details. Example: 'todo read book'. Use 'bye' to exit the program.");
    }
}

package bopes.exception;
public class BopesException extends Exception {
    public BopesException(String message) {
        super(message);
    }

    public static BopesException invalidIndex(int size) {
        return new BopesException("Error: The task index is out of range. Please provide a valid task number between 1 and " + size + ".");
    }

    public static BopesException invalidNumberFormat() {
        return new BopesException("Error: The provided input is not a valid number. Please enter a valid task number.");
    }

    public static BopesException invalidDeadlineFormat() {
        return new BopesException("Error: Incorrect deadline format. Please use the format 'deadline <task> /by <dd/MM/yyyy hh:mm a>'.");
    }

    public static BopesException invalidEventFormat() {
        return new BopesException("Error: Incorrect event format. Please use the format 'event <task> /from <dd/MM/yyyy hh:mm a> /to <dd/MM/yyyy hh:mm a>'.");
    }

    public static BopesException unknownCommand() {
        return new BopesException("Error: Unknown command. Please use 'todo', 'deadline', or 'event' followed by the task details. Example: 'todo read book'. Use 'bye' to exit the program.");
    }
}

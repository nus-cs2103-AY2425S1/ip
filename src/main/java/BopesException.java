public class BopesException extends Exception {
    public BopesException(String message) {
        super(message);
    }

    public static BopesException invalidIndex(int index) {
        return new BopesException("Error: The task index " + (index + 1) + " is out of range. Please provide a valid task number between 1 and " + (index) + ".");
    }

    public static BopesException invalidNumberFormat() {
        return new BopesException("Error: The provided input is not a valid number. Please enter a valid task number.");
    }

    public static BopesException invalidDeadlineFormat() {
        return new BopesException("Error: Incorrect deadline format. Please use the format 'deadline <task> /by <time>'. Example: 'deadline submit report /by tomorrow 5pm'.");
    }

    public static BopesException invalidEventFormat() {
        return new BopesException("Error: Incorrect event format. Please use the format 'event <task> /from <start> /to <end>'. Example: 'event project meeting /from Monday 2pm /to Monday 4pm'.");
    }

    public static BopesException unknownCommand() {
        return new BopesException("Error: Unknown command. Please use 'todo', 'deadline', or 'event' followed by the task details.");
    }
}

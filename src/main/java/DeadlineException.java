public class DeadlineException extends InvalidInputException {
    public DeadlineException() {
        super("Your input format is invalid. Please use: 'deadline <description> /by <actual deadline>'");
    }
}

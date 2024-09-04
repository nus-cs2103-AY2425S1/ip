package elara.task;

public class DeadlineException extends InvalidInputException {
    public DeadlineException() {
        super("Your input format is invalid. Please use: 'deadline <description> /by <actual deadline>' \n"
                + "<actual deadline> format: yyyy-MM-dd HHmm");
    }
}

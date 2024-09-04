package elara.task;

public class EventException extends InvalidInputException {
    public EventException() {
        super("Your input format is invalid. Please use: 'event <description> /from <start> /to <end> \n"
                + "<start> and <end> format: yyyy-MM-dd HHmm");
    }
}

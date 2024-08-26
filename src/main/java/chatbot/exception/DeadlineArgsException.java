package chatbot.exception;

public class DeadlineArgsException extends InputException {
    public DeadlineArgsException() {
        super("There are an incorrect number of deadline arguments!");
    }
}

package chatbot.exception;

/**
 * Exception to be thrown when there are an incorrect number of event args
 */
public class EventArgsException extends InputException {
    public EventArgsException() {
        super("There are an incorrect number of event arguments!");
    }
}

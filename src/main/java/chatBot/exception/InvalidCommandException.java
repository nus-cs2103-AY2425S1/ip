package chatbot.exception;

/** Exception to be thrown when invalid commands are encountered */
public class InvalidCommandException extends Exception {
    /** Constructor */
    public InvalidCommandException() {
        super("commands accepted: todo , deadline , event , list, mark , unmark ,"
                + " bye , delete, find");
    }
}

package arona.AronaExceptions;

public class ListIndexException extends AronaException {
    /**
     * Constructor for the exceptions class of Arona, encapsulates all possible user errors that are non-fatal and can
     * be handled by simply ignoring the user input and sending an error message
     */
    public ListIndexException() {
        super("Error! This task does not exist!");
    }
}

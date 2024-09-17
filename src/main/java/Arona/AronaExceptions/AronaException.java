package arona.AronaExceptions;

public class AronaException extends Exception {
    /**
     * Constructor for the exceptions class of Arona, encapsulates all possible user errors that are non-fatal and can
     * be handled by simply ignoring the user input and sending an error message
     * @param  message  a message indicating what was wrong with the input
     */
    public AronaException(String message) {
        super(message);
    }
}

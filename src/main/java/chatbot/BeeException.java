package chatbot;

/**
 * A class that formats error message shown
 * by chatbot when exceptions are thrown.
 *
 * @author celeschai
 */
public class BeeException extends Exception {

    /**
     * Prepends error message provided with
     * chatbot personality specific wording.
     *
     * @param msg Error message to be shown.
     */
    public BeeException(String msg) {
        super("Hey!!! "
                + msg
                + "\nType 'help' to see what you can do.");
    }
}

package sirpotato;

/**
 * This is the SirPotatoException
 * for when the user types incorrect inputs
 * such as forgetting the description of the task
 * or forgetting to put the /by
 */
public class SirPotatoException extends Exception {

    public SirPotatoException(String message) {
        super(message);
    }

}

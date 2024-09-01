/**
 * Custom exception when an exception running the program occurs
 *
 * @author Qiao Yi
 */
public class SigmaException extends Exception {
    /**
     * Constructor for a SigmaException
     * @param message The error message
     */
    public SigmaException(String message) {
        super(message);
    }
}

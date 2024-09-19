package ouiouibaguette;

/**
 * Represents a custom exception specific to the OuiOuiBaguette application.
 * This exception is thrown to indicate errors specific to the application's logic.
 */
public class OuiOuiBaguetteException extends Exception {

    /**
     * Constructs an OuiOuiBaguetteException with the specified detail message.
     * The error message description is prefixed with "Non non. ".
     *
     * @param msg The detail message explaining the cause of the exception.
     */
    public OuiOuiBaguetteException(String msg) {
        super("Non non. " + msg);
    }
}

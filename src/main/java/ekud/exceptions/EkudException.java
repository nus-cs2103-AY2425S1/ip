package ekud.exceptions;

/**
 * Represents exceptions that can be thrown by the EKuD chat robot program.
 * <p/>
 * EkudException is a checked {@link Exception}, meaning that it has to be handled by
 * methods throw it.
 *
 * @author uniqly
 * @see Exception
 */
public class EkudException extends Exception {
    public EkudException(String message) {
        super(message);
    }
}

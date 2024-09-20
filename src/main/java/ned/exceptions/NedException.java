package ned.exceptions;

/**
 * Represents a generic exception, used to wrap other types of exceptions from method calls involved in running Ned
 */
public abstract class NedException extends Exception {
    /**
     * A generic exception, used to wrap other types of exceptions from method calls involved in running Ned.
     *
     * @param errorMsg The error message that is shown to users
     */
    public NedException(String errorMsg) {
        super(errorMsg);
    }
}

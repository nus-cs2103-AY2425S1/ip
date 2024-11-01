package exception;

/**
 * Represents a generic IO exception in the Blitz application.
 */
public class BlitzIoException extends BlitzException {
    private String desc;

    /**
     * Constructs a new BlitzIOException object with specified description.
     *
     * @param desc String description of the error.
     */
    public BlitzIoException(String desc) {
        this.desc = desc;
    }

    /**
     * Returns a String representation of this object.
     *
     * @return String indicates file I/O error.
     */
    @Override
    public String toString() {
        return desc + ", please try again or contact administrator!";
    }
}

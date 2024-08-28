package exception;

public class BlitzIOException extends BlitzException {
    private String desc;

    /**
     * Constructs a new BlitzIOException object with specified description.
     *
     * @param desc String description of the error.
     */
    public BlitzIOException(String desc) {
        this.desc = desc;
    }

    /**
     * Returns a String representation of this object.
     *
     * @return String indicates file I/O error.
     */
    @Override
    public String toString() {
        return this.desc + ", please try again or contact administrator!";
    }
}

package sigma.exception;

/**
 * Thrown when the file format is incorrect or when an unknown task type is encountered.
 */
public class SigmaFileFormatException extends SigmaException {
    /**
     * Returns an error message indicating that an unknown task type was encountered.
     *
     * @return a string representing the error message
     */
    @Override
    public String toString() {
        return String.format("%s Unknown Task type.", super.toString());
    }
}

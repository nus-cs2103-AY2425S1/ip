package sigma.exception;


/**
 * Thrown when there is an issue with reading from or writing to a file in the Sigma application.
 */
public class SigmaFileException extends SigmaException {
    /**
     * Returns a detailed error message indicating the inability to read from or write to a file.
     *
     * @return a string representing the detailed error message
     */
    @Override
    public String toString() {
        return String.format("%s Cannot read from or write to the file.", super.toString());
    }
}

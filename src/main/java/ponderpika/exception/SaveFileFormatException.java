package ponderpika.exception;

/**
 * Exception thrown when there are issues saving data due to file format problems.
 * <p>
 * This class extends PonderPikaException to handle cases where writing to a file fails because of format issues.
 * </p>
 */
public class SaveFileFormatException extends PonderPikaException {

    /**
     * Returns a string representation of the exception, which includes
     * the default message from the superclass along with a specific message
     * stating that writing to the file could not be completed due to format issues.
     *
     * @return A string that represents the detailed exception
     */
    @Override
    public String toString() {
        return (super.toString() + "Could not write into file, encountered file format issues!");
    }
}

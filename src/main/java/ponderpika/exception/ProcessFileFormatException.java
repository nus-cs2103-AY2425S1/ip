package ponderpika.exception;

/**
 * Exception thrown when there are issues processing the format of a file in a Pika context.
 * <p>
 * This class extends PonderPikaException to handle cases where the data format in a file is invalid or problematic.
 * </p>
 */
public class ProcessFileFormatException extends PonderPikaException {

    /**
     * Returns a string representation of the exception, which includes
     * the default message from the superclass along with a specific message
     * stating that there was an error processing the task format in the file.
     *
     * @return A string that represents the exception, including the
     *     superclass message and details about the format issue in the file.
     */
    @Override
    public String toString() {
        return (super.toString() + "Error processing data, encountered file format issues!");
    }
}

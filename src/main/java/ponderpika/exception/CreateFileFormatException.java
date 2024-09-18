package ponderpika.exception;

/**
 * Exception thrown when there are issues creating a file due to I/O problems.
 * <p>
 * This class extends PonderPikaException to handle cases where file creation fails because of input/output errors.
 * </p>
 */
public class CreateFileFormatException extends PonderPikaException {

    /**
     * Returns a string representation of the exception, which includes
     * the default message from the superclass along with a specific message
     * stating that there was an error creating the file due to I/O issues.
     *
     * @return A string that represents the detailed exception
     */
    @Override
    public String toString() {
        return (super.toString() + "Error creating file, encountered I/O issues!");
    }
}

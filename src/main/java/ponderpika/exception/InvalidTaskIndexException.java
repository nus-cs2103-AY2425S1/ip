package ponderpika.exception;

/**
 * Exception thrown when an invalid index is accessed for particular tasks on which the action has to be performed
 * <p>
 * This class extends PonderPikaException to specifically handle cases
 * where a task is requested at an index that does not exist.
 * </p>
 */
public class InvalidTaskIndexException extends PonderPikaException {

    /**
     * Returns a string representation of the exception, which includes
     * the default message from the superclass along with a specific message
     * stating that no task is available at the given index as it is an invalid index.
     *
     * @return A string that represents the detailed exception
     */
    @Override
    public String toString() {
        return (super.toString() + "No task available at given index!");
    }
}

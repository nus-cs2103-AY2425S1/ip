package ponderpika.exception;

/**
 * Exception thrown when a duplicate task is detected in the task list.
 * <p>
 * This exception extends the PonderPikaException class and is used to indicate that
 * the task already exists in the list
 * </p>
 *
 */
public class DuplicateTaskException extends PonderPikaException {

    /**
     * Returns a string representation of the exception which includes
     * the default message from the superclass along with a specific message stating that
     * the task already exists in the list
     *
     * @return A string that represents the detailed exception
     */
    @Override
    public String toString() {
        return (super.toString() + "You already have this task in list!");
    }
}

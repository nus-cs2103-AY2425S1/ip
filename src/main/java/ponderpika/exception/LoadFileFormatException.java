package ponderpika.exception;

/**
 * Exception thrown when there is an error loading data in a Pika context.
 * <p>
 * This class extends PonderPikaException to handle cases where data cannot be retrieved,
 * resulting in an empty task list.
 * </p>
 */
public class LoadFileFormatException extends PonderPikaException {
    /**
     * Returns a string representation of the exception, which includes
     * the default message from the superclass along with a specific message
     * stating that there was an error loading data and the task list
     * will start empty.
     *
     * @return A string that represents the detailed exception
     */
    @Override
    public String toString() {
        return (super.toString() + "Error Loading Data!, Starting with an empty TaskList!");
    }
}

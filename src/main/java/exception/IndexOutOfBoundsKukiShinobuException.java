package exception;

/**
 * Signals that an index provided is out of bounds for the task list.
 * <p>
 * This exception is thrown when an index that is outside the valid range (1 to the size of the task list) is provided.
 * The index is 1-based, meaning that the first task in the list has an index of 1.
 * </p>
 */
public class IndexOutOfBoundsKukiShinobuException extends KukiShinobuException {

    /**
     * Constructs a new {@code IndexOutOfBoundsKukiShinobuException} with a detailed message.
     * <p>
     * The error message indicates that the provided index is out of range and specifies the valid range of indices.
     * </p>
     *
     * @param index The index that was out of bounds (1-based index).
     * @param maxSize The maximum size of the task list, representing the highest valid index.
     */
    public IndexOutOfBoundsKukiShinobuException(int index, int maxSize) {
        super(String.format(
                "My dear traveller... index '%d' is out of range! Please enter an index between 1 and %d.",
                index,
                maxSize
        ));
    }
}

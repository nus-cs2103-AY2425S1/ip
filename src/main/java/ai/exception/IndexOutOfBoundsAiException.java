package ai.exception;

/**
 * Creates exception when command has an index that is out of bounds.
 */
public class IndexOutOfBoundsAiException extends AiException {
    /**
     * Creates an exception and informs user the valid input range.
     * @param size size of the valid boundary.
     */
    public IndexOutOfBoundsAiException(int size) {
        super("Hey hey!! The task doesn't exist...\n\n"
                + (size == 0 ? "Oh hmph... your task list is empty, you might wanna create one now"
                : "You might wanna try a valid number from 1 to " + size));
    }
}

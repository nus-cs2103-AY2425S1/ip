package panorama.exception;

import panorama.Message;

/**
 * This exception is used to signal that a user-provided input for
 * an ID is out of bounds of the current task list.
 */
public class IdOutOfBoundsException extends PanoramaException {
    /**
     * Constructs a new {@code IdOutOfBoundsException} with a default error message.
     *
     * @param taskListSize The size of the task list currently.
     */
    public IdOutOfBoundsException(int taskListSize) {
        super(buildMessage(taskListSize));
    }

    /**
     * Helper method to construct the error message.
     *
     * @param taskListSize The size of the task list currently.
     * @return The error message string.
     */
    private static String buildMessage(int taskListSize) {
        Integer largestValidId = taskListSize;
        return Message.MyException.ID_OUT_OF_BOUNDS
            + " Please input an ID between 1 and " + largestValidId.toString() + ".";
    }
}


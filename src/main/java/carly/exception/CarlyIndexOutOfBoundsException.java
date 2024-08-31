package carly.exception;

/**
 * Exception thrown when an index is out of bounds for the task list.
 */
public class CarlyIndexOutOfBoundsException extends CarlyException {

    /**
     * Constructs a new CarlyIndexOutOfBoundsException with a detailed error message.
     * The message specifies the invalid index and informs the user about the valid range of indices.
     *
     * @param taskNum The invalid index that caused the exception.
     * @param taskListSize The size of the task list, indicating the valid range of indices.
     */
    public CarlyIndexOutOfBoundsException(Integer taskNum, Integer taskListSize) {
        super("Oh no! we don't have the item number " + taskNum + " in your list! \n" +
                "Please key in your command with list number up to " + taskListSize + " only.");
    }

}

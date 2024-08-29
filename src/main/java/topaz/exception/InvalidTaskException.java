package topaz.exception;

import topaz.main.Topaz;

/**
 * Represents an exception that is thrown when an invalid task input is encountered.
 * This exception provides a detailed message based on the type of task error.
 */
public class InvalidTaskException extends Exception {
    private Topaz.TaskType type;
    /**
     * Constructs an InvalidTaskException with the specified task type.
     *
     * @param type The type of task that caused the exception.
     */
    public InvalidTaskException(Topaz.TaskType type) {
        super();
        this.type = type;
    }

    /**
     * Returns a detailed error message for this exception based on the task type.
     * The message provides guidance on correcting the task input.
     *
     * @return A string containing the error message.
     */
    @Override
    public String toString() {
        switch (type) {
        case T:
            return """
                         Please enter the description of the todo task you want to create.""";
        case D:
            return """
                         Unmatching input pattern for deadline. Better cut our losses...
                         Please follow the format and add the deadline after "/by" in yyyy-mm-dd HH:mm format,
                         eg.deadline return book /by 2024-08-28 17:03""";
        case E:
            return """
                         Unmatching input pattern for event. Better cut our losses...
                         Please follow the format and add the time after "/from" and "/to",
                         with time input in format yyyy-mm-dd HH:mm,
                         eg.event project meeting /from 2024-08-28 09:03 /to 2024-08-28 12:30""";
        default:
            return "";
        }
    }
}

package topaz.exception;


import java.time.LocalDateTime;

import topaz.main.Topaz;

/**
 * Represents an exception that is thrown when there is an invalid time range for a task.
 * This exception is used to indicate that the start time is later than the end time.
 */
public class InvalidTimeException extends InvalidTaskException {

    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs an InvalidTimeException with the specified task type, start time, and end time.
     *
     * @param type The type of task that caused the exception.
     * @param from The start time of the task.
     * @param to The end time of the task.
     */
    public InvalidTimeException(Topaz.TaskType type, LocalDateTime from, LocalDateTime to) {
        super(type);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "Start time: " + from + " shouldn't be later than end time: " + to;
    }
}

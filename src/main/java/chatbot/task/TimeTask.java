package chatbot.task;

import java.time.LocalDateTime;

/**
 * Represents the concept of a Task that has a datetime component
 */
public abstract class TimeTask extends Task {
    /**
     * Constructor for the TimeTask abstract class
     *
     * @param name Name of the task
     * @param isDone Boolean value representing whether the task has been marked as done
     */
    public TimeTask(String name, boolean isDone) {
        super(name, isDone);
    }

    /**
     * Returns the representation of the time associated with the TimeTask
     *
     * @return LocalDateTime object representing the datetime of the TimeTask
     */
    public abstract LocalDateTime getTime();
}

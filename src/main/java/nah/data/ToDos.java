package nah.data;

import java.time.LocalDateTime;

/**
 * The ToDos class represents a task without a specific deadline.
 *
 * <p>If the task type is "todo". The end time for a ToDo is considered to be infinite.</p>
 */
public class ToDos extends Task {
    /**
     * Constructor.
     * @param content the description of the task
     */
    public ToDos(String content) {

        super(content);
    }
    /**
     * Return a brief description of this task.
     *
     * @return a String
     */
    @Override
    public String brief() {
        return "T | " + super.getStatus() + " | " + super.getTask();
    }

    /**
     * Checks if the String to lowercase is "todo".
     *
     * @param s the String that need to be checked
     * @return a boolean value
     */
    @Override
    public boolean isReferingToTask(String s) {
        assert s != null : "Task reference cannot be null";
        return s.trim().toLowerCase().equals("todo");
    }

    /**
     * Returns false because this task has no due time.
     *
     * @return a LocalDateTime value.
     */
    @Override
    public boolean isBefore(LocalDateTime due) {
        return false;
    }

    /**
     * Returns String representation.
     *
     * @return a String
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}

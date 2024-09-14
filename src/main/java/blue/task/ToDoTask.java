package blue.task;

import java.util.Objects;

/**
 * Represents a to-do task, which only has a description.
 * Inherits from the {@link blue.task.Task} class.
 */
public class ToDoTask extends Task {

    /**
     * Constructs a ToDoTask with the specified description.
     *
     * @param description The description of the task.
     */
    public ToDoTask(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the task, including the type indicator "[T]".
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    /**
     * Returns a string representation of the task formatted for saving to a file.
     *
     * @return The string formatted for saving to a file.
     */
    @Override
    public String toFileString() {
        return "T | " + getStatusIcon() + " | " + getDescription();
    }

    /**
     * Checks if this ToDoTask is equal to another object.
     *
     * @param o The object to compare to.
     * @return true if the given object is equal to this task, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ToDoTask that = (ToDoTask) o;
        return Objects.equals(description, that.description) && this.isDone == that.isDone;
    }
}

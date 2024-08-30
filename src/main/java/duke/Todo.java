package duke;

/**
 * Represents a to-do task with a description.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo with the specified description. The task is initially not done.
     *
     * @param description the description of the to-do task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the type icon representing a to-do task.
     *
     * @return the type icon "[T]"
     */
    public String getTypeIcon() {
        return "[T]";
    }

    /**
     * Returns a string representation of the to-do task, including its type icon and status.
     *
     * @return a string representation of the to-do task
     */
    @Override
    public String toString() {
        return this.getTypeIcon() + super.toString();
    }
}

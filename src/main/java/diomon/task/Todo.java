package diomon.task;

/**
 * The {@code Todo} class represents a task that has no specific date or deadline.
 * It is a subclass of {@link Task}, and includes specific functionality related to "Todo" tasks.
 */
public class Todo extends Task{
    public static final String TYPEICON = "T";

    /**
     * Constructs a new {@code Todo} task with the given description.
     * The task is initially marked as incomplete.
     *
     * @param task The description of the task.
     */
    public Todo(String task){
        super(task);
    }

    /**
     * Constructs a new {@code Todo} task with the given completion status and description.
     *
     * @param complete    Whether the task is completed or not.
     * @param description The description of the task.
     */
    public Todo(boolean complete, String description) {
        super(complete, description);

    }

    /**
     * Returns the type icon representing a "Todo" task.
     *
     * @return The type icon for "Todo", which is "T".
     */
    @Override
    public String getTypeIcon() {
        return TYPEICON;
    }

    /**
     * Returns a string representation of the {@code Todo} task formatted for storage.
     * The format includes the type icon, the status icon (completed or not), and the description.
     *
     * @return A string formatted for storage.
     */
    @Override
    public String toStorageString(){
        return String.format("%s|%s|%s", TYPEICON, getStatusIcon(),this.description);
    }

    /**
     * Compares this {@code Todo} task with another object for equality.
     * Two {@code Todo} tasks are considered equal if they have the same completion status and description.
     *
     * @param other The object to compare with.
     * @return {@code true} if the tasks are equal, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Todo temp) {
            return temp.completed == completed && temp.description.equals(description);
        }
        return false;
    }
}

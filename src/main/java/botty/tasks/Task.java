package botty.tasks;

import botty.exceptions.BottyException;
import botty.exceptions.CorruptedTaskStringException;

/**
 * Defines behaviour for tasks
 */
public abstract class Task {
    // Whether the task is completed
    private boolean completed;
    // The task's description
    private final String description;

    /**
     * Constructs a {@code Task} with the given completed and description
     * @param completed whether the {@code Task} is completed
     * @param description the description
     */
    public Task(boolean completed, String description) {
        this.completed = completed;
        this.description = description;
    }

    /**
     * Constructs a {@code Task} with the given description, set as not completed
     * @param description the description
     */
    public Task(String description) {
        this(false, description);
    }

    /**
     * Sets whether the {@code Task} is completed
     * @param completed whether the {@code Task} is completed
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    /**
     * Returns the string representation of completed and description for local storage
     */
    protected String getCompletedAndDescription() {
        return (completed ? "1" : "0") + " | " + description;
    }

    /**
     * Returns a string representation of the {@code Task}
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", completed ? "X" : " ", description);
    }

    /**
     * Constructs a {@code Task} from a data string for loading from file
     * @param taskString the data string from file
     * @return the constructed task
     * @throws BottyException if corrupted task string or invalid arguments
     */
    public static Task fromDataString(String taskString) throws BottyException {
        switch (taskString.charAt(0)) {
        case 'E':
            return Event.fromDataString(taskString);
        case 'D':
            return Deadline.fromDataString(taskString);
        case 'T':
            return Todo.fromDataString(taskString);
        default:
            throw new CorruptedTaskStringException();
        }
    }

    /**
     * Returns a string representation of the {@code Task} that is used for local storage
     */
    public abstract String toDataString();
}

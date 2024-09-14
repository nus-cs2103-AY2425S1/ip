package botty.tasks;

import botty.exceptions.BottyException;
import botty.exceptions.CorruptedTaskStringException;
import botty.exceptions.EmptyArgumentException;

/**
 * Defines behaviour for tasks
 */
public abstract class Task {
    // Whether the task is completed
    private boolean isCompleted;
    // The task's description
    private final String description;

    /**
     * Constructs a {@code Task} with the given completion status and description
     * @param isCompleted whether the {@code Task} is completed
     * @param description the description
     */
    public Task(boolean isCompleted, String description) throws EmptyArgumentException {
        if (description.isEmpty()) {
            throw new EmptyArgumentException("description");
        }
        this.isCompleted = isCompleted;
        this.description = description;
    }

    /**
     * Constructs a {@code Task} with the given description, set as not completed
     * @param description the description
     */
    public Task(String description) throws EmptyArgumentException {
        this(false, description);
    }

    /**
     * Sets whether the {@code Task} is completed
     * @param isCompleted whether the {@code Task} is completed
     */
    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    /**
     * Returns the string representation of completed and description for local storage
     */
    protected String getCompletedAndDescription() {
        return (isCompleted ? "1" : "0") + " | " + description;
    }

    /**
     * Returns a string representation of the {@code Task}
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", isCompleted ? "X" : " ", description);
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

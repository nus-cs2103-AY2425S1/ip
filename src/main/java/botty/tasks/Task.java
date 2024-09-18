package botty.tasks;

import botty.exceptions.BottyException;
import botty.exceptions.CorruptedTaskStringException;
import botty.exceptions.EmptyArgumentException;

/**
 * Defines behaviour for tasks
 */
public abstract class Task<T extends TaskData> {
    // Whether the task is completed
    private boolean isCompleted;
    // The task's description
    private String description;

    /**
     * The types of tasks
     */
    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    /**
     * Constructs a {@code Task} with the given completion status and description
     * @param isCompleted whether the {@code Task} is completed
     * @param data the data involved in generating the {@code Task}
     */
    public Task(boolean isCompleted, T data) throws EmptyArgumentException {
        if (!data.hasDescription()) {
            throw new EmptyArgumentException("description");
        }

        this.isCompleted = isCompleted;
        this.description = data.getDescription();
    }

    /**
     * Constructs a {@code Task} with the given description, set as not completed
     * @param data the data involved in generating the {@code Task}
     */
    public Task(T data) throws EmptyArgumentException {
        this(false, data);
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
    public static Task<? extends TaskData> getTaskFromDataString(String taskString) throws BottyException {
        switch (taskString.charAt(0)) {
        case 'E':
            return Event.getEventFromDataString(taskString);
        case 'D':
            return Deadline.getDeadlineFromDataString(taskString);
        case 'T':
            return Todo.getTodoFromDataString(taskString);
        default:
            throw new CorruptedTaskStringException();
        }
    }

    /**
     * Returns a string representation of the {@code Task} that is used for local storage
     */
    public abstract String getDataString();

    /**
     * Returns the task type of the task
     */
    public abstract TaskType getTaskType();

    /**
     * Updates the task with the given data
     */
    public void update(T data) throws BottyException {
        this.description = data.hasDescription() ? data.getDescription() : this.description;
    }
}

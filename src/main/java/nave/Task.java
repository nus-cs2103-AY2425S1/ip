package nave;

/**
 * The {@code Task} class represents a generic task with a name and a status indicating whether it is done.
 * <p>
 * This is an abstract class that serves as a base for specific types of tasks such as {@code Todo},
 * {@code Deadline}, and {@code Event}. It provides common functionality for marking, unmarking, and
 * displaying tasks.
 * </p>
 */
public abstract class Task {
    private final String name;
    private boolean done;

    /**
     * Constructs a {@code Task} with the specified name.
     * <p>
     * Initializes the task as not done.
     * </p>
     *
     * @param name the name of the task
     */
    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    /**
     * Marks the task as completed.
     * <p>
     * If the task is already marked as done, it does nothing and returns {@code false}.
     * Otherwise, it marks the task as done and returns {@code true}.
     * </p>
     *
     * @return {@code true} if the task was successfully marked; {@code false} if it was already marked
     */
    public boolean mark() {
        if (done) {
            return false;
        } else {
            done = true;
            return true;
        }
    }

    /**
     * Unmarks the task, setting its status to not done.
     * <p>
     * If the task is already not done, it does nothing and returns {@code false}.
     * Otherwise, it unmarks the task and returns {@code true}.
     * </p>
     *
     * @return {@code true} if the task was successfully unmarked; {@code false} if it was already unmarked
     */
    public boolean unmark() {
        if (!done) {
            return false;
        } else {
            done = false;
            return true;
        }
    }

    /**
     * Returns a string representation of the task.
     * <p>
     * The string includes a marker indicating whether the task is done or not, followed by the task's name.
     * </p>
     *
     * @return a string representation of the task, including its completion status and name
     */
    public String toString() {
        String checked = done
                ? "[X] "
                : "[] ";
        return checked + name;
    }

    /**
     * Returns the string format of the task suitable for saving to a csv file.
     * <p>
     * This method provides a basic representation of the task, typically including only the name of the task.
     * Subclasses may override this method to include additional information as needed for their specific formats.
     * </p>
     *
     * @return a string format of the task for file storage
     */
    public String toFileFormat() {
        return name;
    }

    /**
     * Provides a response message that is displayed when the task is created.
     * <p>
     * This is an abstract method that must be implemented by subclasses to return a creation-specific
     * response message tailored to the type of task.
     * </p>
     *
     * @return a string response message indicating task creation details
     */
    abstract String creationResponse();
}

package tasks;

/**
 * The {@code Task} class represents a general task with a name and a completion status.
 * It serves as a base class for more specific types of tasks, such as deadlines and events.
 */
public class Task {

    private final String name;

    private boolean isCompleted;

    /**
     * Constructs a new {@code Task} with the specified name. By default, the task is marked as incomplete.
     *
     * @param name The name or description of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isCompleted = false;
    }

    /**
     * Returns whether the task is completed.
     *
     * @return {@code true} if the task is completed, {@code false} otherwise.
     */
    public boolean isComplete() {
        return this.isCompleted;
    }

    /**
     * Marks the task as completed.
     */
    public void completeTask() {
        this.isCompleted = true;
    }

    /**
     * Returns the name or description of the task.
     *
     * @return The name of the task.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Marks the task as incomplete.
     */
    public void uncompleteTask() {
        this.isCompleted = false;
    }

    /**
     * Returns a string representation of the task, including its completion status and name.
     *
     * @return A string representation of the task in the format "[X] name" if completed,
     *         or "[ ] name" if not completed.
     */
    @Override
    public String toString() {
        if (isCompleted) {
            return ("[X] " + this.name);
        }
        return ("[ ] " + this.name);
    }
}

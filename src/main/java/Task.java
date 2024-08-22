/**
 * Represents a task with a description.
 * This class provides methods to retrieve the task description and
 * represent the task as a string.
 */
public class Task {
    private String description;
    /**
     * Constructs a task with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        System.out.println("____________________________________________________________");
        System.out.println("added:" + description);
        System.out.println("____________________________________________________________");
    }
    /**
     * Returns the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return this.description;
    }
    /**
     * Returns a string representation of the task.
     * @return The description of the task.
     */
    @Override
    public String toString() {
        return description;
    }


}

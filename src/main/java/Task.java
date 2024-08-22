/**
 * Represents a task with a description and completion status.
 * This class provides methods to retrieve the task description,
 * mark the task as done or not done, and represent the task as a string.
 *
 */
public class Task {
    private String description;
    private boolean done;
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
        this.done = false;
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
     * Marks the task as done.
     */
    public void done() {
        this.done = true;
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this);
        System.out.println("____________________________________________________________");
    }

    /**
     * Returns a string representation of the task.
     * @return The description of the task.
     */
    @Override
    public String toString() {
        if (done) {
            return "[X] " + description;
        } else {
            return "[ ] " + description;
        }

    }

    /**
     * Marks the task as not done.
     */
    public void notDone() {
        this.done = false;
        System.out.println("____________________________________________________________");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this);
        System.out.println("____________________________________________________________");
    }
}

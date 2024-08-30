package evelyn.task;

/**
 * Represents a Task.
 */
public class Task {
    private String description;
    private boolean isMarked;

    /**
     * Constructor of a Task object
     * @param desctiption Description of a Task.
     * @param isMarked Status of a Task.
     */
    public Task (String desctiption, boolean isMarked) {
        this.description = desctiption;
        this.isMarked = isMarked;
    }

    /**
     * Marks a task as completed.
     */
    public void mark() {
        this.isMarked = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("    " + this.toString());
    };

    /**
     * Marks a task as incomplete.
     */
    public void unmark() {
        this.isMarked = false;
        System.out.println("Ok! I've unmarked this task:");
        System.out.println("    " + this.toString());
    };

    /**
     * Provides the string representation of a Task.
     * @return The string representation of a Task.
     */
    @Override
    public String toString() {
        return isMarked ? "[X] " + this.description :
                "[ ] " + this.description;
    }

}

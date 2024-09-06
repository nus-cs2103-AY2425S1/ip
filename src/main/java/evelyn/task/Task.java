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
    public Task(String desctiption, boolean isMarked) {
        this.description = desctiption;
        this.isMarked = isMarked;
    }

    /**
     * Marks a task as completed.
     */
    public String mark() {
        this.isMarked = true;
        String str = "Nice! I've marked this task as done:\n";
        str = str + "    " + this.toString() + "\n";
        return str;
    };

    /**
     * Marks a task as incomplete.
     */
    public String unmark() {
        this.isMarked = false;
        String str = "Ok! I've unmarked this task:\n";
        str = str + "    " + this.toString() + "\n";
        return str;
    };

    /**
     * Provides the string representation of a Task.
     * @return The string representation of a Task.
     */
    @Override
    public String toString() {
        return isMarked ? "[X] " + this.description
                : "[ ] " + this.description;
    }

}

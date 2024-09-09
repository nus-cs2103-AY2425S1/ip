package nuffle.task;

/**
 * Represents a to-do task, which is a task without any specific date or time.
 */
public class Todo extends Task {

    /**
     * Constructs a to-do task with the specified description.
     *
     * @param description The description of the to-do task.
     */
    public Todo(String description) {
        // Constructor for Todo class
        super(description);

    }

    /**
     * Returns a string representation of the to-do task,
     * including its type ([T]), status, and description.
     *
     * @return A string representation of the to-do task.
     */
    @Override
    public String toString() {
        // Add a [T] at the front of task description (parent class)
        return "[T]" + super.toString();
    }

    /**
     * Converts the to-do task to a specific string format for saving to a file.
     * The format includes whether the task is done and its description.
     *
     * @return A string representation of the to-do task suitable for saving to a file.
     */
    public String printSaveFormat() {
        String temp;
        if (isDone) {
            temp = "1";
        } else {
            temp = "0";
        }
        return "T | " + temp + " | " + description;
    }
}

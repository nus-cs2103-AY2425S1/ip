package lama.task;

/**
 * Represent a to-do task with a string description and a boolean status indicating whether the task is done.
 * Subclass of Task, that is represented by [T].
 */
public class Todo extends Task {

    /**
     * Construct a to-do task with specified description.
     *
     * @param description Description of a to-do task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Return a string representation of a to-do task.
     * The format of the string is "[T]" task.toString().
     *
     * @return String representation of to-do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Convert the to-do task to a string format that is suitable to save in file.
     * The format of string is "T | status | description".
     * If the status is done, it will show "1", else "0".
     *
     * @return String representation of to-do task in file.
     */
    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}

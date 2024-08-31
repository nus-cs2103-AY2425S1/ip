package weeny;

/**
 * Represents a to-do task with no specific date or time.
 */
public class Todo extends Task {

    /**
     * Creates a new to-do task with the specified description.
     *
     * @param description The description of the to-do task.
     */
    public Todo(String description) {
        super(description, TaskType.TODO);
    }

    /**
     * Returns a string representation of the to-do task for output.
     *
     * @return A string with task details in the format "T | checkMark | description".
     */
    public String toOutput() {
        int checkMark = this.isDone ? 1 : 0;
        return "T | " + checkMark + " | " + this.description;
    }

    @Override
    /**
     * Returns a string representation of the to-do task.
     *
     * @return A string in the format "[T][statusIcon] description".
     */
    public String toString() {
        return "[T]" + super.toString();
    }
}

package babblebot.task;

/**
 * The Todo class represents a simple task with no specific date attached.
 * It extends the Task class and does not add any additional fields.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo task with the specified description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }


    /**
     * Converts the Todo task to a format suitable for file storage.
     *
     * @return A string representation of the Todo task in file format.
     */
    @Override
    public String toFileFormat() {
        if (getStatusIcon().equals("X")) {
            return "T" + " | " + "1" + " | " + this.description;
        } else {
            return "T" + " | " + "0" + " | " + this.description;
        }
    }

    /**
     * Returns a string representation of the Todo task.
     *
     * @return A string representation of the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
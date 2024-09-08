package myapp.helperbuddy;

/**
 * To_Do class representing a To_Do task
 */
public class ToDo extends Task {
    /**
     * Constructs a To_Do task with the specified description.
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description);
        assert description != null : "Description should not be null.";
        assert !description.trim().isEmpty() : "Description should not be empty.";
    }

    /**
     * Parses a string to create a To_Do task.
     * The string should be in the format used for saving to a file, with fields separated by " | ".
     * The fields are: task type ("T"), completion status (1 for done, 0 for not done), and description.
     * @param taskData The string representing the To_Do task.
     * @return A To_Do object created from the string data.
     */
    public static ToDo parseTask(String taskData) {
        assert taskData != null : "Task data should not be null.";
        String[] parts = taskData.split(" \\| ");
        assert parts.length >= 3 : "Task data should have at least 3 parts.";

        String description = parts[2].trim();
        assert !description.isEmpty() : "Description should not be empty.";

        ToDo todo = new ToDo(description);
        if (parts[1].trim().equals("1")) {
            todo.markDone();
        }
        return todo;
    }

    /**
     * Returns a string representation of the To_Do task.
     * The string representation includes the task type ("T"), completion status, and description.
     * For example, a completed task might be represented as: "[T][X] Buy groceries".
     * @return A string representing the To_Do task.
     */
    @Override
    public String toString() {
        assert this.getDescription() != null : "Description should not be null.";
        return "[T][" + (this.getDone() ? "X" : " ") + "] " + this.getDescription();
    }

    /**
     * Converts the To_Do task into a format suitable for saving to a file.
     * The format includes the task type ("T"), completion status (1 for done, 0 for not done), and description.
     * For example, a saved task might be represented as: "T | 1 | Buy groceries".
     * @return A string representing the To_Do task in file format.
     */
    @Override
    public String toFileFormat() {
        assert this.getDescription() != null : "Description should not be null.";
        return "T | " + (this.getDone() ? "1" : "0") + " | " + this.getDescription();
    }
}

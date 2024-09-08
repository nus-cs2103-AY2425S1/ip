package opus;

/**
 * Represents a ToDo task, which is a basic task without any date or time associated with it.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with the specified description.
     *
     * @param description The description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the ToDo task in a format suitable for saving.
     *
     * @return The formatted string representing the ToDo task.
     */
    @Override
    public String toSaveFormat() {
        return "T|" + (isDone ? "1" : "0") + "|" + description;
    }

    /**
     * Creates a ToDo object from a formatted string read from storage.
     *
     * @param fullLine The line read from the storage file.
     * @return A new ToDo object based on the parsed string.
     */
    public static Task fromFileFormat(String fullLine) {
        String[] parts = fullLine.split("\\|");
        ToDo todo = new ToDo(parts[2]);
        if (parts[1].equals("1")) {
            todo.markAsDone();
        }
        return todo;
    }

    /**
     * Returns the string representation of the ToDo task.
     *
     * @return The string representation of the ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + (isDone ? "[X] " : "[ ] ") + description;
    }
}

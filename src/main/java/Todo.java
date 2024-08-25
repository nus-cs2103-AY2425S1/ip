import java.util.StringTokenizer;

/**
 * Represents a Todo task which is a specific type of Task.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo task with the specified description.
     *
     * @param description The description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a new Todo task with the specified description and completion status.
     *
     * @param description The description of the Todo task.
     * @param isDone The completion status of the Todo task.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a string representation of the Todo task, including its type indicator.
     *
     * @return A string representation of the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts the Todo task to a string format suitable for saving to a file.
     * The format includes the type indicator "T", the completion status, and the description of the task.
     *
     * @return A string representation of the Todo task for file storage.
     */
    @Override
    public String toFileEntry() {
        return "T/" + super.toFileEntry();
    }

    /**
     * Parses a StringTokenizer to create a new Todo task.
     *
     * @param tokenizedInput The StringTokenizer containing the description of the Todo task.
     * @return A new Todo task with the parsed description.
     */
    public static Todo parseTodo(StringTokenizer tokenizedInput) {
        StringBuilder description = new StringBuilder();
        String token = tokenizedInput.nextToken();
        description.append(token).append(" ");
        while (tokenizedInput.hasMoreTokens()) {
            token = tokenizedInput.nextToken();
            description.append(token).append(" ");
        }
        return new Todo(description.toString().trim());
    }
}
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
     * Returns a string representation of the Todo task, including its type indicator.
     *
     * @return A string representation of the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
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
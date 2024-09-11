package alfred.task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import alfred.exception.AlfredException;

/**
 * Represents a task of type "To-Do". This class extends the <code>Task</code> class
 * and provides specific functionality for tasks that do not have a fixed deadline
 * or event time.
 */
public class ToDos extends Task {
    private static final String VALID_TODO_FORMAT = "^todo\\s+(.+)$";
    /**
     * Constructs a new <code>ToDos</code> task with the specified description.
     * The task is initially marked as not done.
     *
     * @param description Description of the task.
     */
    public ToDos(String description) {
        super(description);
    }

    /**
     * Constructs a new <code>ToDos</code> task with the specified description and completion status.
     *
     * @param description Description of the task.
     * @param isDone The completion status of the task.
     */
    public ToDos(String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    /**
     * Returns a string representation of the <code>ToDos</code> task.
     * The format includes the task type indicator "[T]" followed by the task details.
     *
     * @return A string representing the <code>ToDos</code> task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Creates a <code>ToDos</code> task from the given input string.
     * The input must match the format: todo description
     * If the input is not in the correct format, an <code>AlfredException</code> is thrown.
     *
     * @param input The input string containing the task description.
     * @return A <code>ToDos</code> task created from the input string.
     * @throws AlfredException If the input string does not match the expected format.
     */
    public static Task createTask(String input) throws AlfredException {
        String[] parsedInput = parseInputForTodo(input);
        String description = parsedInput[0];
        return new ToDos(description);
    }

    /**
     * Parses the input string to extract the task description.
     * Validates the input format using a regular expression.
     *
     * @param input The input string to be parsed.
     * @return An array where the first element is the description.
     * @throws AlfredException If the input format is incorrect.
     */
    private static String[] parseInputForTodo(String input) throws AlfredException {
        Pattern pattern = Pattern.compile(VALID_TODO_FORMAT);
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            return new String[]{matcher.group(1)};
        } else {
            throw new AlfredException("That is the wrong todo format Sir. It goes todo <task>");
        }
    }

    /**
     * Returns the file format representation of the <code>ToDos</code> task.
     * The format is "T | status | description", where "status" is the task's
     * completion status and "description" is the task's description.
     *
     * @return A string representing the <code>ToDos</code> task in file format.
     */
    @Override
    public String toFileFormat() {
        return "T | " + getStatusIcon() + " | " + description;
    }
}

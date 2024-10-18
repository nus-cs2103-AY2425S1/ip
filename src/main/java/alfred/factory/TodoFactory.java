package alfred.factory;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import alfred.exception.AlfredException;
import alfred.task.Task;
import alfred.task.ToDo;

/**
 * Represents a factory class responsible for creating ToDo tasks in the Alfred task management application.
 * This class encapsulates the logic for creating ToDo tasks from user input or file data.
 * <p>
 * It ensures that all ToDo tasks are created in a standardized way, adhering to the expected format.
 * This includes validation of user input and creation of ToDo tasks with associated tags and completion status.
 */
public class TodoFactory extends TaskFactory {
    private static final String VALID_TODO_FORMAT = "^todo\\s+(.+)$";

    /**
     * Creates a ToDo task based on the provided task data and completion status.
     *
     * @param taskData An array containing the task details.
     *                 The third element represents the task description.
     * @param isDone A boolean indicating whether the task is completed.
     * @param tags   A list of tags associated with the task.
     * @return A <code>ToDos</code> object created from the provided task data.
     */
    @Override
    public ToDo createTaskFromData(String[] taskData, boolean isDone, List<String> tags) {
        String description = taskData[3];
        return new ToDo(description, isDone, tags);
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
    @Override
    public Task createTaskFromInput(String input) throws AlfredException {
        String[] parsedInput = parseInputForTodo(input);
        String description = parsedInput[0];
        return new ToDo(description);
    }

    /**
     * Parses the input string to extract the task description.
     * Validates the input format using a regular expression.
     *
     * @param input The input string to be parsed.
     * @return An array where the first element is the description.
     * @throws AlfredException If the input format is incorrect.
     */
    private String[] parseInputForTodo(String input) throws AlfredException {
        Pattern pattern = Pattern.compile(VALID_TODO_FORMAT);
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            return new String[]{matcher.group(1)};
        } else {
            throw new AlfredException("That is the wrong todo format Sir. It goes todo <task>");
        }
    }
}

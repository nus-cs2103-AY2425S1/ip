package alfred.factory;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import alfred.exception.AlfredException;
import alfred.task.Deadline;
import alfred.task.Task;

/**
 * Represents a factory class responsible for creating Deadline tasks in the Alfred task management application.
 * This class encapsulates the logic for creating Deadline tasks from user input or file data.
 * <p>
 * It ensures that all Deadline tasks are created in a standardized way, adhering to the expected format.
 */
public class DeadlineFactory extends TaskFactory {
    private static final String VALID_DEADLINE_FORMAT = "^deadline\\s+(.+?)\\s+/by\\s+(\\d{4}-\\d{2}-\\d{2})$";

    /**
     * Creates a Deadline task based on the provided task data and completion status.
     * It verifies that the input data matches the expected format for a Deadline task.
     *
     * @param taskData A String array containing the task details.
     * @param isDone A boolean indicating whether the task is completed.
     * @param tags A list that contains tags task is associated with.
     * @return A <code>Deadlines</code> object created from the provided task data.
     * @throws AlfredException If the task data is corrupted or does not match the expected Deadline format.
     */
    @Override
    public Deadline createTaskFromData(String[] taskData, boolean isDone,
                                              List<String> tags) throws AlfredException {
        if (taskData.length != 5) {
            throw new AlfredException("Corrupted save: Invalid deadline format");
        }
        String description = taskData[3];
        String deadline = taskData[4];
        return new Deadline(description, deadline, isDone, tags);
    }

    /**
     * Creates a Deadlines task from the input string.
     * The input should be in the format: deadline task /by yyyy-mm-dd.
     *
     * @param input The input string containing the task details.
     * @return A Deadlines object with the specified description and deadline date.
     * @throws AlfredException If the input string does not match the expected format.
     */
    @Override
    public Task createTaskFromInput(String input) throws AlfredException {
        String[] parsedInput = parseInputForDeadline(input);
        String description = parsedInput[0];
        String deadline = parsedInput[1];

        return new Deadline(description, deadline);
    }

    /**
     * Parses the input string to extract the task description and deadline.
     * Validates the input format using a regular expression.
     *
     * @param input The input string to be parsed.
     * @return An array where the first element is the description and the second element is the deadline.
     * @throws AlfredException If the input format is incorrect.
     */
    private String[] parseInputForDeadline(String input) throws AlfredException {
        Pattern pattern = Pattern.compile(VALID_DEADLINE_FORMAT);
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            return new String[]{matcher.group(1), matcher.group(2)};
        } else {
            throw new AlfredException("That is the wrong deadline format Sir. It goes deadline <task> "
                    + "/by yyyy-mm-dd");
        }
    }
}

package alfred.factory;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import alfred.exception.AlfredException;
import alfred.task.Event;
import alfred.task.Task;

/**
 * Represents a factory class responsible for creating Event tasks in the Alfred task management application.
 * This class encapsulates the logic for creating Event tasks from user input or file data.
 * <p>
 * It ensures that all Event tasks are created in a standardized way, adhering to the expected format.
 */
public class EventFactory extends TaskFactory {
    private static final String VALID_EVENT_FORMAT = "^event\\s+(.+?)\\s+/from\\s+"
            + "(\\d{4}-\\d{2}-\\d{2})\\s+/to\\s+(\\d{4}-\\d{2}-\\d{2})$";

    /**
     * Creates an Event task based on the provided task data and completion status.
     * It verifies that the input data matches the expected format for an Event task.
     *
     * @param taskData A String array containing the task details.
     * @param isDone A boolean indicating whether the task is completed.
     * @param tags A list that contains tags task is associated with
     * @return An <code>Events</code> object created from the provided task data.
     * @throws AlfredException If the task data is corrupted or does not match the expected Event format.
     */
    @Override
    public Event createTaskFromData(String[] taskData, boolean isDone,
                                           List<String> tags) throws AlfredException {
        if (taskData.length != 6) {
            throw new AlfredException("Corrupted save: Invalid event format");
        }
        String description = taskData[3];
        String from = taskData[4];
        String to = taskData[5];
        return new Event(description, from, to, isDone, tags);
    }

    /**
     * Creates an <code>Events</code> task from the given input string.
     * The input must match the format: event description /from yyyy-mm-dd /to yyyy-mm-dd.
     * If the input is not in the correct format,
     * an <code>AlfredException</code> is thrown.
     *
     * @param input The input string containing event details.
     * @return An <code>Events</code> task created from the input string.
     * @throws AlfredException If the input string does not match the expected format.
     */
    @Override
    public Task createTaskFromInput(String input) throws AlfredException {
        String[] parsedInput = parseInputForEvent(input);
        String description = parsedInput[0];
        String from = parsedInput[1];
        String to = parsedInput[2];
        return new Event(description, from, to);
    }

    /**
     * Parses the input string to extract the task description, from and to dates.
     * Validates the input format using a regular expression.
     *
     * @param input The input string to be parsed.
     * @return An array where the first element is the description, second from and third to.
     * @throws AlfredException If the input format is incorrect.
     */
    private String[] parseInputForEvent(String input) throws AlfredException {
        Pattern pattern = Pattern.compile(VALID_EVENT_FORMAT);
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            return new String[]{matcher.group(1), matcher.group(2), matcher.group(3)};
        } else {
            throw new AlfredException("That is the wrong events format Sir. It goes event <task> "
                    + "/from yyyy-mm-dd /to yyyy-mm-dd");
        }
    }
}

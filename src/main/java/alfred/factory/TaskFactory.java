package alfred.factory;

import static alfred.task.Task.NO_TAG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import alfred.exception.AlfredException;
import alfred.task.Task;

/**
 * Represents an abstract factory class for creating tasks in the Alfred task management application.
 * This class provides common constants and methods that are used by concrete task factories
 * such as ToDoFactory, EventFactory, and DeadlineFactory to create task objects.
 */
public abstract class TaskFactory {
    private static final List<String> TASKS = Arrays.asList("todo", "deadline", "event");
    private static final String TODO_SYMBOL = "T";
    private static final String DEADLINE_SYMBOL = "D";
    private static final String EVENT_SYMBOL = "E";
    private static final String TODO = "todo";
    private static final String EVENT = "event";
    private static final String DEADLINE = "deadline";

    /**
     * Determines if the given input string is a valid command to create a task.
     *
     * @param input The input string to be checked.
     * @return <code>true</code> if the input matches a known task type, <code>false</code> otherwise.
     */
    public static boolean isCreateTaskCommand(String input) {
        String[] parts = input.split(" ");
        return TASKS.contains(parts[0]);
    }

    /**
     * Initializes a task based on the input string.
     * The input must start with a task type, followed by task details.
     *
     * @param input The input string containing task details.
     * @return A <code>Task</code> object created from the input string.
     * @throws AlfredException If the input string does not match any known task type.
     */
    public static Task initialise(String input) throws AlfredException {
        String taskType = input.split(" ")[0];
        return createTaskFromType(input, taskType);
    }

    /**
     * Creates a task based on the task type extracted from the taskData array.
     * This method delegates the task creation to the appropriate class depending
     * on whether the task is a "todo", "event", or "deadline".
     *
     * @param input    The input string that contains the full task details.
     * @param taskType A string array containing the type of task
     * @return A Task object created from the provided input.
     * @throws AlfredException If the task type is invalid or unrecognized.
     */
    private static Task createTaskFromType(String input, String taskType) throws AlfredException {
        TaskFactory factory;

        switch (taskType) {
        case TODO:
            factory = new TodoFactory();
            break;
        case EVENT:
            factory = new EventFactory();
            break;
        case DEADLINE:
            factory = new DeadlineFactory();
            break;
        default:
            throw new AlfredException("Invalid task: " + taskType);
        }
        return factory.createTaskFromInput(input);
    }

    /**
     * Creates a task from its file format representation.
     * The file format should include task type, status, description, and possibly additional details.
     *
     * @param fileFormat The string representing the task in file format.
     * @return A <code>Task</code> object created from the file format string.
     * @throws AlfredException If the file format is corrupted or unknown.
     */
    public static Task fromFileFormat(String fileFormat) throws AlfredException {
        String[] taskData = parseTaskData(fileFormat);
        String taskType = taskData[0];
        List<String> tags = parseTags(taskData);
        boolean isDone = parseTaskCompletionStatus(taskData);

        TaskFactory factory;

        switch (taskType) {
        case TODO_SYMBOL:
            factory = new TodoFactory();
            break;
        case EVENT_SYMBOL:
            factory = new EventFactory();
            break;
        case DEADLINE_SYMBOL:
            factory = new DeadlineFactory();
            break;
        default:
            throw new AlfredException("Corrupted save: Unknown task type");
        }
        return factory.createTaskFromData(taskData, isDone, tags);
    }

    /**
     * Creates a Task from the input string.
     * This must be implemented by subclasses to create the correct task type.
     *
     * @param input The input string containing the task description.
     * @return A Task created from the input string.
     * @throws AlfredException If the input format is incorrect.
     */
    public abstract Task createTaskFromInput(String input) throws AlfredException;

    /**
     * Creates a Task from the provided task data array.
     * This must be implemented by subclasses to create the correct task type.
     *
     * @param taskData An array containing the task details.
     * @param isDone A boolean indicating whether the task is completed.
     * @param tags   A list of tags associated with the task.
     * @return A Task object created from the task data.
     * @throws AlfredException If the data format is incorrect.
     */
    public abstract Task createTaskFromData(String[] taskData, boolean isDone, List<String> tags)
            throws AlfredException;

    /**
     * Parses the third element of the task data array into a list of tags.
     * If the third element contains "No Tags", returns an empty list.
     * Otherwise, the tags are expected to be space-separated within the third element.
     *
     * @param taskData The array of task data, where the third element contains space-separated tags.
     * @return A list of tags parsed from the third element of the task data.
     *         If the element is "No Tags" or no tags are found, the list will be empty.
     */
    private static List<String> parseTags(String[] taskData) {
        String tagsString = taskData[2].trim();

        if (tagsString.equals(NO_TAG)) {
            return new ArrayList<>();
        }
        return Arrays.asList(tagsString.split(" "));
    }

    /**
     * Parses the task completion status from the task data.
     * Checks if the second element of the taskData array indicates
     * whether the task is completed ("X" for done).
     *
     * @param taskData A String array containing the task details.
     * @return <code>true</code> if the task is marked as done, <code>false</code> otherwise.
     */
    private static boolean parseTaskCompletionStatus(String[] taskData) {
        return taskData[1].equals("X");
    }

    /**
     * Parses and trims the task data from the file format string.
     * It splits the input string into its components (task type, status, description, etc.)
     * and ensures that the data is properly formatted and cleaned.
     *
     * @param fileFormat The string representing the task in file format.
     * @return A trimmed array of strings, each representing a part of the task's information.
     * @throws AlfredException If the file format is corrupted or contains insufficient data.
     */
    private static String[] parseTaskData(String fileFormat) throws AlfredException {
        String[] taskData = fileFormat.split("\\|");
        if (taskData.length < 4) {
            throw new AlfredException("Corrupted save: Invalid task format");
        }
        for (int i = 0; i < taskData.length; i++) {
            taskData[i] = taskData[i].trim();
        }
        return taskData;
    }
}

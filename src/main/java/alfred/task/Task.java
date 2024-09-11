package alfred.task;

import java.util.Arrays;
import java.util.List;

import alfred.exception.AlfredException;

/**
 * Represents an abstract task with a description and completion status.
 * This class provides methods for marking tasks as done or undone,
 * and for handling different task types such as "ToDos", "Deadlines", and "Events".
 */
public abstract class Task {
    private static final List<String> TASKS = Arrays.asList("todo", "deadline", "event");
    private static final String TODO_SYMBOL = "T";
    private static final String DEADLINE_SYMBOL = "D";
    private static final String EVENT_SYMBOL = "E";

    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new <code>Task</code> with the specified description.
     * The task is initially marked as not done.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the task's status as a string icon.
     *
     * @return "X" if the task is done, otherwise a space character.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Checks if the task description contains the specified keyword.
     *
     * @param keyword The keyword to search for in the task description.
     * @return True if the description contains the keyword, false otherwise.
     */
    public boolean containsKeyword(String keyword) {
        return description.contains(keyword);
    }

    /**
     * Marks the task as completed by setting its status to done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not completed by resetting its status.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Returns a string representation of the task, including its status and description.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

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
        String[] taskData = input.split(" ");
        return createTaskFromType(input, taskData);
    }

    /**
     * Creates a task based on the task type extracted from the taskData array.
     * This method delegates the task creation to the appropriate class depending
     * on whether the task is a "todo", "event", or "deadline".
     *
     * @param input The input string that contains the full task details.
     * @param taskData A string array containing the task information
     * @return A Task object created from the provided input.
     * @throws AlfredException If the task type is invalid or unrecognized.
     */
    private static Task createTaskFromType(String input, String[] taskData) throws AlfredException {
        switch (taskData[0]) {
        case "todo":
            return ToDos.createTask(input);
        case "event":
            return Events.createTask(input);
        case "deadline":
            return Deadlines.createTask(input);
        default:
            throw new AlfredException("Invalid task: " + taskData[0]);
        }
    }

    /**
     * Returns the file format representation of the task.
     * The format includes task type, status, and description, and may include additional details.
     *
     * @return A string representing the task in file format.
     */
    public abstract String toFileFormat();

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
        boolean isDone = parseTaskCompletionStatus(taskData);

        switch (taskType) {
        case TODO_SYMBOL:
            return createTodoTask(taskData, isDone);
        case DEADLINE_SYMBOL:
            return createDeadlineTask(taskData, isDone);
        case EVENT_SYMBOL:
            return createEventTask(taskData, isDone);
        default:
            throw new AlfredException("Corrupted save: Unknown task type");
        }
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
     * Creates an Event task based on the provided task data and completion status.
     * It verifies that the input data matches the expected format for an Event task.
     *
     * @param taskData A String array containing the task details.
     * @param isDone A boolean indicating whether the task is completed.
     * @return An <code>Events</code> object created from the provided task data.
     * @throws AlfredException If the task data is corrupted or does not match the expected Event format.
     */
    private static Events createEventTask(String[] taskData, boolean isDone) throws AlfredException {
        if (taskData.length != 5) {
            throw new AlfredException("Corrupted save: Invalid event format");
        }
        String description = taskData[2];
        String from = taskData[3];
        String to = taskData[4];
        return new Events(description, from, to, isDone);
    }

    /**
     * Creates a Deadline task based on the provided task data and completion status.
     * It verifies that the input data matches the expected format for a Deadline task.
     *
     * @param taskData A String array containing the task details.
     * @param isDone A boolean indicating whether the task is completed.
     * @return A <code>Deadlines</code> object created from the provided task data.
     * @throws AlfredException If the task data is corrupted or does not match the expected Deadline format.
     */
    private static Deadlines createDeadlineTask(String[] taskData, boolean isDone) throws AlfredException {
        if (taskData.length != 4) {
            throw new AlfredException("Corrupted save: Invalid deadline format");
        }
        String description = taskData[2];
        String deadline = taskData[3];
        return new Deadlines(description, deadline, isDone);
    }

    /**
     * Creates a ToDo task based on the provided task data and completion status.
     *
     * @param taskData An array containing the task details.
     *                 The third element represents the task description.
     * @param isDone A boolean indicating whether the task is completed.
     * @return A <code>ToDos</code> object created from the provided task data.
     */
    private static ToDos createTodoTask(String[] taskData, boolean isDone) {
        String description = taskData[2];
        return new ToDos(description, isDone);
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
        if (taskData.length < 3) {
            throw new AlfredException("Corrupted save: Invalid task format");
        }
        for (int i = 0; i < taskData.length; i++) {
            taskData[i] = taskData[i].trim();
        }
        return taskData;
    }
}

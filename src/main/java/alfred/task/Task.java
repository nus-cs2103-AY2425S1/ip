package alfred.task;

import java.util.ArrayList;
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
    private static final String TODO = "todo";
    private static final String EVENT = "event";
    private static final String DEADLINE = "deadline";
    private static final String NO_TAG = "No Tags";

    protected String description;
    protected boolean isDone;
    private List<String> tags;

    /**
     * Constructs a new <code>Task</code> with the specified description.
     * The task is initially marked as not done.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tags = new ArrayList<>();
    }

    /**
     * Constructs a new <code>Task</code> with the specified description, done status and tags.
     *
     * @param description Description of the task.
     * @param isDone The completion status of the task.
     * @param tags The list of tags associated with the task
     */
    public Task(String description, boolean isDone, List<String> tags) {
        this.description = description;
        this.isDone = isDone;
        this.tags = new ArrayList<>(tags);
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
     * Adds a tag to the task.
     *
     * @param tag The tag to assign to the task.
     */
    public void addTag(String tag) {
        tags.add(tag);
    }

    /**
     * Removes the tag from the task.
     * If the tag is not present, the list remains unchanged.
     *
     * @param tag The tag to remove from the task.
     */
    public void untag(String tag) {
        tags.remove(tag);
    }

    /**
     * Returns the tags formatted as a string.
     * If the tag list is empty, returns "No Tags".
     *
     * @return A string representing the tags, separated by spaces. If no tags exist, returns "No Tags".
     */
    public String getTagsAsString() {
        if (tags.isEmpty()) {
            return NO_TAG;
        }
        return String.join(" ", tags);
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
        String taskType = input.split(" ")[0];
        return createTaskFromType(taskType, input);
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
    private static Task createTaskFromType(String taskType, String input) throws AlfredException {
        switch (taskType) {
        case TODO:
            return ToDos.createTaskFromInput(input);
        case EVENT:
            return Events.createTaskFromInput(input);
        case DEADLINE:
            return Deadlines.createTaskFromInput(input);
        default:
            throw new AlfredException("Invalid task: " + taskType);
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
        List<String> tags = parseTags(taskData);
        boolean isDone = parseTaskCompletionStatus(taskData);

        switch (taskType) {
        case TODO_SYMBOL:
            return ToDos.createTaskFromData(taskData, isDone, tags);
        case EVENT_SYMBOL:
            return Events.createTaskFromData(taskData, isDone, tags);
        case DEADLINE_SYMBOL:
            return Deadlines.createTaskFromData(taskData, isDone, tags);
        default:
            throw new AlfredException("Corrupted save: Unknown task type");
        }
    }

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

    /**
     * Returns a string representation of the task, including its status and description.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s", getStatusIcon(), getTagsAsString(), description);
    }
}

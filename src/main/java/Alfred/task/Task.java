package alfred.task;

import alfred.exception.AlfredException;

import java.util.Arrays;
import java.util.List;

/**
 * Represents an abstract task with a description and completion status.
 * This class provides methods for marking tasks as done or undone,
 * and for handling different task types such as "ToDos", "Deadlines", and "Events".
 */
abstract public class Task {
    protected String description;
    protected boolean isDone;

    private static final List<String> TASKS = Arrays.asList("todo", "deadline", "event");

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
        String[] parts = input.split(" ");
        switch (parts[0]) {
        case "todo":
            return ToDos.createTask(input);
        case "event":
            return Events.createTask(input);
        case "deadline":
            return Deadlines.createTask(input);
        default:
            throw new AlfredException("Invalid task: " + parts[0]);
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
        String[] parts = fileFormat.split("\\|");
        if (parts.length < 3) {
            throw new AlfredException("Corrupted save: Invalid task format");
        }

        // Trim parts
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("X");
        String description = parts[2];

        switch (type) {
        case "T":
            return new ToDos(description, isDone);
        case "D":
            if (parts.length != 4) {
                throw new AlfredException("Corrupted save: Invalid deadline format");
            }
            String deadline = parts[3];
            return new Deadlines(description, deadline, isDone);
        case "E":
            if (parts.length != 5) {
                throw new AlfredException("Corrupted save: Invalid event format");
            }
            String from = parts[3];
            String to = parts[4];
            return new Events(description, from, to, isDone);
        default:
            throw new AlfredException("Corrupted save: Unknown task type");
        }
    }
}

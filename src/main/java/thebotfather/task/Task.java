package thebotfather.task;

import java.time.format.DateTimeFormatter;
import java.util.Objects;

import thebotfather.util.TheBotFatherException;


/**
 * Represents a task with a description, completion status, and a type identifier.
 * This class serves as an abstract base for more specific types of tasks.
 */
public abstract class Task {

    /**
     * Formatter for input date and time strings.
     */
    protected static final DateTimeFormatter DATE_STRING_FORMATTER =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    /**
     * Formatter for printing date and time in a more readable format.
     */
    protected static final DateTimeFormatter DATE_STRING_FORMATTER_PRINT =
            DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm");

    /**
     * Indicates whether the task is completed.
     */
    private boolean isDone = false;

    /**
     * The description of the task.
     */
    private final String description;

    /**
     * The type of the task, e.g., "T" for Todo, "D" for Deadline, "E" for Event.
     */
    private final String type;

    /**
     * Constructs a Task with the specified description and type.
     *
     * @param description The description of the task.
     * @param type        The type identifier for the task.
     * @throws TheBotFatherException If the description is empty or null.
     */
    public Task(String description, String type) throws TheBotFatherException {
        if (Objects.equals(description, "")) {
            throw new TheBotFatherException("Kid, Learn to read, where is the description??");
        }
        assert description != null && !description.trim().isEmpty() : "Description cannot be null or empty";
        assert type != null && !type.trim().isEmpty() : "Task type cannot be null or empty";
        this.description = description;
        this.type = type;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Checks if the task is done.
     *
     * @return true if the task is done, false otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Checks if the task description contains the specified word.
     * <p>
     * This method evaluates whether the task's description includes the given word as a substring.
     * The comparison is case-sensitive, meaning that the method will only return true if the exact
     * word (with the same casing) is found in the description.
     *
     * @param word The word to search for within the task's description.
     * @return {@code true} if the description contains the specified word; {@code false} otherwise.
     */
    public boolean isWordInDescription(String word) {
        return description.contains(word);
    }

    /**
     * Returns the status icon representing whether the task is done.
     *
     * @return "X" if done, otherwise a space character.
     */
    protected String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns a string representation of the task, including its type, status, and description.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + this.type + "][" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Converts the task to a string format suitable for saving to a file.
     *
     * @return A string representation of the task for file storage.
     */
    public String toFile() {
        return this.type + " | " + (this.isDone() ? "1" : "0") + " | " + this.description;
    }
}

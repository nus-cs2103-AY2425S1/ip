package dipsy.task;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a generic task in the task management system.
 * A task has a description and can be marked as done or undone.
 */
public class Task {
    protected static final String DELIMITER = "|";
    protected String description;
    protected boolean isDone;
    /**
     * Constructs a new {@code Task} with the specified description.
     * By default, the task is marked as undone.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a new {@code Task} with the specified description and completion status.
     *
     * @param description The description of the task.
     * @param isDone      The initial completion status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Returns the status icon of the task.
     * A done task is marked with 'X', and an undone task is marked with a space ' '.
     *
     * @return The status icon representing whether the task is done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Wraps the provided string in quotes.
     *
     * @param str The string to wrap.
     * @return The string wrapped in quotes.
     */
    protected String wrapInQuotes(String str) {
        return "\"" + str + "\"";
    }

    /**
     * Returns the type of the task. This method is intended to be overridden by subclasses.
     *
     * @return A string representing the task type, defaulting to "Task".
     */
    protected String getTaskType() {
        return "Task";
    }

    /**
     * Returns the relevant date associated with the task. By default, this is {@code null} for generic tasks.
     * This method is intended to be overridden by subclasses (e.g., deadline or event tasks).
     *
     * @return The relevant date for the task, or {@code null} if not applicable.
     */
    public LocalDate getRelevantDate() {
        return null; // By default, no relevant date for a generic task
    }

    /**
     * Formats the task data into a CSV format, where each field is wrapped in quotes
     * and separated by a delimiter.
     *
     * @return A string representing the task data in CSV format.
     */
    public String formatToCsv() {
        String[] arr = new String[]{
                wrapInQuotes(this.getStatusIcon()),
                wrapInQuotes(this.getTaskType()),
                wrapInQuotes(this.description)
        };
        return String.join(DELIMITER, arr);
    }

    /**
     * Checks if the task description contains the specified keyword.
     * The search is case-insensitive.
     *
     * @param keyword The keyword to search for in the description.
     * @return true if the keyword is found in the description, false otherwise.
     */
    public boolean hasKeywordInDescription(String keyword) {
        if (keyword == null || description == null) {
            return false;
        }

        // Use word boundaries to ensure exact word matches
        String regex = "\\b" + Pattern.quote(keyword.toLowerCase()) + "\\b";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(description.toLowerCase());

        return matcher.find();
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}

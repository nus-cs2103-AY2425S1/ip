package easton.model;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.function.Predicate;

/**
 * Represents a task.
 */
public abstract class Task {

    public static final DateTimeFormatter DATE_TIME_PARSE_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy H:mm");
    public static final DateTimeFormatter DATE_TIME_PRINT_FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy H:mm");
    private String description;
    private boolean isDone;

    /**
     * Constructs a new task with the specified description.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns an icon representation the completion of the task.
     *
     * @return The icon of the completion of the task.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Returns a csv string representation of the task.
     *
     * @return The csv string representation of the task.
     */
    public String getCsvFormat() {
        String isDoneBinaryValue = isDone ? "1" : "0";
        return isDoneBinaryValue + "," + description;
    }

    /**
     * Checks if the keywords are in the description.
     *
     * @param keywords Keywords to search.
     * @return If the keyword is in the description.
     */
    public boolean hasKeywords(String ... keywords) {
        String beforeWordRegex = ".*\\b";
        String afterWordRegex = "\\b.*";
        Predicate<String> hasKeyword = word -> description.matches(beforeWordRegex + word + afterWordRegex);
        return Arrays.stream(keywords).anyMatch(hasKeyword);
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}

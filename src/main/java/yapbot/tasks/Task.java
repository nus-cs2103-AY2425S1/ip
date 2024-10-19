package yapbot.tasks;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

import yapbot.exceptions.YapBotException;

/**
 * Parent class of all tasks that can be created for YapBot.
 */
public class Task {
    // Formats used for parsing of date and time from user input, locale is explicity set to en_sg for compatibility
    private static final Locale LOCALE = new Locale("en_sg");
    protected static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("ha yyyy/MM/dd",
            LOCALE);
    protected static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd", LOCALE);
    protected static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("ha", LOCALE);

    // Format for displaying date and time to user
    protected static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("ha dd MMM yyyy", LOCALE);

    private String description;
    private boolean isDone;

    /**
     * Returns a Task instance.
     *
     * @param description Details of the Task.
     * @throws YapBotException If task detail is empty.
     */
    public Task(String description) throws YapBotException {
        if (description.isEmpty()) {
            throw new YapBotException("Error, Automated Task Suggestion module offline."
                    + "\nTask details must be manually entered.");
        }

        this.description = description;
        this.isDone = false;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Checks if the Task description contains the query String.
     *
     * @param query Keyword to check for in Task description.
     * @return true if the keyword is present in Task description and false otherwise.
     */
    public boolean checkTaskname(String query) {
        return this.description.contains(query);
    }

    /**
     * Returns the string representation of the task to be saved on a file.
     * The string is formatted such that YapBot can create the task again by parsing it.
     */
    public String saveTask() {
        if (this.isDone) {
            return "1/" + this.description;
        } else {
            return "0/" + this.description;
        }
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }
}

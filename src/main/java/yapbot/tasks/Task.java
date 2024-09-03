package yapbot.tasks;

import yapbot.exceptions.YapBotException;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Parent class of all tasks that can be created for YapBot.
 */
public class Task {
    private String description;
    private boolean isDone;

    // Formats used for parsing of date and time from user input, locale is explicity set to en_sg for compatibility
    private static final Locale LOCALE = new Locale("en_sg");
    protected static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("ha yyyy/MM/dd",
            LOCALE);
    protected static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd", LOCALE);
    protected static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("ha", LOCALE);

    // Format for displaying date and time to user
    protected static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("ha dd MMM yyyy", LOCALE);

    /**
     * Returns a Task instance.
     *
     * @param description Details of the Task.
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
     * Returns the string representation of the task that is parseable by YapBot.
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

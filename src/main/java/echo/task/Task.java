package echo.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Represents a Task with description about the task
 *
 * @author Ernest Lim
 */
public abstract class Task {

    /** Input formatter for when users type in the deadlines*/
    protected static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

    /** Output formatter when the ChatBot prints the deadline of the tasks*/
    protected static final DateTimeFormatter OUTPUT_FORMATTER =
            DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
    private boolean isCompleted;
    private String description;
    /**
     * Constructor for Task
     *
     * @param description what the task is about
     */
    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    /**
     * Returns the Task as a fancier string with its status and description
     *
     * @return Fancier string of the Task
     */
    public abstract String toFancyString();

    /**
     * Returns the Task as a string with its status and description
     *
     * @return String of the Task
     */
    @Override
    public String toString() {
        if (this.isCompleted) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }

    /**
     * Returns the status of the Task
     *
     * @return 1 if the task is marked as completed and 0 if the task is unmarked
     */
    public int getStatus() {
        return isCompleted ? 1 : 0;
    }

    /**
     * Returns the description of the task
     *
     * @return returns the description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the isCompleted variable to true and prints out a display message
     */
    public void mark() {
        this.isCompleted = true;
    }


    /**
     * Sets the isCompleted variable to false and prints out a display message
     */
    public void unmark() {
        this.isCompleted = false;
    }

    /**
     * Converts the LocalDateTime object back to a String that is provided by users
     * Converts from 1234-12-11T12:56 to 11-12-1234 1256
     *
     * @return String that is provided by users
     */
    public String reverseLocalDateTimeParse(LocalDateTime dateTime) {
        return dateTime.format(INPUT_FORMATTER);
    }
}

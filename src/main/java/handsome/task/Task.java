package handsome.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * The Task class is an abstract base class representing a task in the
 * Handsome chatBot application. It provides common properties and methods for
 * managing task content, status, and formatting dates.
 */
public abstract class Task {
    private final String content;
    private String isDone;

    /**
     * Constructs a Task object with the specified content and status.
     *
     * @param content The description of the task.
     * @param isDone  The status of the task, indicating whether it is done or not.
     */
    public Task(String content, String isDone) {
        this.content = content;
        this.isDone = isDone;
    }

    /**
     * Marks the task as not done by setting its status to "[ ]".
     */
    public void markDone() {
        this.isDone = "[X]";
    }

    /**
     * Marks the task as not done by setting its status to "[ ]".
     */
    public void markUndone() {
        this.isDone = "[ ]";
    }

    /**
     * Returns the task in a specific data format used for saving to the storage file.
     *
     * @return A formatted string representing the task for data storage.
     */
    public String toDataFormat() {
        return isDone + " | " + content;
    };

    /**
     * Checks if the task's content contains the specified keyword.
     *
     * @param keyword The keyword to search for within the task's content.
     * @return true if the task's content contains the keyword, false otherwise.
     */
    public boolean contains(String keyword) {
        return content.contains(keyword);
    }

    /**
     * Formats a date string into a more readable format. The input date string
     * should be in the format "yyyy-MM-dd HHmm".
     * The formatted date is returned in the format "Day, dd MMM yyyy, hh:mm a".
     *
     * @param date The date string to be formatted.
     * @return A formatted date string.
     */
    public String formatDate(String date) {
        String[] dateAndTime = date.split(" ");
        LocalDate d = LocalDate.parse(dateAndTime[0]);
        LocalTime t = LocalTime.parse(dateAndTime[1], DateTimeFormatter.ofPattern("HHmm"));
        return d.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + ", " + d.getDayOfMonth() + " "
                + d.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + " " + d.getYear() + ", "
                + t.format(DateTimeFormatter.ofPattern("hh:mm a"));
    }

    @Override
    public String toString() {
        return isDone + " " + content + " ";
    }
}

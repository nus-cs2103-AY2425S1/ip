package duke;

import java.time.LocalDate;
import java.time.format.TextStyle;

import java.util.Locale;

/**
 * Represents a Task item with a description.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    private static final String STATUS_MARKED = "[X] ";
    private static final String STATUS_UNMARKED = "[ ] ";

    public Task(String description) {
        this.description = description.trim();
        this.isDone = false;
    }
    public String getDescription() { return description; }

    /**
     * Returns String value to represent if the task is done.
     * If done, '[X]' is returned. Else, '[ ]' is returned.
     *
     * @returns String representation of whether the task is done.
     * */
    public String getStatusIcon() {
        return (isDone ? STATUS_MARKED : STATUS_UNMARKED);
    }

    /**
     * Marks this task as done.
     * */
    public void mark() {
        isDone = true;
    }

    /**
     * Marks this task as not done.
     * */
    public void unmark() {
        isDone = false;
    }

    /**
     * Returns date of this task, if any.
     *
     * @returns String representation of the date of the task.
     * */
    public String getDates() {
        return "";
    }

    /**
     * Converts the input date from type LocalDate to type String.
     *
     * @param date Date to be converted, of LocalDate type.
     * @return String representation of d.
     * */
    protected String convertDates(LocalDate date) {
        String month = date.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
        return  month + " " + date.getDayOfMonth() + " " + date.getYear();
    }

    /**
     * Returns description of task, when TaskList.printTasks() is called.
     *
     * @return String task description.
     * */
    public String toString() {
        return getStatusIcon() + description + " ";
    }

}

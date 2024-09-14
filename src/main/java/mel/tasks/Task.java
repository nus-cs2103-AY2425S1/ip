package mel.tasks;

import java.time.LocalDateTime;

import mel.exceptions.ParseException;
import mel.utils.Parser;

/**
 * Task class represents a task.
 */
public class Task {
    protected String task;
    protected boolean isDone;

    /**
     * Returns a LocalDateTime representing date-time, by parsing
     * a date and/or time input of valid format.
     * @param str date and/or time input string.
     * @return parsed date-time.
     * @throws ParseException if date-time input cannot be parsed
     *      or is of invalid format.
     * @see LocalDateTime
     */
    protected LocalDateTime parseDateTime(String str) throws ParseException {
        return new Parser().parseDateTime(str);
    }

    /**
     * Marks a task to indicate its completion.
     */
    public void markTaskAsDone() {
        isDone = true;
    }

    /**
     * Removes a task's mark to indicate it is incomplete.
     */
    public void markTaskAsNotDone() {
        isDone = false;
    }

    /**
     * Checks if the requested user input
     * matches the task detail.
     * @param str input string.
     * @return boolean of match result,
     *      where True - match,
     *      False - no match.
     */
    public boolean isMatch(String str) {
        return task.contains(str);
    }

    /**
     * Returns status of task as string in save file format.
     * @return Status of task as string.
     */
    public String toSaveString() {
        return isDone ? "X" : " ";
    }

    /**
     * Returns status of task as string.
     * @return Status of task as string.
     */
    @Override
    public String toString() {
        String s = isDone ? "X" : " ";
        return "[" + s + "] ";
    }
}

package mel.tasks;

import mel.exceptions.ParseException;
import mel.utils.Parser;

import java.time.LocalDateTime;

/**
 * Task class represents a task.
 */
public class Task {
    protected boolean isDone;

    /**
     * Returns a LocalDateTime representing date-time, by parsing
     * a date and/or time input of valid format.
     * @param str date and/or time input string.
     * @return parsed date-time.
     * @throws ParseException if date-time input cannot be parsed
     * or is of invalid format.
     * @see LocalDateTime
     */
    protected LocalDateTime parseDateTime(String str) throws ParseException {
        return new Parser().parseDateTime(str);
    }

    /**
     * Marks a task to indicate its completion.
     */
    public void mark() {
        isDone = true;
        System.out.println("  " + this);
    }

    /**
     * Removes a task's mark to indicate it is incomplete.
     */
    public void unmark() {
        isDone = false;
        System.out.println("  " + this);
    }

    @Override
    public String toString() {
        String s = isDone ? "X" : " ";
        return "[" + s + "] ";
    }
}

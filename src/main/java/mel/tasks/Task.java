package mel.tasks;

import mel.exceptions.ParseException;
import mel.utils.Parser;

import java.time.LocalDateTime;

public class Task {
    protected String task;
    protected boolean isDone;

    protected LocalDateTime parseDateTime(String str) throws ParseException {
        return new Parser().parseDateTime(str);
    }

    public void mark() {
        isDone = true;
        System.out.println("  " + this);
    }

    public void unmark() {
        isDone = false;
        System.out.println("  " + this);
    }

    /**
     * Checks if the requested user input
     * matches the task detail.
     * @param str input string.
     * @return boolean of match result,
     * where True - match,
     * False - no match.
     */
    public boolean isMatch(String str) {
        return task.contains(str);
    }

    @Override
    public String toString() {
        String s = isDone ? "X" : " ";
        return "[" + s + "] " + task;
    }
}

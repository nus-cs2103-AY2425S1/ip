package mel.tasks;

import mel.exceptions.ParseException;
import mel.utils.Parser;

import java.time.LocalDateTime;

public class Task {
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

    @Override
    public String toString() {
        String s = isDone ? "X" : " ";
        return "[" + s + "] ";
    }
}

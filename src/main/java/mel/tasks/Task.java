package mel.tasks;

import mel.utils.Parser;

import java.time.LocalDateTime;

public class Task {
    protected final String task;
    protected boolean isDone;

    public Task() {
        task = "";
    }

    protected LocalDateTime parseDateTime(String str) {
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
        return "[" + s + "] " + task;
    }
}

package reo;

public class Task {
    private boolean isDone;
    private String name;
    public static int boolToInt(boolean b) {
        return b ? 1 : 0;
    }

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }
    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public boolean nameDoesContain(String s) {
        return this.name.contains(s);
    }

    @Override
    public String toString() {
        String checkbox;
        if (isDone) {
            checkbox = "[X] ";
        } else {
            checkbox = "[ ] ";
        }
        return checkbox + name;
    }

    public String toFileString() {
        String s;
        if (isDone) {
            s = "1 | " + name;
        } else {
            s = "0 | " + name;
        }

        return s;
    }
}

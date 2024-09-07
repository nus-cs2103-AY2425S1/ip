package reo.tasks;

public class Task {
    private boolean isDone;
    private String name;
    public static int boolToInt(boolean b) {
        return b ? 1 : 0;
    }

    public Task(String name, boolean isDone) {
        assert name != null : "Task name cannot be null";
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
        assert s!= null: "String s cannot be null";
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

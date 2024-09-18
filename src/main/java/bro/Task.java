package bro;

public class Task {
    String name;
    boolean isDone = false;

    public Task(String s) {
        name = s;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String toLoad() {
        return "";
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + name;
        } else {
            return "[ ] " + name;
        }
    }
}
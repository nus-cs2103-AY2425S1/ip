package kobe.task;

public class Task {
    public final String name;
    public boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false; // Default is not done
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String showDoneOrNot() {
        return (isDone ? "[X]" : "[ ]"); // Return X or space depending on isDone
    }

    public String toFileFormat() {
        return (this instanceof Todo ? "T" : (this instanceof Deadline ? "D" : "E")) + " | " +
                (isDone ? "1" : "0") + " | " + name;
    }

    @Override
    public String toString() {
        return showDoneOrNot() + " " + name;
    }
}
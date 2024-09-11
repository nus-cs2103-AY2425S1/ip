package main.tasks;

public abstract class Task {
    protected final String text;
    protected boolean isDone;

    public Task(String text, boolean isDone) {
        this.text = text;
        this.isDone = isDone;
    }
    public abstract String export();

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    public String printStatus() {
        return this.isDone ? "X" : " ";
    }

    public boolean getStatus() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.printStatus(), this.text);
    }
}
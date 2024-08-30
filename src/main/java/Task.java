package main.java;

public abstract class Task {
    private String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public boolean isMarked() {
        return this.isDone;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        String cross = this.isDone ? "X" : " ";
        return String.format("[%s] %s", cross, this.name);
    }

    /**
     * Returns a readable string to be written into Karen.txt for storage
     *
     */
    public abstract String toFileString();
}

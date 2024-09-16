package ava.task;

abstract public class Task {
    private String title;
    private boolean isDone;
    private Priority priority;

    public Task(String title) {
        this.title = title;
        this.isDone = false;
    }

    public Task(String title, boolean isDone) {
        this.title = title;
        this.isDone = isDone;
    }

    public String getTitle() {
        return title;
    }

    public boolean isDone() {
        return isDone;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("%s | %s", this.isDone?"✅ Done   ":"❌ Pending",title);
    }
}

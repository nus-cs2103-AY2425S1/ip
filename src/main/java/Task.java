public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected char eventType;
    public Task(String description, char eventType) {
        this.description = description;
        this.isDone = false;
        this.eventType = eventType;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public char getEventType() {
        return this.eventType;
    }
    public void markTask() {
        this.isDone = true;
    }

    public void unMarkTask() {
        this.isDone = false;
    }
}
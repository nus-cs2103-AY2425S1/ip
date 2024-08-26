package gravitas.task;


public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected String eventType;

    public Task(String description, String eventType) {
        this.description = description;
        this.isDone = false;
        this.eventType = eventType;
    }
    public abstract String getDescription();
    public abstract String formatData();

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public String getEventType() {
        return this.eventType;
    }
    public void markTask() {
        this.isDone = true;
    }
    public void unMarkTask() {
        this.isDone = false;
    }

}
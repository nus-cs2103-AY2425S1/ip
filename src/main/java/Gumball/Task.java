package Gumball;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    protected String originalInput;

    protected String taskType;

    public Task(String description, String originalInput) {
        this.description = description;
        this.originalInput = originalInput;
        this.isDone = false;
        taskType = "[ ]";
    }

    public String getTaskType() {
        return taskType;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public String getDescription() {
        return description;
    }

    public String getOriginalInput() {
        return originalInput;
    }

    public void markDone() {
        isDone = true;
    }

    public boolean isItDone() {
        return isDone;
    }


}

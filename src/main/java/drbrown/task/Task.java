package drbrown.task;

public abstract class Task {

    private boolean status;
    private final String description;

    public Task(boolean status, String description) {
        this.status = status;
        this.description = description;
    }

    public Task() {
        this.description = "";
    }

    public boolean getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public abstract String toFileString();

    public abstract String toUIString();
}

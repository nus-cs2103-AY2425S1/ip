package Arona;

public class Task {
    protected String description;
    protected boolean isDone; // True means task is done

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getCategory() {
        return "[ ]";
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }// X means task is done

    public void setStatus(boolean newStatus) {
        isDone = newStatus;
    }

    public String toFriendlyString() {
        return this.toString();
    }

    @Override
    public String toString() {
        return description;
    }

}
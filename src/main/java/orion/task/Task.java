package orion.task;

public abstract class Task {
    private final int taskID;
    private final String description;
    private boolean completed;

    public Task(int taskID, String description) {
        this.description = description;
        this.completed = false;
        this.taskID = taskID;
    }

    public int getTaskID() {
        return taskID;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean newValue) {
        this.completed = newValue;
    }

    public abstract String getTypeIcon();
    // this function was suggested to me by Claude Sonnet as a way of making my code more modular

    @Override

    public String toString(){
        String tickBox;
        if (!this.completed) {
            tickBox = "[ ]";
        }   else {
            tickBox = "[x]";
        }

        return getTypeIcon() + tickBox + " " + this.description;
    }
}

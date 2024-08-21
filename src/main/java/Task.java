public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); //return tick or X symbols
    }

    public String displayDescription() {
        return description;
    }

    public void setCompletion(boolean done) {
        this.isDone = done;
    }

    public String getFullTask() {
        return (getStatusIcon() + " " + displayDescription());
    }
}
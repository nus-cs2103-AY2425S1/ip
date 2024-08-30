package sirpotato;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon indicating whether a task is complete
     * @return Status icon 
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); //return tick or X symbols
    }

    public String displayDescription() {
        return description;
    }

    public void setCompletion(boolean done) {
        this.isDone = done;
    }

    @Override
    public String toString() {
        return (getStatusIcon() + " " + displayDescription());
    }
}
public class Task {
    private String description;
    private boolean completed;
    public Task(String task){
        this.description = task;
        this.completed = false;
    }

    public void editDescription(String description) {
        this.description = description;
    }

    public void mark() {
        this.completed = true;
    }

    public void unmark() {
        this.completed = false;
    }

    public String getStatusIcon() {
        return this.completed ? "X" : " ";
    }

    public boolean getStatus() {
        return this.completed;
    }

    @Override
    public String toString() {
        return this.description;
    }
}

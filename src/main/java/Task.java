public class Task {
    private String taskName;
    private Boolean completed;

    public Task(String name) {
        this.taskName = name;
        this.completed = false;
    }

    public void mark() {
        this.completed = true;
    }

    public void unmark() {
        this.completed = false;
    }

    public String getStatus() {
        return this.completed ? "[X]" : "[ ]";
    }

    @Override
    public String toString() {
        return String.format("%s %s", this.getStatus(), this.taskName);
    }
}

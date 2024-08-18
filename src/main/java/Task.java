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

    @Override
    public String toString() {
        String status = this.completed ? "[X}" : "[ ]";
        return String.format("%s %s", status, this.taskName);
    }
}

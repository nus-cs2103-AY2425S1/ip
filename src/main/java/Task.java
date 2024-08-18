public class Task {
    private boolean completed = false;
    private String name;

    public Task(String taskName) {
        this.name = taskName;
    }

    public void markTask() {
        this.completed = true;
    }

    public String taskName() {
        return this.name;
    }

    public boolean taskStatus() {
        return this.completed;
    }
}

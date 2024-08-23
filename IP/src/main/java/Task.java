public class Task {
    private boolean completed;
    private String name;

    public Task(String name) {
        this.completed = false;
        this.name = name;
    }

    public void markAsCompleted() {
        this.completed = true;
    }

    public void markAsIncomplete() {
        this.completed = false;
    }
    @Override
    public String toString() {
        String box;
        if (this.completed) {
            box = "[X]";
        } else {
            box = "[ ]";
        }
        return box + " " + this.name;
    }
}

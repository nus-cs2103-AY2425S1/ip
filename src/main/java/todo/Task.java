package todo;

public class Task {
    private String name = "new task";
    private boolean status = false;

    Task() {}
    public Task(String name) {
        this.name = name;
    }

    public void markAsDone() {
        this.status = true;
    }

    public void markAsIncomplete() {
        this.status = false;
    }

    public void editTask(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("[%c] %s", this.status ? 'X' : ' ', this.name);
    }
}

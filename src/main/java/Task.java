public class Task {
    private String name;
    private boolean completed = false;

    public Task(String name) {
        this.name = name;
    }

    public void complete() {
        this.completed = true;
    }

    public void uncomplete() {
        this.completed = false;
    }

    @Override
    public String toString() {
        String completedBox = this.completed ? "[X] " : "[ ] ";
        return completedBox + this.name;
    }
}

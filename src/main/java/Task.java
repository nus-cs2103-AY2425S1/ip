public class Task {
    private boolean completed;
    private String name;
    private static final String done = "[X]";
    private static final String undone = "[ ]";

    public Task(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return (completed ? done : undone) + this.name;
    }
}

public class Task {
    private boolean isCompleted = false;
    private final String name;

    public Task(String name) {
        this.name = name;
    }

    public void mark() {
        this.isCompleted = true;
    }

    public void unmark() {
        this.isCompleted = false;
    }

    @Override
    public String toString() {
        return "[" + (isCompleted ? "X" : " ") + "] " + this.name + "\n";
    }
}

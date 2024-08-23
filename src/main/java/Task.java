public class Task {
    private boolean isComplete = false;
    private final String name;

    public Task(String name) {
        this.name = name;
    }

    public void mark() {
        this.isComplete = true;
    }

    public void unmark() {
        this.isComplete = false;
    }

    @Override
    public String toString() {
        return "[" + (isComplete ? "X" : " ") + "] " + this.name;
    }
}

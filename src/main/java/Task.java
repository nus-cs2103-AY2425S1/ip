public class Task {
    private boolean isComplete = false;
    private String name;

    public Task(String name) {
        this.name = name;
    }

    public void mark() {
        isComplete = true;
    }

    public void unmark() {
        isComplete = false;
    }

    @Override
    public String toString() {
        return "[" + (isComplete ? "X" : " ") + "] " + name;
    }
}

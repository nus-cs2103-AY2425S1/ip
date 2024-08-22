public class Task {
    private boolean isComplete = false;
    private String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "[" + (isComplete ? "X" : " ") + "] " + name;
    }
}

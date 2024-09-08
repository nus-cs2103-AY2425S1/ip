public class Task {
    private boolean isComplete = false;
    private final String name;

    public Task(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void mark() {
        this.isComplete = true;
    }

    public void unmark() {
        this.isComplete = false;
    }

    public String toFileString() {
        return "N , 0 , T\n";
    }

    @Override
    public String toString() {
        return "[" + (isComplete ? "X" : " ") + "] " + this.name;
    }
}

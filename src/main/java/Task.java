public class Task {
    private boolean completed;
    private final String name;

    public Task(String name) {
        this.completed = false;
        this.name = name;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean getCompleted() {
        return this.completed;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "[" + (this.getCompleted() ? "X" : " ") + "] " + this.name;
    }
}

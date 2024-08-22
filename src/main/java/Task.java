public class Task {
    private final String name;
    private boolean completed;

    public Task(String name) {
        this.name = name;
        this.completed = false;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public void markComplete() {
        this.completed = true;
    }

    public void markUncomplete() {
        this.completed = false;
    }

    @Override
    public String toString() {
        return "["
                + ((this.isCompleted()) ? "X" : " ")
                + "] "
                + this.name;
    }
}

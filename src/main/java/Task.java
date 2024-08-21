public class Task {
    private String name;
    private boolean completed = false;

    public Task(String name) {
        this.name = name;
    }

    public void complete() {
        this.completed = true;
    }

    public boolean isComplete() {
        return this.completed;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

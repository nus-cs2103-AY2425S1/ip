public class Task {
    public String name;
    public boolean isDone;

    public Task(String name) {
        this.name =name;
        this.isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unMarkAsDone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return isDone? "[X] " + name : "[ ] " + name;
    }
}

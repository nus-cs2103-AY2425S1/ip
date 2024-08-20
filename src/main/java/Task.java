public class Task {
    String name;
    Boolean isCompleted;

    public Task(String name) {
        this.name = name;
        this.isCompleted = false;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

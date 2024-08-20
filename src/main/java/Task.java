public class Task {
    String name;
    boolean isCompleted;

    public Task(String name) {
        this.name = name;
        this.isCompleted = false;
    }

    public void setComplete(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    @Override
    public String toString() {
        if (isCompleted) {
            return "[x] " + this.name;
        }
        return "[ ] " + this.name;
    }
}

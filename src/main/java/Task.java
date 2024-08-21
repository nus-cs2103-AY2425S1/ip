public class Task {
    boolean completed;
    String description;

    public Task(String s) {
        this.completed = false;
        this.description = s;
    }

    @Override
    public String toString() {
        if (this.completed) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }
}

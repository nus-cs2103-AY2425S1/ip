public class Task {
    String description;
    boolean status;

    public Task(String description) {
        this.description = description;
        this.status = false;
    }

    public void markAsDone() {
        this.status = true;
    }

    public void unmarkAsUndone() {
        this.status = false;
    }

    @Override
    public String toString() {
        if (this.status) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }
}

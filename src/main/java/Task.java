public class Task {
    private String description;
    private boolean done;

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    public void changeDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        if (this.done) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }
}

public class Task {
    private String description;
    private boolean done;

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isDone() {
        return this.done;
    }

    public void complete() {
        this.done = true;
    }

    public void uncomplete() {
        this.done = false;
    }

    @Override
    public String toString() {
        String complete = " ";
        if (this.done) {
            complete = "X";
        }
        return "[" + complete + "] " + this.description;
    }
}

public class Task {

    private boolean done;
    private String taskName;

    public Task(String name) {
        this.done = false;
        this.taskName = name;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    @Override
    public String toString() {
        if (this.done) {
            return "[X] " + this.taskName;
        } else {
            return "[ ] " + this.taskName;
        }
    }
    
}

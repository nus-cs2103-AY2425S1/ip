public class Task {
    boolean done;
    String desc;

    public Task(String d) {
        this.desc = d;
        this.done = false;
    }

    public void markAsDone() {
        this.done = true;
    }

    public void markAsUndone() {
        this.done = false;
    }

    @Override
    public String toString() {
        if (done) {
            return "[X] " + desc;
        } else
            return "[ ] " + desc;
    }
}

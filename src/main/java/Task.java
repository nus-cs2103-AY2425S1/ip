public class Task {
    private final String name;
    private boolean done;

    public Task (String name) {
        this.name = name;
        this.done = false;
    }

    @Override
    public String toString() {
        if (done) {
            return "[x] " + name;
        }
        return "[ ] " + name;
    }

    public void setDone() {
        this.done = true;
    }

    public void setUndone() {
        this.done = false;
    }
}
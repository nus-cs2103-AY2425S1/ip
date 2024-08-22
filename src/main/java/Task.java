public class Task {
    private String description;
    private boolean done;

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    public void setDone(boolean nv) {
        done = nv;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        char isDone = ' ';
        if (done) isDone = 'X';
        return "[" + isDone + "] " + description;
    }
}

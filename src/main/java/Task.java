abstract public class Task {
    private final String name;
    private boolean done;
    private final int count;

    public Task(String name, int count) {
        this.name = name;
        this.count = count;
        this.done = false;
    }

    public boolean mark() {
        if (done) {
            return false;
        } else {
            done = true;
            return done;
        }
    }

    public boolean unmark() {
        if (!done) {
            return false;
        } else {
            done = false;
            return true;
        }
    }

    public int getCount() {
        return this.count;
    }

    public String toString() {
        String checked = this.done
                ? "[X] "
                : "[] ";
        return checked + this.name;
    }
}

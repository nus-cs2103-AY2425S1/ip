public class Task {
    private final String name;
    private boolean done;
    private final int count;

    public Task(String name, int count) {
        this.name = name;
        this.count = count;
        this.done = false;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    public String toString() {
        String checked = this.done
                ? "[X] "
                : "[] ";
        return this.count + ": " + checked + this.name;
    }
}

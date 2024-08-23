abstract public class Task {
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

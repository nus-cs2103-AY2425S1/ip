package Nave;

abstract public class Task {
    private final String name;
    private boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public boolean mark() {
        if (done) {
            return false;
        } else {
            done = true;
            return true;
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

    public String toString() {
        String checked = done
                ? "[X] "
                : "[] ";
        return checked + name;
    }

    public String toFileFormat() {
        return name;
    }
    abstract String creationResponse();
}

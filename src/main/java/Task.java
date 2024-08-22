public class Task {
    protected final String name;
    protected boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public String getName() {
        return this.name;
    }
    public void markDone() {
        this.done = true;
    }

    public void markUndone() {
        this.done = false;
    }

    @Override
    public String toString() {
        String out;
        if (this.done) {
            out = "{X} " + this.name;
        } else {
            out = "{ } " + this.name;
        }
        return out;
    }
}
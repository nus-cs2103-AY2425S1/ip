public class Task {
    private final String name;
    private boolean done;

    public Task(String name) {
        this.name = name;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    @Override
    public String toString() {
        String doneMark;
        if (done) {
            doneMark = "X";
        } else {
            doneMark = " ";
        }

        return "[" + doneMark + "] " + this.name;
    }
}

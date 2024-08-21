public class Task {
    private final String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        String doneMark;
        if (isDone) {
            doneMark = "X";
        } else {
            doneMark = " ";
        }

        return "[" + doneMark + "] " + this.name;
    }
}

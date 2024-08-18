class Task {
    private final String name;
    private boolean isDone;

    Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    void markAsDone() {
        this.isDone = true;
    }

    void unmark() {
        this.isDone = false;
    }
    @Override
    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "] " + this.name;
    }
}
